package com.example.bookapp.data.local.repository

import com.example.bookapp.domain.repository.ThemeRepository
import com.example.bookapp.presentation.ui.theme.AppTheme
import javax.inject.Inject

/**
 * Репозиторий для управления темой приложения (светлая/тёмная).
 */
class ThemeRepositoryImpl @Inject constructor() : ThemeRepository {

    override fun setTheme(isDark: Boolean) {
        AppTheme.isDarkTheme = isDark
    }

    override fun getCurrentTheme(): Boolean {
        return AppTheme.isDarkTheme
    }
}