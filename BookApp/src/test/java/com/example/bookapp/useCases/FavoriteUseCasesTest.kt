package com.example.bookapp.useCases

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import com.example.bookapp.domain.useCases.Favorite.GetFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.RemoveFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class FavoriteUseCasesTest : BaseTest() {
    private lateinit var getFavoriteUseCase: GetFavoriteUseCase
    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase
    private lateinit var repository: FavoriteRepository

    @Before
    fun setupUseCase() {
        repository = mockk()
        getFavoriteUseCase = GetFavoriteUseCase(repository)
        toggleFavoriteUseCase = ToggleFavoriteUseCase(repository)
        removeFavoriteUseCase = RemoveFavoriteUseCase(repository)
    }

    @Test
    fun `GetFavoriteUseCase should return favorites from repository`() = runTest {
        val books = listOf(
            Book(
                id = "1",
                title = "Test Book",
                authors = listOf("Author"),
                categories = emptyList()
            )
        )
        coEvery { repository.getAllFavorites() } returns flowOf(books)

        val result = getFavoriteUseCase().first()

        assertThat(result).isEqualTo(books)
    }

    @Test
    fun `ToggleFavoriteUseCase should call repository addFavorite`() = runTest {
        val book = Book(
            id = "1",
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList()
        )
        coEvery { repository.addFavorite(book) } just Runs

        toggleFavoriteUseCase(book)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.addFavorite(book) }
    }

    @Test
    fun `RemoveFavoriteUseCase should call repository removeFavorite`() = runTest {
        val book = Book(
            id = "1",
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList()
        )
        coEvery { repository.removeFavorite(book) } just Runs

        removeFavoriteUseCase(book)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.removeFavorite(book) }
    }
}