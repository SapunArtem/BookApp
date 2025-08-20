package com.example.bookapp.domain.repository

/**
 * Репозиторий для работы с темой приложения (светлая/тёмная).
 */
interface ThemeRepository {
    /**
     * Установка темы приложения.
     *
     * @param isDark true - тёмная тема, false - светлая
     */
    fun setTheme(isDark: Boolean)

    /**
     * Получение текущей темы приложения.
     *
     * @return true если тёмная тема, false если светлая
     */
    fun getCurrentTheme(): Boolean
}