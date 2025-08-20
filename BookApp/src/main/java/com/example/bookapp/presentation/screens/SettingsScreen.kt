package com.example.bookapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.bookapp.presentation.components.settings.SettingsData
import com.example.bookapp.presentation.components.settings.language.LanguageSettingSection
import com.example.bookapp.presentation.components.settings.theme.ThemeSettingsSection
import com.example.bookapp.presentation.viewModel.SettingsViewModel

/**
 * Экран настроек приложения.
 *
 * Позволяет пользователю менять тему и язык приложения.
 *
 * @param viewModel ViewModel для работы с настройками.
 */
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Раздел выбора темы
            ThemeSettingsSection(
                items = SettingsData.theme,
                isDarkTheme = isDarkTheme,
                setTheme = viewModel::setTheme
            )
        }
        item {
            // Раздел выбора языка
            LanguageSettingSection(
                items = SettingsData.language,
                currentLanguage = currentLanguage,
                setLanguage = viewModel::setLanguage
            )
        }
    }
}