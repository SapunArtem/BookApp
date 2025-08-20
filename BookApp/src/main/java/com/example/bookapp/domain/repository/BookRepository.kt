package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Book

/**
 * Репозиторий для работы с книгами.
 */
interface BookRepository {
    /**
     * Получение списка книг по поисковому запросу.
     *
     * @param query поисковый запрос
     * @return результат с списком книг или ошибкой
     */
    suspend fun getBooks(query: String): Result<List<Book>>

    /**
     * Получение детальной информации о книге по ID.
     *
     * @param id идентификатор книги
     * @return результат с объектом книги или ошибкой
     */
    suspend fun getBookDetails(id: String): Result<Book>
}