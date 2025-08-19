package com.example.bookapp.viewModel

import android.content.Context
import com.example.bookapp.BaseTest
import com.example.bookapp.data.local.repository.LocalizationRepositoryImpl
import com.example.bookapp.domain.repository.LocalizationRepository
import com.example.bookapp.domain.repository.ThemeRepository
import com.example.bookapp.domain.useCases.Settings.ChangeAppLanguageUseCase
import com.example.bookapp.domain.useCases.Settings.ChangeAppThemeUseCase
import com.example.bookapp.presentation.viewModel.SettingsViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class SettingsViewModelTest : BaseTest() {
    private lateinit var viewModel: SettingsViewModel
    private lateinit var changeLanguage: ChangeAppLanguageUseCase
    private lateinit var localizationRepository: LocalizationRepository
    private lateinit var changeTheme: ChangeAppThemeUseCase
    private lateinit var themeRepository: ThemeRepository

    @Before
    fun setupViewModel() {
        changeLanguage = mockk()
        localizationRepository = mockk()
        changeTheme = mockk()
        themeRepository = mockk()

        coEvery { localizationRepository.getCurrentLanguage() } returns "en"
        every { themeRepository.getCurrentTheme() } returns false

        viewModel = SettingsViewModel(changeLanguage, localizationRepository, changeTheme, themeRepository)
    }

    @Test
    fun `setLanguage should call use case and update currentLanguage`() = runTest {
        val languageCode = "ru"
        coEvery { changeLanguage(languageCode) } just Runs
        coEvery { localizationRepository.getCurrentLanguage() } returns languageCode

        viewModel.setLanguage(languageCode)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { changeLanguage(languageCode) }
        assertThat(viewModel.currentLanguage.value).isEqualTo(languageCode)
    }

    @Test
    fun `setTheme should call use case and update isDarkTheme`() = runTest {
        val isDark = true
        coEvery { changeTheme(isDark) } just Runs
        every { themeRepository.getCurrentTheme() } returns isDark

        viewModel.setTheme(isDark)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { changeTheme(isDark) }
        assertThat(viewModel.isDarkTheme.value).isTrue()
    }

    @Test
    fun `updateContext should update context in repository`() {
        val context = mockk<Context>()
        val localizationRepo = mockk<LocalizationRepositoryImpl>()
        every { localizationRepo.updateContext(context) } just Runs
        every { localizationRepo.getCurrentLanguage() } returns "ru"

        viewModel = SettingsViewModel(
            changeLanguage,
            localizationRepo,
            changeTheme,
            themeRepository
        )

        viewModel.updateContext(context)

        verify { localizationRepo.updateContext(context) }
        assertThat(viewModel.currentLanguage.value).isEqualTo("ru")
    }
}