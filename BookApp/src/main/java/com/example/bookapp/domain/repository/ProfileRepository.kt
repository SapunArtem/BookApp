package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Profile
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для работы с профилем пользователя.
 */
interface ProfileRepository {
    /**
     * Получение профиля пользователя в виде потока.
     */
    fun getProfile(): Flow<Profile?>

    /**
     * Обновление данных профиля пользователя.
     */
    suspend fun updateProfile(profile: Profile)
}