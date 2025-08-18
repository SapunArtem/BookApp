package com.example.bookapp.domain.useCases.Favorite

import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(book: Book){
        if (repository.isFavorite(book.id)){
            repository.removeFavorite(book.id)
        }else{
            repository.addFavorite(book)
        }
    }
}