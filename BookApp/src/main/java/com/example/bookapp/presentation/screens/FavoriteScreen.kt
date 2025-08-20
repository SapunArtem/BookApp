package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapp.R
import com.example.bookapp.presentation.components.state.EmptyState
import com.example.bookapp.presentation.components.state.ErrorMessage
import com.example.bookapp.presentation.components.books.BookCard
import com.example.bookapp.presentation.navigation.Screens
import com.example.bookapp.presentation.viewModel.FavoriteUiState
import com.example.bookapp.presentation.viewModel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is FavoriteUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
            }
        }

        is FavoriteUiState.Error -> {
            val message = (state as FavoriteUiState.Error).message
            ErrorMessage(error = message) {
                viewModel.loadFavorites()
            }
        }

        is FavoriteUiState.Empty -> {
            EmptyState(stringResource(R.string.there_are_no_favorites))
        }

        is FavoriteUiState.Success -> {
            val favorites = (state as FavoriteUiState.Success).favorites
            LazyColumn(
                modifier = Modifier.testTag("FavoriteList")
            ) {
                items(favorites) { book ->
                    BookCard(
                        book = book,
                        onClick = {
                            navController.navigate(
                                Screens.Details.createRoute(book.id)
                            )
                        }
                    )
                }
            }
        }
    }
}

