package com.example.bookapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookapp.presentation.screens.DetailsScreen
import com.example.bookapp.presentation.screens.FavoriteScreen
import com.example.bookapp.presentation.screens.HomeScreen
import com.example.bookapp.presentation.screens.ProfileScreen
import com.example.bookapp.presentation.screens.SettingsScreen

/**
 * Функция BooksAppNavigation настраивает навигацию приложения
 * с использованием NavHostController и NavHost.
 *
 * @param navController - контроллер навигации
 * @param modifier - модификатор для NavHost
 */
@Composable
fun BooksAppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        // Домашний экран
        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        // Экран профиля
        composable(Screens.Profile.route) {
            ProfileScreen()
        }
        // Экран избранного
        composable(Screens.Favorite.route) {
            FavoriteScreen(
                navController = navController
            )
        }
        // Экран настроек
        composable(Screens.Settings.route) {
            SettingsScreen()
        }
        // Экран деталей книги, принимает аргумент bookId
        composable(
            Screens.Details.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
            DetailsScreen(
                bookId = bookId
            )

        }
    }
}