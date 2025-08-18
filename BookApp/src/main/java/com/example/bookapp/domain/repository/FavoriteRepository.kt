package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Book
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Book>>
    suspend fun addFavorite(book: Book)
    suspend fun removeFavorite(book: Book)
    fun isFavorite(bookId: String): Flow<Boolean>
}