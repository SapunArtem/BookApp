package com.example.bookapp.data.local.repository

import android.content.Context
import com.example.bookapp.domain.repository.LocalizationRepository
import com.example.bookapp.presentation.components.settings.language.LocalizationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocalizationRepositoryImpl @Inject constructor(
    @ApplicationContext private var context: Context,
    private val localizationManager: LocalizationManager
) : LocalizationRepository {

    override fun setLanguage(languageCode: String) {
        localizationManager.setLocale(context, languageCode)
    }

    override fun getCurrentLanguage(): String {
        return localizationManager.getCurrentLanguage(context)
    }

    fun updateContext(newContext: Context) {
        context = newContext
    }
}