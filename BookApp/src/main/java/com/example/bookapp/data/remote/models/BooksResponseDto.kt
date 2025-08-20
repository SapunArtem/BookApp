package com.example.bookapp.data.remote.models

/**
 * DTO с ответом на поиск книг.
 */
data class BooksResponseDto(
    val kind: String?,
    val totalItems: Int?,
    val items: List<BookDto>
)
