package com.example.bookapp.data.remote.api

import com.example.bookapp.data.remote.models.BookDto
import com.example.bookapp.data.remote.models.BooksResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.bookapp.utils.Constant

/**
 * Интерфейс для работы с API Google Books через Retrofit.
 */
interface BooksServiceApi {
    /**
     * Получение списка книг по запросу.
     *
     * @param query поисковая строка.
     * @param apiKey ключ API (по умолчанию берётся из Constant.API_KEY).
     * @return DTO с результатами поиска книг.
     */
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = Constant.API_KEY
    ): BooksResponseDto

    /**
     * Получение детальной информации о книге.
     *
     * @param volumeId ID книги.
     * @param apiKey ключ API.
     * @return DTO с подробной информацией о книге.
     */
    @GET("volumes/{volumeId}")
    suspend fun getBookDetails(
        @Path("volumeId") volumeId: String,
        @Query("key") apiKey: String = Constant.API_KEY
    ): BookDto
}