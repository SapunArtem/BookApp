package com.example.bookapp.presentation.components.topBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.example.bookapp.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    showBackButton: Boolean = false,
    showTitle: Boolean = false
) {
    TopAppBar(
        title = {
            if (showTitle) {
                TopBarTitle()
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    modifier = Modifier.testTag("backBtn"),
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "backButton"
                    )
                }
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.testTag("settingsButton"),
                onClick = { navController.navigate(Screens.Settings.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "settingsButton"
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.secondary
        )
    )
}