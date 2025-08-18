package com.example.bookapp.domain.repository

import com.example.bookapp.domain.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(): Flow<Profile?>
    suspend fun updateProfile(profile: Profile)
}