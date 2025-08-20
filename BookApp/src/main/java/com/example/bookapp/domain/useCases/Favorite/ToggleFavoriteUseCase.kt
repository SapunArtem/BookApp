package com.example.bookapp.domain.useCases.Favorite

import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import javax.inject.Inject

/**
 * Use case для добавления книги в избранное.
 */
class ToggleFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.addFavorite(book)
    }
}