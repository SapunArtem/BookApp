package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapp.presentation.components.EmptyState
import com.example.bookapp.presentation.components.ErrorMessage
import com.example.bookapp.presentation.components.books.BookCard
import com.example.bookapp.presentation.components.favorites.EmptyFavorites
import com.example.bookapp.presentation.navigation.Screens
import com.example.bookapp.presentation.ui.theme.Orange
import com.example.bookapp.presentation.viewModel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
){
    val state by viewModel.state

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Orange)
            }
        }

        state.error != null -> {
            ErrorMessage(state.error!!) {
                viewModel.loadFavorites()
            }

        }

        state.books.isEmpty() -> {
            EmptyFavorites()
        }

        else -> {
            LazyColumn {
                items(state.books) { book ->
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

