package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY addedAt DESC")
    suspend fun getAll(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :bookId")
    suspend fun deleteById(bookId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :bookId LIMIT 1)")
    suspend fun exists(bookId: String): Boolean

    @Query("SELECT * FROM favorites WHERE id = :bookId LIMIT 1")
    suspend fun getById(bookId: String): FavoriteEntity?
}