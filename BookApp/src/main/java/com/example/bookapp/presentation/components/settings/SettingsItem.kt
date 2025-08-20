package com.example.bookapp.presentation.components.settings

/**
 * Сеалб класс SettingsItem используется для определения
 * различных типов элементов настроек.
 */
sealed class SettingsItem {

    /**
     * Класс для настроек темы приложения
     * @param titleRes - ресурс строки с названием темы
     * @param isDarkTheme - true, если тема тёмная
     * @param imageRes - ресурс изображения темы
     */
    data class ThemeSettings(
        val titleRes: Int,
        val isDarkTheme: Boolean,
        val imageRes: Int
    ) : SettingsItem()


    /**
     * Класс для настроек языка приложения
     * @param titleRes - ресурс строки с названием языка
     * @param languageCode - код языка (например, "en", "ru")
     * @param flagRes - ресурс изображения флага
     */
    data class LanguageSettings(
        val titleRes: Int,
        val languageCode: String,
        val flagRes: Int
    ) : SettingsItem()


    /**
     * Класс для заголовков секций настроек
     * @param titleRes - ресурс строки с заголовком
     */
    data class Header(val titleRes: Int) : SettingsItem()
}