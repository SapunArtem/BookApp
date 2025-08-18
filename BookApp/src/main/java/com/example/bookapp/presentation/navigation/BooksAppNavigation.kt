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

@Composable
fun BooksAppNavigation(
    navController: NavHostController,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route){
            HomeScreen(
                navController = navController
            )
        }
        composable(Screens.Profile.route){
            ProfileScreen()
        }
        composable(Screens.Favorite.route){
            FavoriteScreen(
                navController = navController
            )
        }
        composable(
            Screens.Details.route,
            arguments = listOf(
                navArgument("bookId") {type = NavType.StringType}
            )
            ){backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
                DetailsScreen(
                    bookId = bookId
                )

            }
    }
}