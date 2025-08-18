package com.example.bookapp.data.remote.dataSources

import com.example.bookapp.data.remote.models.BookDto
import com.example.bookapp.data.remote.models.BooksResponseDto

interface BooksRemoteDataSources {
    suspend fun getBooks(query: String): BooksResponseDto
    suspend fun getBookDetails(id: String): BookDto
}