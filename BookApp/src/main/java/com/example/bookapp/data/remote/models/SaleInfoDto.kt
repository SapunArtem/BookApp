package com.example.bookapp.data.remote.models

/**
 * DTO с информацией о продаже книги.
 */
data class SaleInfoDto(
    val country: String?,
    val saleability: String?,
    val isEbook: Boolean?
)
