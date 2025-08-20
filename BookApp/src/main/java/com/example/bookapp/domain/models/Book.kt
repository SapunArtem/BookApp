package com.example.bookapp.domain.models

/**
 * Модель данных книги.
 *
 * @property id уникальный идентификатор книги
 * @property title название книги
 * @property authors список авторов книги
 * @property publisher издатель (опционально)
 * @property publishedDate дата публикации (опционально)
 * @property description описание книги (опционально)
 * @property pageCount количество страниц (опционально)
 * @property categories категории книги
 * @property thumbnail ссылка на изображение обложки (опционально)
 * @property language язык книги (опционально)
 * @property previewLink ссылка на превью книги (опционально)
 * @property infoLink ссылка на дополнительную информацию (опционально)
 * @property isEbook признак электронного формата книги
 * @property saleability информация о возможности покупки (опционально)
 */
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
