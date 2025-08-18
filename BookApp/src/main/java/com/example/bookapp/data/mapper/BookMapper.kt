package com.example.bookapp.data.mapper

import com.example.bookapp.data.remote.models.BookDto
import com.example.bookapp.domain.models.Book

fun BookDto.toDomain(): Book {
    val info = this.volumeInfo
    val sale = this.saleInfo
    return Book(
        id = this.id,
        title = info?.title ?: "No title",
        authors = info?.authors ?: emptyList(),
        publisher = info?.publisher,
        publishedDate = info?.publishedDate,
        description = info?.description,
        pageCount = info?.pageCount,
        categories = info?.categories ?: emptyList(),
        thumbnail = info?.imageLinks?.thumbnail
            ?.replace("http://", "https://")
            ?: info?.imageLinks?.smallThumbnail?.replace("http://", "https://"),
        language = info?.language,
        previewLink = info?.previewLink,
        infoLink = info?.infoLink,
        isEbook = sale?.isEbook ?: false,
        saleability = sale?.saleability
    )
}