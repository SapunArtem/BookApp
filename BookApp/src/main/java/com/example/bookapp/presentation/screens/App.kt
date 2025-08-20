package com.example.bookapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.presentation.components.bottomBar.BottomBar
import com.example.bookapp.presentation.components.topBar.TopBar
import com.example.bookapp.presentation.navigation.BooksAppNavigation
import com.example.bookapp.presentation.navigation.Screens
import com.example.bookapp.presentation.viewModel.SettingsViewModel

/**
 * Основная Composable-функция приложения.
 * Создает NavController, получает текущий контекст и настройки из SettingsViewModel.
 * Реагирует на изменения темы и языка и запускает MainAppContent.
 */
@Composable
fun App() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel: SettingsViewModel = hiltViewModel()
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()


    // Обновление контекста для локализации при старте
    LaunchedEffect(context) {
        viewModel.updateContext(context)
    }

    // Перестройка UI при изменении языка или темы
    key(currentLanguage, isDarkTheme) {
        MainAppContent(
            navController = navController,

            )

    }

}

/**
 * Главный контент приложения с Scaffold.
 * Scaffold включает TopBar, BottomBar и навигацию между экранами.
 *
 * @param navController NavController для управления навигацией между экранами.
 */
@SuppressLint("SuspiciousIndentation")
@Composable
fun MainAppContent(
    navController: NavHostController,
) {
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
        content = { padding ->
            BooksAppNavigation(
                modifier = Modifier
                    .padding(padding),
                navController = navController
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}
