package com.example.bookapp.data.remote.dataSources

import com.example.bookapp.data.remote.api.BooksServiceApi
import com.example.bookapp.data.remote.models.BookDto
import com.example.bookapp.data.remote.models.BooksResponseDto
import javax.inject.Inject

/**
 * Реализация удалённого источника данных для работы с API книг.
 *
 * @param api сервис для работы с Google Books API.
 */
class BooksRemoteDataSourcesImpl @Inject constructor(
    private val api: BooksServiceApi
) : BooksRemoteDataSources {
    override suspend fun getBooks(query: String): BooksResponseDto {
        return api.getBooks(query)
    }

    override suspend fun getBookDetails(id: String): BookDto {
        return api.getBookDetails(id)
    }
}