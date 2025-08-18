package com.example.bookapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookapp.data.local.Converts
import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converts::class)
abstract class FavoriteDataBase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile private var instance: FavoriteDataBase? = null

        fun getInstance(context: Context): FavoriteDataBase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDataBase::class.java,
                    "favorites.db"
                ).build().also { instance = it }
            }
        }
    }
}