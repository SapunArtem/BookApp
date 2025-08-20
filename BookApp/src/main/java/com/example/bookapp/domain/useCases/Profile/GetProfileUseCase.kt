package com.example.bookapp.domain.useCases.Profile

import com.example.bookapp.domain.models.Profile
import com.example.bookapp.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case для получения профиля пользователя.
 */
class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<Profile?> = repository.getProfile()
}