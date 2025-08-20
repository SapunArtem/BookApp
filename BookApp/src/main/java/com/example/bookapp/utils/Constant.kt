package com.example.bookapp.utils

import com.example.bookapp.BuildConfig

/**
 * Класс Constant содержит константы приложения, такие как BASE_URL и API_KEY
 */
class Constant {
    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/" // Базовый URL для API книг
        val API_KEY = BuildConfig.BOOKS_API_KEY                     // Ключ API из BuildConfig
    }
}