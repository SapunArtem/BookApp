package com.example.bookapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Favorite.GetFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.RemoveFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для работы с избранными книгами.
 * Управляет списком избранного и обновляет его при добавлении/удалении.
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    /** Состояние UI избранных книг */
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState

    init {
        loadFavorites()
    }


    /** Загружает список избранных книг */
    fun loadFavorites() {
        viewModelScope.launch {
            getFavoriteUseCase()
                .catch { e ->
                    _uiState.value = FavoriteUiState.Error(e.message ?: "Unknown error")
                }
                .collectLatest { favorites ->
                    _uiState.value = if (favorites.isEmpty()) {
                        FavoriteUiState.Empty
                    } else {
                        FavoriteUiState.Success(favorites)
                    }
                }
        }
    }


    /**
     * Переключает статус избранного для книги
     * @param book - объект книги
     * @param isFavorite - текущее состояние избранного
     */
    fun toggleFavorite(book: Book, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                if (isFavorite) {
                    removeFavoriteUseCase(book)
                } else {
                    toggleFavoriteUseCase(book)
                }
            } catch (e: Exception) {
                _uiState.value = FavoriteUiState.Error("Failed to update favorites: ${e.message}")
            }
        }
    }

    /**
     * Возвращает поток, отслеживающий, является ли книга избранной
     * @param bookId - ID книги
     */
    fun isFavoriteFlow(bookId: String): Flow<Boolean> {
        return getFavoriteUseCase()
            .map { favorites -> favorites.any { it.id == bookId } }
    }
}

/** Состояния экрана избранного */
sealed class FavoriteUiState {
    object Loading : FavoriteUiState()
    object Empty : FavoriteUiState()
    data class Success(val favorites: List<Book>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}