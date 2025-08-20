package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей "favorites".
 * Позволяет добавлять, удалять и проверять избранные книги.
 */
@Dao
interface FavoriteDao {
    /**
     * Получить все избранные книги в виде Flow.
     */
    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoriteEntity>>

    /**
     * Добавить книгу в избранное.
     * Если книга уже существует, она будет заменена.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    /**
     * Удалить книгу из избранного.
     */
    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    /**
     * Проверить, находится ли книга в избранном.
     */
    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE id = :bookId)")
    fun isFavorite(bookId: String): Flow<Boolean>
}