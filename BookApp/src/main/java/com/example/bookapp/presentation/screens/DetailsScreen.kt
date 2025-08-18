package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapp.presentation.components.ErrorMessage
import com.example.bookapp.presentation.components.details.BookDetailsContent
import com.example.bookapp.presentation.ui.theme.Orange
import com.example.bookapp.presentation.viewModel.BookDetailsViewModel

@Composable
fun DetailsScreen(
    bookId : String,
    viewModel: BookDetailsViewModel = hiltViewModel()
){
    val state by viewModel.state

    LaunchedEffect(bookId) {
            viewModel.loadDetails(bookId)
    }

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
                        viewModel.loadDetails(bookId)

                }

        }

        state.book != null -> {
            BookDetailsContent(
                book = state.book!!,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
