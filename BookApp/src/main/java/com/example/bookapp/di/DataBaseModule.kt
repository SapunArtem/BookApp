package com.example.bookapp.di

import android.content.Context
import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.dao.ProfileDao
import com.example.bookapp.data.local.database.AppDataBase
import com.example.bookapp.data.mapper.FavoriteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль для предоставления базы данных и DAO.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    /**
     * Предоставление экземпляра базы данных.
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    /**
     * Предоставление DAO для работы с избранным.
     */
    @Provides
    fun provideFavoriteDao(database: AppDataBase): FavoriteDao {
        return database.favoriteDao()
    }

    /**
     * Предоставление DAO для работы с профилем.
     */
    @Provides
    fun provideProfileDao(database: AppDataBase): ProfileDao {
        return database.profileDao()
    }

    /**
     * Предоставление маппера для работы с избранным.
     */
    @Provides
    fun provideFavoriteMapper(): FavoriteMapper {
        return FavoriteMapper()
    }
}