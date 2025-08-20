package com.example.bookapp.data.mapper

import com.example.bookapp.data.local.entity.FavoriteEntity
import com.example.bookapp.domain.models.Book
import javax.inject.Inject

/**
 * Маппер для преобразования между FavoriteEntity и Book.
 */
class FavoriteMapper @Inject constructor() {
    fun mapToDomain(entity: FavoriteEntity): Book {
        return Book(
            id = entity.id,
            title = entity.title,
            authors = entity.authors.split("|"),
            publisher = entity.publisher,
            publishedDate = entity.publishedDate,
            description = entity.description,
            pageCount = entity.pageCount,
            categories = entity.categories.split("|"),
            thumbnail = entity.thumbnail,
            language = entity.language,
            previewLink = entity.previewLink,
            infoLink = entity.infoLink,
            isEbook = entity.isEbook,
            saleability = entity.saleability
        )
    }

    fun mapToEntity(book: Book): FavoriteEntity {
        return FavoriteEntity(
            id = book.id,
            title = book.title,
            authors = book.authors.joinToString("|"),
            publisher = book.publisher,
            publishedDate = book.publishedDate,
            description = book.description,
            pageCount = book.pageCount,
            categories = book.categories.joinToString("|") ,
            thumbnail = book.thumbnail,
            language = book.language,
            previewLink = book.previewLink,
            infoLink = book.infoLink,
            isEbook = book.isEbook,
            saleability = book.saleability
        )
    }


}