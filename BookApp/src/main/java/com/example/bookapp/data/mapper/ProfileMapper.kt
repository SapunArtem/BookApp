package com.example.bookapp.data.mapper

import com.example.bookapp.data.local.entity.ProfileEntity
import com.example.bookapp.domain.models.Profile

fun ProfileEntity.toDomain(): Profile = Profile(
    id = id,
    name = name,
    email = email,
    avatarUrl = avatarUrl
)

fun Profile.toEntity(): ProfileEntity = ProfileEntity(
    id = id,
    name = name,
    email = email,
    avatarUrl = avatarUrl
)