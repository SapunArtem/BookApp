package com.example.bookapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Home.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {
    private val _state = mutableStateOf(BooksState())
    val state: State<BooksState> = _state

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private var _lastQuery = MutableStateFlow("")
    val lastQuery: StateFlow<String> = _lastQuery

    init {
        searchBooks("а")
    }

    fun updateLastQuery(query: String) {
        _lastQuery.value = query
    }

    fun searchBooks(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            _lastQuery.value = query

            try {
                getBooksUseCase(query)
                    .onSuccess { response ->
                        _books.value = response
                    }
                    .onFailure { e ->
                        _state.value = _state.value.copy(
                            error = e.message ?: "Unknown error",
                            isLoading = false
                        )
                    }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Network error: ${e.localizedMessage}",
                    isLoading = false
                )
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}


data class BooksState(
    val isLoading: Boolean = false,
    val error: String? = null
)