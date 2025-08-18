package com.example.bookapp.domain.useCases.Favorite

import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(): List<Book> = repository.getAllFavorites()
}