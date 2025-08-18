package com.example.bookapp.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.repository.LocalizationRepositoryImpl
import com.example.bookapp.domain.repository.LocalizationRepository
import com.example.bookapp.domain.repository.ThemeRepository
import com.example.bookapp.domain.useCases.Settings.ChangeAppLanguageUseCase
import com.example.bookapp.domain.useCases.Settings.ChangeAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val changeLanguage: ChangeAppLanguageUseCase,
    private val localizationRepository: LocalizationRepository,
    private val changeTheme: ChangeAppThemeUseCase,
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _currentLanguage = MutableStateFlow(localizationRepository.getCurrentLanguage())
    val currentLanguage: StateFlow<String> = _currentLanguage

    private val _isDarkTheme = MutableStateFlow(themeRepository.getCurrentTheme())
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    fun setLanguage(languageCode: String) {
        viewModelScope.launch {
            changeLanguage(languageCode)
            _currentLanguage.value = localizationRepository.getCurrentLanguage()
        }
    }

    fun updateContext(newContext: Context) {
        if (localizationRepository is LocalizationRepositoryImpl) {
            localizationRepository.updateContext(newContext)
            _currentLanguage.value = localizationRepository.getCurrentLanguage()
        }
    }

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            changeTheme(isDark)
            _isDarkTheme.value = themeRepository.getCurrentTheme()
        }
    }
}