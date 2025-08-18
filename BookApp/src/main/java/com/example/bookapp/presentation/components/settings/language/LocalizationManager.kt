package com.example.bookapp.presentation.components.settings.language

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.Locale

object LocalizationManager {
    private const val PREF_LANGUAGE = "pref_language"
    private const val PREF_NAME = "app_settings"

    fun setLocale(context: Context, languageCode: String) {
        persistLanguage(context, languageCode)
        updateResources(context, languageCode)
    }

    fun getCurrentLanguage(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(PREF_LANGUAGE, Locale.getDefault().language) ?: "en"
    }

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


    private fun persistLanguage(context: Context, languageCode: String) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().putString(PREF_LANGUAGE, languageCode).apply()
    }


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