package com.example.bookapp.domain.useCases.Settings

import com.example.bookapp.domain.repository.ThemeRepository
import javax.inject.Inject

class ChangeAppThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {

    operator fun invoke(isDark: Boolean) {
        themeRepository.setTheme(isDark)
    }
}