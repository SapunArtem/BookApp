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

/**
 * ViewModel для домашнего экрана и поиска книг.
 * Управляет списком книг, состоянием загрузки и последним поисковым запросом.
 */
@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    /** Состояние экрана с флагами загрузки и ошибок */
    private val _state = mutableStateOf(BooksState())
    val state: State<BooksState> = _state

    /** Список книг */
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    /** Последний поисковый запрос */
    private var _lastQuery = MutableStateFlow("")
    val lastQuery: StateFlow<String> = _lastQuery

    init {
        searchBooks("а")
    }

    /** Обновляет последний поисковый запрос */
    fun updateLastQuery(query: String) {
        _lastQuery.value = query
    }

    /**
     * Выполняет поиск книг по запросу
     * @param query - поисковый запрос
     */
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

/**
 * Состояние домашнего экрана с книгами.
 * @param isLoading - индикатор загрузки
 * @param error - сообщение об ошибке
 */
data class BooksState(
    val isLoading: Boolean = false,
    val error: String? = null
)