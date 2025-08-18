package com.example.bookapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBooksDetailsUseCase: GetBooksDetailsUseCase
): ViewModel(){
    private val _state = mutableStateOf(BookDetailsState())
    val state: State<BookDetailsState> = _state

    fun loadDetails(bookId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getBooksDetailsUseCase(bookId)
                .onSuccess { details ->
                    _state.value = _state.value.copy(
                        book = details,
                        isLoading = false
                    )
                }
                .onFailure { e ->
                    _state.value = _state.value.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
        }
    }

}
data class BookDetailsState(
    val book: Book? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)