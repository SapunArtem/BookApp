package com.example.bookapp.data.remote.models

data class BookDto(
    val kind: String?,
    val id: String,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: BookInfoDto?,
    val saleInfo: SaleInfoDto?,
    val accessInfo: AccessInfoDto?,
    val searchInfo: SearchInfoDto?
)
