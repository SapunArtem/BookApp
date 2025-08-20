package com.example.bookapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения данных об избранных книгах.
 */
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: String,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val categories: String,
    val thumbnail: String?,
    val language: String?,
    val previewLink: String?,
    val infoLink: String?,
    val isEbook: Boolean,
    val saleability: String?,
    val addedAt: Long = System.currentTimeMillis()
)
