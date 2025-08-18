package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Book

interface FavoriteRepository {
    suspend fun getAllFavorites(): List<Book>
    suspend fun addFavorite(book: Book)
    suspend fun removeFavorite(bookId: String)
    suspend fun isFavorite(bookId: String): Boolean
    suspend fun getFavoriteDetails(bookId: String): Book?
}