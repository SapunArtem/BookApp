package com.example.bookapp.presentation.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object Favorite : Screens("favorite_screen")
    object Profile : Screens("profile_screen")
    object Details : Screens("details_screen/{bookId}") {

        fun createRoute(bookId: String) = "details_screen/$bookId"
    }
}