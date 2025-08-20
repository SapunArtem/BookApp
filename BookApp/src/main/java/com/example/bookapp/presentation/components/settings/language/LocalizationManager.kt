package com.example.bookapp.presentation.components.settings.language

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.Locale

/**
 * Менеджер локализации приложения. Отвечает за установку, получение и сохранение текущего языка.
 */
object LocalizationManager {
    private const val PREF_LANGUAGE = "pref_language"
    private const val PREF_NAME = "app_settings"

    /**
     * Устанавливает язык приложения и сохраняет его в SharedPreferences.
     *
     * @param context Контекст приложения.
     * @param languageCode Код языка (например, "en", "ru").
     */
    fun setLocale(context: Context, languageCode: String) {
        persistLanguage(context, languageCode)
        updateResources(context, languageCode)
    }

    /**
     * Получает текущий выбранный язык.
     *
     * @param context Контекст приложения.
     * @return Код текущего языка.
     */
    fun getCurrentLanguage(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(PREF_LANGUAGE, Locale.getDefault().language) ?: "en"
    }

    // Обновляет конфигурацию ресурсов с новым языком
    private fun updateResources(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
            configuration.setLocales(LocaleList(locale))
        } else {
            configuration.locale = locale
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }


    // Сохраняет выбранный язык в SharedPreferences
    private fun persistLanguage(context: Context, languageCode: String) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().putString(PREF_LANGUAGE, languageCode).apply()
    }


    /**
     * Оборачивает контекст с нужной локалью для использования в Compose или ресурсах.
     *
     * @param context Исходный контекст.
     * @return Контекст с примененной локалью.
     */
    fun wrapContext(context: Context): Context {
        val language = getCurrentLanguage(context)
        val locale = Locale(language)
        Locale.setDefault(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val configuration = context.resources.configuration
            configuration.setLocale(locale)
            configuration.setLocales(LocaleList(locale))
            context.createConfigurationContext(configuration)
        } else {
            context
        }
    }

}