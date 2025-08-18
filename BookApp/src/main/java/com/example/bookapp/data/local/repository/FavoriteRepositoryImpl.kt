package com.example.bookapp.data.local.repository

import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.mapper.FavoriteMapper
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val mapper: FavoriteMapper
) : FavoriteRepository{
    override suspend fun getAllFavorites(): List<Book> {
        return dao.getAll().map { mapper.mapToDomain(it) }
    }

    override suspend fun addFavorite(book: Book) {
        dao.insert(mapper.mapToEntity(book))
    }

    override suspend fun removeFavorite(bookId: String) {
        dao.deleteById(bookId)
    }

    override suspend fun isFavorite(bookId: String): Boolean {
        return dao.exists(bookId)
    }

    override suspend fun getFavoriteDetails(bookId: String): Book? {
        return dao.getById(bookId)?.let { mapper.mapToDomain(it) }
    }
}