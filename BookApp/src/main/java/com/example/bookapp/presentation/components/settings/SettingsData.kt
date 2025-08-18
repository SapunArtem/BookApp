package com.example.bookapp.presentation.components.settings

import com.example.bookapp.R

object SettingsData {
    val theme = listOf(
        SettingsItem.ThemeSettings(
            titleRes = R.string.light_theme,
            isDarkTheme = false,
            imageRes = R.drawable.my_lightfon
        ),
        SettingsItem.ThemeSettings(
            titleRes = R.string.dark_theme,
            isDarkTheme = true,
            imageRes = R.drawable.my_darkfon
        ),
    )

    val language = listOf(
        SettingsItem.LanguageSettings(
            titleRes = R.string.english,
            languageCode = "en",
            flagRes = R.drawable.ic_flag_uk
        ),
        SettingsItem.LanguageSettings(
            titleRes = R.string.russian,
            languageCode = "ru",
            flagRes = R.drawable.ic_flag_russia
        )
    )

    // Заголовок секции "Тема"
    val themeHeader = SettingsItem.Header(R.string.app_theme)

    // Заголовок секции "Язык"
    val languageHeader = SettingsItem.Header(R.string.app_language)
}