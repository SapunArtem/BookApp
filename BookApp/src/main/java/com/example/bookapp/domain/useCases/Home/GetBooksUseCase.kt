package com.example.bookapp.domain.useCases.Home

import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.BookRepository
import javax.inject.Inject

/**
 * Use case для получения списка книг по поисковому запросу.
 */
class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(query: String): Result<List<Book>> {
        return repository.getBooks(query)
    }
}