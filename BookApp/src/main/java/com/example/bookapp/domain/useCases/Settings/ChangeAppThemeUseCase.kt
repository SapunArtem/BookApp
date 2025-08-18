package com.example.bookapp.domain.useCases.Settings

import com.example.bookapp.domain.repository.ThemeRepository
import javax.inject.Inject

class ChangeAppThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    /**
     * Устанавливает тему приложения.
     *
     * @param isDark true — тёмная тема, false — светлая тема.
     */
    operator fun invoke(isDark: Boolean) {
        themeRepository.setTheme(isDark)
    }
}