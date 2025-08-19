package com.example.bookapp.domain.models

data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String? = null
)
