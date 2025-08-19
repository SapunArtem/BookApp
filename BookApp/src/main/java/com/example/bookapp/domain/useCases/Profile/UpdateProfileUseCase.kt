package com.example.bookapp.domain.useCases.Profile

import com.example.bookapp.domain.models.Profile
import com.example.bookapp.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profile: Profile) = repository.updateProfile(profile)
}