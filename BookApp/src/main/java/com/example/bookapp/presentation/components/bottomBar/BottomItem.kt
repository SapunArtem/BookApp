package com.example.bookapp.presentation.components.bottomBar

import androidx.compose.ui.graphics.vector.ImageVector

// Модель данных для элементов нижней навигации
data class BottomItem(
    val title: Int,
    val icon: ImageVector,
    val route: String
)
