package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Book
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для работы с избранными книгами.
 */
interface FavoriteRepository {
    /**
     * Получение всех избранных книг в виде потока.
     */
    fun getAllFavorites(): Flow<List<Book>>

    /**
     * Добавление книги в избранное.
     */
    suspend fun addFavorite(book: Book)

    /**
     * Удаление книги из избранного.
     */
    suspend fun removeFavorite(book: Book)

    /**
     * Проверка, находится ли книга в избранном.
     */
    fun isFavorite(bookId: String): Flow<Boolean>
}