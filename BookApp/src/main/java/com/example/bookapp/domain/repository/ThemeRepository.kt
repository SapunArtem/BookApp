package com.example.bookapp.domain.repository

interface ThemeRepository {
    fun setTheme(isDark: Boolean)


    fun getCurrentTheme(): Boolean
}