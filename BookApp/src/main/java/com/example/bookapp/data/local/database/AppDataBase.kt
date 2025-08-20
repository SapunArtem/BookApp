package com.example.bookapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookapp.data.local.Converts
import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.dao.ProfileDao
import com.example.bookapp.data.local.entity.FavoriteEntity
import com.example.bookapp.data.local.entity.ProfileEntity

/**
 * Главная база данных приложения.
 * Содержит таблицы: избранное (favorites) и профиль (profile).
 */
@Database(
    entities = [FavoriteEntity::class, ProfileEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converts::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        /**
         * Получение единственного экземпляра базы данных (Singleton).
         */
        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}