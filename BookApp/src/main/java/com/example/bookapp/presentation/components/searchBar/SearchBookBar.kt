package com.example.bookapp.presentation.components.searchBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.bookapp.R
import com.example.bookapp.presentation.ui.theme.Orange
import com.example.bookapp.presentation.viewModel.BookViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieBar(
    viewModel: BookViewModel,
    onSearch: (String) -> Unit
) {
    val lastQuery by viewModel.lastQuery.collectAsState()
    var query by remember { mutableStateOf(lastQuery) }
    var active by remember { mutableStateOf(false) }


    LaunchedEffect(query) {
        if (query != lastQuery) {
            viewModel.updateLastQuery(query)
        }
    }
    SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
    inputField = {
        SearchBarDefaults.InputField(
            query = query,
            onQueryChange =  { newQuery ->
                query = newQuery
            },
            onSearch = {
                active = false
                if (query.isNotBlank()) {
                    onSearch(query)
                }
            },
            expanded = active,
            onExpandedChange = { active = it },
            placeholder = {
                Text(
                    text = "${stringResource(R.string.search)}...",
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                if (active && query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            },
            colors = SearchBarDefaults.inputFieldColors(
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.secondary
            )

        )
    },
    expanded = active,
    onExpandedChange = { active = it },
    colors = SearchBarDefaults.colors(
        containerColor = MaterialTheme.colorScheme.primary
    )

    ) { }

}