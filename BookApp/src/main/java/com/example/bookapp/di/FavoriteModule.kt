package com.example.bookapp.di

import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.database.FavoriteDataBase
import com.example.bookapp.data.local.repository.FavoriteRepositoryImpl
import com.example.bookapp.data.mapper.FavoriteMapper
import com.example.bookapp.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
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