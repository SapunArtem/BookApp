package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapp.R
import com.example.bookapp.presentation.components.EmptyState
import com.example.bookapp.presentation.components.ErrorMessage
import com.example.bookapp.presentation.components.books.BooksList
import com.example.bookapp.presentation.components.searchBar.SearchMovieBar
import com.example.bookapp.presentation.navigation.Screens
import com.example.bookapp.presentation.ui.theme.Orange
import com.example.bookapp.presentation.viewModel.BookViewModel
import kotlinx.coroutines.delay

/**
 * Главный экран приложения со списком фильмов и поиском.
 *
 * @param navController Контроллер навигации
 * @param viewModel ViewModel для работы с фильмами
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()

) {
    val books by viewModel.books.collectAsState()
    val state by viewModel.state
    val lastQuery by viewModel.lastQuery.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(state.isLoading) {
        if (!state.isLoading) {
            delay(500)
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.searchBooks("a")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchMovieBar(
                viewModel = viewModel,
                onSearch = { query ->
                    viewModel.searchBooks(query)
                }

            )
            Spacer(modifier = Modifier.height(30.dp))
            when {
                state.isLoading && books.isEmpty() -> {
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
                        viewModel.searchBooks(lastQuery)
                    }
                }

                books.isEmpty() -> {
                    EmptyState(stringResource(R.string.movies_not_found))
                }

                else -> {
                    BooksList(
                        books = books,
                        onBookClick = { bookId ->
                            navController.navigate(Screens.Details.createRoute(bookId))
                        },
                        isLoading = state.isLoading,
                    )
                }
            }
        }
    }

}