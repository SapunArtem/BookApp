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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.bookapp.R
import com.example.bookapp.presentation.components.EmptyState
import com.example.bookapp.presentation.components.ErrorMessage
import com.example.bookapp.presentation.components.details.BookDetailsContent
import com.example.bookapp.presentation.ui.theme.Orange
import com.example.bookapp.presentation.viewModel.BookDetailsViewModel
import com.example.bookapp.presentation.viewModel.FavoriteViewModel

@Composable
fun DetailsScreen(
    bookId : String,
    viewModel: BookDetailsViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val bookDetails by viewModel.bookDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(bookId) {
        viewModel.loadDetails(bookId)
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Orange)
            }
        }

        error != null -> {
            ErrorMessage(error!!) {
                viewModel.loadDetails(bookId)

            }

        }

        bookDetails == null -> EmptyState(text = stringResource(R.string.movie_etails_not_available))

        else -> {
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
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
