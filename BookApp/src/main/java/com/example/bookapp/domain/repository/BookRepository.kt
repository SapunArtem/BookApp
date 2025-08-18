package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Book

interface BookRepository {
    suspend fun getBooks(query: String): Result<List<Book>>
    suspend fun getBookDetails(id: String): Result<Book>
}