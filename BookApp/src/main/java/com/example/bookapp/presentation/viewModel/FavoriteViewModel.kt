package com.example.bookapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Favorite.GetFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _state = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> = _state

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val favorites = getFavoriteUseCase()
                _state.value = _state.value.copy(
                    books = favorites,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to load favorites",
                    isLoading = false
                )
            }
        }
    }

    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            toggleFavoriteUseCase(book)
            loadFavorites() // Обновляем список после изменения
        }
    }
}
data class FavoriteState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)