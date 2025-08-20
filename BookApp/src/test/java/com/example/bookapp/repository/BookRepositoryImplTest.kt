package com.example.bookapp.repository

import com.example.bookapp.BaseTest
import com.example.bookapp.data.mapper.toDomain
import com.example.bookapp.data.remote.dataSources.BooksRemoteDataSources
import com.example.bookapp.data.remote.models.AccessInfoDto
import com.example.bookapp.data.remote.models.BookDto
import com.example.bookapp.data.remote.models.BookInfoDto
import com.example.bookapp.data.remote.models.BooksResponseDto
import com.example.bookapp.data.remote.models.ImageLinksDto
import com.example.bookapp.data.remote.models.SaleInfoDto
import com.example.bookapp.data.remote.repository.BooksRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Тесты для [BooksRepositoryImpl].
 *
 * Проверяется:
 * - успешное получение списка книг;
 * - успешное получение деталей книги по id.
 */
class BooksRepositoryImplTest : BaseTest() {
    private lateinit var repository: BooksRepositoryImpl
    private lateinit var remoteDataSources: BooksRemoteDataSources

    @Before
    fun setupRepository() {
        remoteDataSources = mockk()
        repository = BooksRepositoryImpl(remoteDataSources)
    }

    /**
     * Тест проверяет, что метод [BooksRepositoryImpl.getBooks]
     * возвращает корректно замапленный список книг при успешном ответе API.
     */
    @Test
    fun `getBooks should return mapped books on success`() = runTest {

        val dto = BookDto(
            kind = "books#volume",
            id = "1",
            etag = "etag123",
            selfLink = "http://example.com/book/1",
            volumeInfo = BookInfoDto(
                title = "Test",
                authors = listOf("Author"),
                publisher = "Test Publisher",
                publishedDate = "2025-01-01",
                description = "Some description",
                industryIdentifiers = emptyList(),
                pageCount = 123,
                categories = listOf("Category"),
                maturityRating = "NOT_MATURE",
                imageLinks = ImageLinksDto(
                    smallThumbnail = "",
                    thumbnail = ""
                ),
                language = "en",
                previewLink = "",
                infoLink = "",
                canonicalVolumeLink = ""
            ),
            saleInfo = SaleInfoDto(
                country = "US",
                saleability = "FOR_SALE",
                isEbook = false
            ),
            accessInfo = AccessInfoDto(
                country = "US",
                viewability = "PARTIAL",
                embeddable = true,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED",
                webReaderLink = "",

                ),
            searchInfo = null
        )
        val expectedBook = dto.toDomain()
        coEvery { remoteDataSources.getBooks(any()) } returns BooksResponseDto(
            kind = "books#volumes",
            totalItems = 1,
            items = listOf(dto)
        )

        val result = repository.getBooks("test")

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).containsExactly(expectedBook)
    }

    /**
     * Тест проверяет, что метод [BooksRepositoryImpl.getBookDetails]
     * возвращает замапленную модель [Book] при успешном ответе API.
     */
    @Test
    fun `getBookDetails should return mapped book on success`() = runTest {
        val dto = BookDto(
            kind = "books#volume",
            id = "1",
            etag = "etag123",
            selfLink = "http://example.com/book/1",
            volumeInfo = BookInfoDto(
                title = "Test",
                authors = listOf("Author"),
                publisher = "Test Publisher",
                publishedDate = "2025-01-01",
                description = "Some description",
                industryIdentifiers = emptyList(),
                pageCount = 123,
                categories = listOf("Category"),
                maturityRating = "NOT_MATURE",
                imageLinks = ImageLinksDto(
                    smallThumbnail = "",
                    thumbnail = ""
                ),
                language = "en",
                previewLink = "",
                infoLink = "",
                canonicalVolumeLink = ""
            ),
            saleInfo = SaleInfoDto(
                country = "US",
                saleability = "FOR_SALE",
                isEbook = false
            ),
            accessInfo = AccessInfoDto(
                country = "US",
                viewability = "PARTIAL",
                embeddable = true,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED",
                webReaderLink = "",

                ),
            searchInfo = null
        )
        val expectedBook = dto.toDomain()
        coEvery { remoteDataSources.getBookDetails(any()) } returns dto

        val result = repository.getBookDetails("1")

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedBook)
    }
}