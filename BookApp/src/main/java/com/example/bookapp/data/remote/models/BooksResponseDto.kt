package com.example.bookapp.data.remote.models

data class BooksResponseDto(
    val kind: String?,
    val totalItems: Int?,
    val items: List<BookDto>
)
