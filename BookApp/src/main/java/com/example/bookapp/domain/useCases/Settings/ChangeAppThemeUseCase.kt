package com.example.bookapp.domain.useCases.Settings

import com.example.bookapp.domain.repository.ThemeRepository
import javax.inject.Inject

/**
 * Use case для смены темы приложения.
 */
class ChangeAppThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {

    operator fun invoke(isDark: Boolean) {
        themeRepository.setTheme(isDark)
    }
}