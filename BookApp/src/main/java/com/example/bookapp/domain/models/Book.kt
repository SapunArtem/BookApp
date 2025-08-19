package com.example.bookapp.domain.models

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>,
    val thumbnail: String? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val isEbook: Boolean = false,
    val saleability: String? = null
)
