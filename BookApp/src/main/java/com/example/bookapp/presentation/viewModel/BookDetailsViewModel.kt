package com.example.bookapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Favorite.RemoveFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBooksDetailsUseCase: GetBooksDetailsUseCase,
): ViewModel() {
    private val _bookDetails = MutableStateFlow<Book?>(null)
    val bookDetails: StateFlow<Book?> = _bookDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadDetails(bookId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            getBooksDetailsUseCase(bookId)
                .onSuccess {
                    _bookDetails.value = it
                    _error.value = null
                }
                .onFailure {e->
                    _error.value = e.message ?: "Faild to load book details"
                    _bookDetails.value = null
                }
            _isLoading.value = false
        }


    }
}
