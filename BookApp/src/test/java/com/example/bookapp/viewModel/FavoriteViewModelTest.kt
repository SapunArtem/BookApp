package com.example.bookapp.viewModel

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Favorite.GetFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.RemoveFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import com.example.bookapp.presentation.viewModel.FavoriteUiState
import com.example.bookapp.presentation.viewModel.FavoriteViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteViewModelTest : BaseTest() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var getFavoriteUseCase: GetFavoriteUseCase
    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase

    @Before
    fun setupViewModel() {
        MockKAnnotations.init(this)
        getFavoriteUseCase = mockk(relaxed = true)
        toggleFavoriteUseCase = mockk(relaxed = true)
        removeFavoriteUseCase = mockk(relaxed = true)
        viewModel = FavoriteViewModel(getFavoriteUseCase, toggleFavoriteUseCase, removeFavoriteUseCase)
    }

    @Test
    fun `loadFavorites should update uiState with Success when favorites exist`() = runTest {
        val books = listOf(
            Book(
                id = "1",
                title = "Test Book",
                authors = listOf("Author"),
                categories = emptyList())
        )

        coEvery { getFavoriteUseCase() } returns flowOf(books)

        viewModel.loadFavorites()
        testScheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertThat(uiState).isInstanceOf(FavoriteUiState.Success::class.java)
        assertThat((uiState as FavoriteUiState.Success).favorites).isEqualTo(books)
    }

    @Test
    fun `loadFavorites should update uiState with Empty when no favorites`() = runTest {
        coEvery { getFavoriteUseCase() } returns flowOf(emptyList())

        viewModel.loadFavorites()
        testScheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isInstanceOf(FavoriteUiState.Empty::class.java)
    }

    @Test
    fun `loadFavorites should update uiState with Error on failure`() = runTest {
        val error = "Error message"

        coEvery { getFavoriteUseCase() } returns flow { throw Exception(error) }

        viewModel.loadFavorites()
        testScheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isInstanceOf(FavoriteUiState.Error::class.java)
        assertThat((viewModel.uiState.value as FavoriteUiState.Error).message).isEqualTo(error)
    }

    @Test
    fun `toggleFavorite should call toggleFavoriteUseCase when not favorite`() = runTest {
        val book = Book(
            id = "1",
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList())

        coEvery { toggleFavoriteUseCase(book) } just Runs
        coEvery { getFavoriteUseCase() } returns flowOf(emptyList())

        viewModel.toggleFavorite(book, false)
        testScheduler.advanceUntilIdle()

        coVerify { toggleFavoriteUseCase(book) }
    }

    @Test
    fun `toggleFavorite should call removeFavoriteUseCase when favorite`() = runTest {
        val book = Book(
            id = "1",
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList())

        coEvery { removeFavoriteUseCase(book) } just Runs
        coEvery { getFavoriteUseCase() } returns flowOf(listOf(book))

        viewModel.toggleFavorite(book, true)
        testScheduler.advanceUntilIdle()

        coVerify { removeFavoriteUseCase(book) }
    }

    @Test
    fun `isFavoriteFlow should return true when book is favorite`() = runTest {
        val bookId = "1"
        val books = listOf(Book(
            id = bookId,
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList()))

        coEvery { getFavoriteUseCase() } returns flowOf(books)

        val result = viewModel.isFavoriteFlow(bookId).first()

        assertThat(result).isTrue()
    }
}