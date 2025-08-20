package com.example.bookapp.viewModel

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Home.GetBooksUseCase
import com.example.bookapp.presentation.viewModel.BookViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Тесты для [BookViewModel].
 *
 * Проверяется:
 * - успешный поиск книг;
 * - обработка ошибок поиска;
 * - обновление последнего поискового запроса.
 */
class BookViewModelTest : BaseTest() {
    private lateinit var viewModel: BookViewModel
    private lateinit var getBooksUseCase: GetBooksUseCase

    @Before
    fun setupViewModel() {
        getBooksUseCase = mockk()
        viewModel = BookViewModel(getBooksUseCase)
    }

    /**
     * Тест проверяет, что при успешном поиске:
     * - загрузка завершается,
     * - ошибок нет,
     * - список книг обновляется,
     * - сохраняется последний запрос.
     */
    @Test
    fun `searchBooks should update state and books on success`() = runTest {
        val query = "test"
        val books = listOf(
            Book(
                id = "1",
                title = "Test Book",
                authors = listOf("Author"),
                categories = emptyList()
            )
        )

        coEvery { getBooksUseCase(query) } returns Result.success(books)

        viewModel.searchBooks(query)
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.state.value.isLoading).isFalse()
        assertThat(viewModel.state.value.error).isNull()
        assertThat(viewModel.books.value).isEqualTo(books)
        assertThat(viewModel.lastQuery.value).isEqualTo(query)
    }

    /**
     * Тест проверяет, что при ошибке поиска:
     * - загрузка завершается,
     * - ошибка сохраняется,
     * - список книг очищается.
     */
    @Test
    fun `searchBooks should handle error`() = runTest {
        val query = "test"
        val error = "Network error"
        coEvery { getBooksUseCase(query) } returns Result.failure(Exception(error))

        viewModel.searchBooks(query)
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.state.value.isLoading).isFalse()
        assertThat(viewModel.state.value.error).isEqualTo(error)
        assertThat(viewModel.books.value).isEmpty()
    }

    /**
     * Тест проверяет, что метод [BookViewModel.updateLastQuery]
     * корректно обновляет значение последнего поискового запроса.
     */
    @Test
    fun `updateLastQuery should update lastQuery`() {
        val query = "new query"

        viewModel.updateLastQuery(query)

        assertThat(viewModel.lastQuery.value).isEqualTo(query)
    }
}