package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.bookapp.R
import com.example.bookapp.presentation.components.state.EmptyState
import com.example.bookapp.presentation.components.state.ErrorMessage
import com.example.bookapp.presentation.components.details.BookDetailsContent
import com.example.bookapp.presentation.ui.theme.Orange
import com.example.bookapp.presentation.viewModel.BookDetailsViewModel
import com.example.bookapp.presentation.viewModel.FavoriteViewModel

/**
 * Экран с деталями книги.
 *
 * @param bookId ID книги для загрузки деталей.
 * @param viewModel ViewModel для загрузки данных книги.
 * @param favoriteViewModel ViewModel для управления избранными книгами.
 */
@Composable
fun DetailsScreen(
    bookId: String,
    viewModel: BookDetailsViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val bookDetails by viewModel.bookDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val context = LocalContext.current

    // Загрузка деталей книги при изменении bookId
    LaunchedEffect(bookId) {
        viewModel.loadDetails(bookId)
    }

    when {
        isLoading -> {
            // Отображение индикатора загрузки
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Orange)
            }
        }

        error != null -> {
            // Отображение ошибки и возможность повторной загрузки
            ErrorMessage(error!!) {
                viewModel.loadDetails(bookId)

            }

        }

        bookDetails == null -> // Если книга не найдена
             EmptyState(text = stringResource(R.string.movie_etails_not_available))

        else -> {
            // Отображение информации о книге
            bookDetails?.let { item ->
                val isFavorite by favoriteViewModel
                    .isFavoriteFlow(item.id)
                    .collectAsState(false)
                BookDetailsContent(
                    book = item,
                    onFavoriteClick = {
                        favoriteViewModel.toggleFavorite(
                            book = item,
                            isFavorite = isFavorite
                        )
                    },
                    isFavorite = isFavorite,
                    modifier = Modifier.padding(16.dp),
                    onPreview = {
                        viewModel.openInBrowser(
                            context,
                            item.previewLink ?: "No preview"
                        )
                    }
                )
            }
        }
    }
}
