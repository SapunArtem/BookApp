package com.example.bookapp.domain.repository

interface LocalizationRepository {
    fun setLanguage(languageCode: String)

    fun getCurrentLanguage(): String
}