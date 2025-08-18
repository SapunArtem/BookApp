package com.example.bookapp.presentation.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bookapp.R
import com.example.bookapp.domain.models.Book

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookDetailsContent(
    book: Book,
    onPreview: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                model = book.thumbnail,
                contentDescription = book.title,
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = book.authors.joinToString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }


        HorizontalDivider()


        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = book.description ?: stringResource(R.string.null_description),
            style = MaterialTheme.typography.bodyMedium
        )


        HorizontalDivider()

        val metaItems = listOfNotNull(
            book.publishedDate?.let { "${stringResource(R.string.published)}: $it" },
            book.publisher?.let { "${stringResource(R.string.publisher)}: $it" },
            book.pageCount?.let { "${stringResource(R.string.pages)}: $it" }
        )

        metaItems.forEach { item ->
            Text(text = item)
        }

        book.previewLink?.let { link ->
            Button(
                onClick = { onPreview() },
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = stringResource(R.string.read_preview))
            }
        }
    }
}

