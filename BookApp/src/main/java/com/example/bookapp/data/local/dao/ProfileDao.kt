package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей "profile".
 * Хранит данные профиля пользователя.
 */
@Dao
interface ProfileDao {
    /**
     * Получить текущий профиль пользователя.
     */
    @Query("SELECT * FROM profile LIMIT 1")
    fun getProfile(): Flow<ProfileEntity?>

    /**
     * Добавить или обновить профиль пользователя.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: ProfileEntity)
}