package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.presentation.components.bottomBar.BottomBar
import com.example.bookapp.presentation.components.topBar.TopBar
import com.example.bookapp.presentation.navigation.BooksAppNavigation
import com.example.bookapp.presentation.navigation.Screens

@Composable
fun App(){
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                showBackButton = currentRoute != Screens.Home.route,
                showTitle = currentRoute != Screens.Details.route
            )

        },
        bottomBar = {
            BottomBar(
                navController = navController,
                currentRoute = currentRoute
            )

        },
        content = {padding ->
            BooksAppNavigation(
                modifier = Modifier
                    .padding(padding),
                navController = navController
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}