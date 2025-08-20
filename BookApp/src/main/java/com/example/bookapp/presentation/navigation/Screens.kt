package com.example.bookapp.presentation.navigation

/**
 * Сеалб класс Screens содержит все маршруты для навигации
 */
sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object Favorite : Screens("favorite_screen")
    object Settings : Screens("settings_screen")
    object Profile : Screens("profile_screen")
    object Details : Screens("details_screen/{bookId}") {
        /** Функция для создания маршрута к деталям книги с конкретным ID */
        fun createRoute(bookId: String) = "details_screen/$bookId"
    }
}