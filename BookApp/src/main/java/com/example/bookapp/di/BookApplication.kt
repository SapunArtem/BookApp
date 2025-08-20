package com.example.bookapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Класс приложения, используется для инициализации Hilt.
 */
@HiltAndroidApp
class BookApplication : Application()