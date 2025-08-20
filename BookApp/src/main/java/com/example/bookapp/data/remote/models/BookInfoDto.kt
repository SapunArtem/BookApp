package com.example.bookapp.data.remote.models

/**
 * DTO с метаданными о книге (название, авторы, обложка и т.д.).
 */
data class BookInfoDto(
    val title: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val industryIdentifiers: List<IndustryIdentifierDto>?,
    val pageCount: Int?,
    val categories: List<String>?,
    val maturityRating: String?,
    val imageLinks: ImageLinksDto?,
    val language: String?,
    val previewLink: String?,
    val infoLink: String?,
    val canonicalVolumeLink: String?
)
