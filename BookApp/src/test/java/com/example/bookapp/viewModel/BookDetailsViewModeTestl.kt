package com.example.bookapp.viewModel

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import com.example.bookapp.presentation.viewModel.BookDetailsViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class BookDetailsViewModelTest : BaseTest() {
    private lateinit var viewModel: BookDetailsViewModel
    private lateinit var getBooksDetailsUseCase: GetBooksDetailsUseCase

    @Before
    fun setupViewModel() {
        getBooksDetailsUseCase = mockk()
        viewModel = BookDetailsViewModel(getBooksDetailsUseCase)
    }

    @Test
    fun `loadDetails should update bookDetails on success`() = runTest {
        val bookId = "1"
        val book = Book(
            id = bookId,
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList()
        )
        coEvery { getBooksDetailsUseCase(bookId) } returns Result.success(book)

        viewModel.loadDetails(bookId)
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.isLoading.value).isFalse()
        assertThat(viewModel.error.value).isNull()
        assertThat(viewModel.bookDetails.value).isEqualTo(book)
    }

    @Test
    fun `loadDetails should handle error`() = runTest {
        val bookId = "1"
        val error = "Error message"
        coEvery { getBooksDetailsUseCase(bookId) } returns Result.failure(Exception(error))

        viewModel.loadDetails(bookId)
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.isLoading.value).isFalse()
        assertThat(viewModel.error.value).isEqualTo(error)
        assertThat(viewModel.bookDetails.value).isNull()
    }
}