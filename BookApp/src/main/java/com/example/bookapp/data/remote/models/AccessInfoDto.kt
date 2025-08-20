package com.example.bookapp.data.remote.models

/**
 * DTO с информацией о доступе к книге.
 */
data class AccessInfoDto(
    val country: String?,
    val viewability: String?,
    val embeddable: Boolean?,
    val publicDomain: Boolean?,
    val textToSpeechPermission: String?,
    val webReaderLink: String?
)
