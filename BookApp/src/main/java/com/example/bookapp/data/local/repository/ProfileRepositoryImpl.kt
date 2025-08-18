package com.example.bookapp.data.local.repository

import com.example.bookapp.data.local.dao.ProfileDao
import com.example.bookapp.data.mapper.toDomain
import com.example.bookapp.data.mapper.toEntity
import com.example.bookapp.domain.models.Profile
import com.example.bookapp.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: ProfileDao
) : ProfileRepository {
    override fun getProfile(): Flow<Profile?> {
        return dao.getProfile().map { it?.toDomain() }
    }

    override suspend fun updateProfile(profile: Profile) {
        dao.insert(profile.toEntity())
    }
}