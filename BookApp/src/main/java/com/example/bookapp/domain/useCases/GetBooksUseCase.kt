package com.example.bookapp.domain.useCases

import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.BookRepository
import retrofit2.http.Query
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(query: String): Result<List<Book>>{
        return repository.getBooks(query)
    }
}