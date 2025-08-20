package com.example.bookapp.domain.repository

/**
 * Репозиторий для работы с локализацией приложения.
 */
interface LocalizationRepository {
    /**
     * Установка языка приложения.
     *
     * @param languageCode код языка (например, "ru" или "en")
     */
    fun setLanguage(languageCode: String)

    /**
     * Получение текущего языка приложения.
     *
     * @return код текущего языка
     */
    fun getCurrentLanguage(): String
}