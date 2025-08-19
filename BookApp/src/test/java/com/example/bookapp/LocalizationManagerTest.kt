package com.example.bookapp

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.bookapp.presentation.components.settings.language.LocalizationManager
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LocalizationManagerTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testContext = mockk<Context>()
    private val sharedPreferences = mockk<SharedPreferences>()
    private val editor = mockk<SharedPreferences.Editor>()

    @Before
    fun setup() {
        every { testContext.getSharedPreferences(any(), any()) } returns sharedPreferences
        every { sharedPreferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor
        every { editor.apply() } just Runs
        every { sharedPreferences.getString(any(), any()) } returns "en"
    }

    @Test
    fun `setLocale should persist language and update resources`() {
        val languageCode = "ru"
        val resources = mockk<Resources>()
        val configuration = mockk<Configuration>(relaxed = true)
        val displayMetrics = mockk<android.util.DisplayMetrics>(relaxed = true)

        every { testContext.resources } returns resources
        every { resources.configuration } returns configuration
        every { resources.displayMetrics } returns displayMetrics
        every { resources.updateConfiguration(any(), any()) } just Runs

        LocalizationManager.setLocale(testContext, languageCode)

        verify { editor.putString("pref_language", languageCode) }
        verify { editor.apply() }
        verify { resources.updateConfiguration(any(), displayMetrics) }
    }

    @Test
    fun `getCurrentLanguage should return saved language`() {
        val expectedLanguage = "ru"
        every { sharedPreferences.getString("pref_language", any()) } returns expectedLanguage

        val result = LocalizationManager.getCurrentLanguage(testContext)

        assertThat(result).isEqualTo(expectedLanguage)
    }

    @Test
    fun `wrapContext applies saved locale`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        LocalizationManager.setLocale(context, "ru")

        val wrapped = LocalizationManager.wrapContext(context)
        val config = wrapped.resources.configuration

        val language = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            config.locales[0].language
        } else {
            @Suppress("DEPRECATION")
            config.locale.language
        }

        assertThat(language).isEqualTo("ru")
    }
}