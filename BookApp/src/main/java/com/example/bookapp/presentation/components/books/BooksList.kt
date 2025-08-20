package com.example.bookapp.presentation.components.books

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.bookapp.domain.models.Book
import com.example.bookapp.presentation.ui.theme.Orange

@Composable
fun BooksList(
    books: List<Book>,
    onBookClick: (String) -> Unit,
    isLoading: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .testTag("BookList")
    ) {
        items(books) { book ->
            BookCard(
                book = book,
                onClick = { onBookClick(book.id) }
            )
        }
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Orange)
                }
            }
        }
    }
}