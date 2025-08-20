package com.example.bookapp.repository

import com.example.bookapp.BaseTest
import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.entity.FavoriteEntity
import com.example.bookapp.data.local.repository.FavoriteRepositoryImpl
import com.example.bookapp.data.mapper.FavoriteMapper
import com.example.bookapp.domain.models.Book
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FavoriteRepositoryImplTest : BaseTest() {
    private lateinit var repository: FavoriteRepositoryImpl
    private lateinit var dao: FavoriteDao
    private lateinit var mapper: FavoriteMapper

    @Before
    fun setupRepository() {
        dao = mockk()
        mapper = mockk()
        repository = FavoriteRepositoryImpl(dao, mapper)
    }

    @Test
    fun `getAllFavorites should return mapped books`() = runTest {

        val entity = FavoriteEntity(
            id = "1",
            title = "Test",
            authors = "Author",
            categories = "",
            publisher = "Test Publisher",
            publishedDate = "2025-01-01",
            description = "Test description",
            pageCount = 123,
            thumbnail = "https://example.com/cover.jpg",
            language = "en",
            previewLink = "https://example.com/preview",
            infoLink = "https://example.com/info",
            isEbook = false,
            saleability = "FOR_SALE"
        )
        val book = Book(
            id = "1",
            title = "Test",
            authors = listOf("Author"),
            categories = emptyList(),

            )
        coEvery { dao.getAll() } returns flowOf(listOf(entity))
        every { mapper.mapToDomain(entity) } returns book


        val result = repository.getAllFavorites().first()


        assertThat(result).containsExactly(book)
    }

    @Test
    fun `addFavorite should call dao insert with mapped entity`() = runTest {

        val book = Book(
            id = "1",
            title = "Test",
            authors = listOf("Author"),
            categories = emptyList(),
            isEbook = false
        )
        val entity = FavoriteEntity(
            id = "1",
            title = "Test",
            authors = "Author",
            categories = "",
            publisher = "Test Publisher",
            publishedDate = "2025-01-01",
            description = "Test description",
            pageCount = 100,
            thumbnail = "https://example.com/image.jpg",
            language = "en",
            previewLink = "https://example.com/preview",
            infoLink = "https://example.com/info",
            saleability = "NOT_FOR_SALE",
            isEbook = false
        )
        coEvery { dao.insert(any()) } just Runs
        every { mapper.mapToEntity(book) } returns entity

        repository.addFavorite(book)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { dao.insert(entity) }
    }

    @Test
    fun `isFavorite should return flow from dao`() = runTest {

        val bookId = "1"
        coEvery { dao.isFavorite(bookId) } returns flowOf(true)

        val result = repository.isFavorite(bookId).first()

        assertThat(result).isTrue()
    }
}