package com.example.bookapp.di

import android.content.Context
import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.database.FavoriteDataBase
import com.example.bookapp.data.local.repository.FavoriteRepositoryImpl
import com.example.bookapp.data.mapper.FavoriteMapper
import com.example.bookapp.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FavoriteDataBase {
        return FavoriteDataBase.getInstance(context)
    }

    @Provides
    fun provideFavoriteDao(database: FavoriteDataBase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun provideFavoriteRepository(
        dao: FavoriteDao,
        mapper: FavoriteMapper
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(dao, mapper)
    }

    @Provides
    fun provideFavoriteMapper(): FavoriteMapper {
        return FavoriteMapper()
    }
}