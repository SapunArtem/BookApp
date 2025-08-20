package com.example.bookapp.presentation.viewModel

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для отображения деталей книги.
 * Загружает информацию о книге по её ID и управляет состоянием загрузки и ошибок.
 */
@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBooksDetailsUseCase: GetBooksDetailsUseCase,
) : ViewModel() {

    /** Состояние с деталями книги. Null, если данные еще не загружены */
    private val _bookDetails = MutableStateFlow<Book?>(null)
    val bookDetails: StateFlow<Book?> = _bookDetails

    /** Флаг загрузки данных книги */
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    /** Сообщение об ошибке загрузки книги, null если ошибок нет */
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Загружает детали книги по ID.
     * @param bookId - идентификатор книги
     */
    fun loadDetails(bookId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            getBooksDetailsUseCase(bookId)
                .onSuccess {
                    _bookDetails.value = it
                    _error.value = null
                }
                .onFailure { e ->
                    _error.value = e.message ?: "Faild to load book details"
                    _bookDetails.value = null
                }
            _isLoading.value = false
        }


    }

    /**
     * Открывает ссылку в браузере.
     * @param context - контекст для запуска Intent
     * @param uri - ссылка для открытия
     */
    fun openInBrowser(context: Context, uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, uri.toUri())
        context.startActivity(intent)
    }
}
