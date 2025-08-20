package com.example.bookapp.domain.models

/**
 * Модель данных профиля пользователя.
 *
 * @property id уникальный идентификатор пользователя
 * @property name имя пользователя
 * @property email электронная почта пользователя
 * @property avatarUrl ссылка на аватар (опционально)
 */
data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String? = null
)
