package com.example.bookapp.domain.useCases

import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksDetailsUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id : String): Result<Book>{
        return repository.getBookDetails(id)
    }
}