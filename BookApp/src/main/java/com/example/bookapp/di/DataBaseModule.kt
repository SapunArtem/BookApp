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

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Provides
    fun provideFavoriteDao(database: AppDataBase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun provideProfileDao(database: AppDataBase): ProfileDao {
        return database.profileDao()
    }


    @Provides
    fun provideFavoriteMapper(): FavoriteMapper {
        return FavoriteMapper()
    }
}