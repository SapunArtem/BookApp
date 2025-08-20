package com.example.bookapp.useCases

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.BookRepository
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Тесты для [GetBooksDetailsUseCase].
 *
 * Проверяется:
 * - успешное получение книги по id;
 * - обработка ошибки при получении деталей книги.
 */
class GetBooksDetailsUseCaseTest : BaseTest() {
    private lateinit var useCase: GetBooksDetailsUseCase
    private lateinit var repository: BookRepository

    @Before
    fun setupUseCase() {
        repository = mockk()
        useCase = GetBooksDetailsUseCase(repository)
    }

    /**
     * Тест проверяет, что при успешном запросе [BookRepository.getBookDetails]
     * возвращается корректный объект [Book].
     */
    @Test
    fun `invoke should return book details on success`() = runTest {
        // Given
        val bookId = "1"
        val book = Book(
            id = bookId,
            title = "Test Book",
            authors = listOf("Author"),
            categories = emptyList()
        )
        coEvery { repository.getBookDetails(bookId) } returns Result.success(book)

        val result = useCase(bookId)

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(book)
    }

    /**
     * Тест проверяет, что при ошибке запроса возвращается [Result.failure].
     */
    @Test
    fun `invoke should return failure on error`() = runTest {
        val bookId = "1"
        val error = Exception("Error")
        coEvery { repository.getBookDetails(bookId) } returns Result.failure(error)

        val result = useCase(bookId)

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(error)
    }
}