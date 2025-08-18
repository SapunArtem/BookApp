package com.example.bookapp.data.remote.models

data class AccessInfoDto(
    val country: String?,
    val viewability: String?,
    val embeddable: Boolean?,
    val publicDomain: Boolean?,
    val textToSpeechPermission: String?,
    val webReaderLink: String?
)
