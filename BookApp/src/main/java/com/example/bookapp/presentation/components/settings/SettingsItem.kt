package com.example.bookapp.presentation.components.settings

sealed class SettingsItem {

    data class ThemeSettings(
        val titleRes: Int,
        val isDarkTheme: Boolean,
        val imageRes: Int
    ) : SettingsItem()


    data class LanguageSettings(
        val titleRes: Int,
        val languageCode: String,
        val flagRes: Int
    ) : SettingsItem()


    data class Header(val titleRes: Int) : SettingsItem()
}