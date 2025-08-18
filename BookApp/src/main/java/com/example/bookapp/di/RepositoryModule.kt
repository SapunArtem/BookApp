package com.example.bookapp.di

import android.content.Context
import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.local.dao.ProfileDao
import com.example.bookapp.data.local.repository.FavoriteRepositoryImpl
import com.example.bookapp.data.local.repository.LocalizationRepositoryImpl
import com.example.bookapp.data.local.repository.ProfileRepositoryImpl
import com.example.bookapp.data.local.repository.ThemeRepositoryImpl
import com.example.bookapp.data.mapper.FavoriteMapper
import com.example.bookapp.domain.repository.FavoriteRepository
import com.example.bookapp.domain.repository.LocalizationRepository
import com.example.bookapp.domain.repository.ProfileRepository
import com.example.bookapp.domain.repository.ThemeRepository
import com.example.bookapp.presentation.components.settings.language.LocalizationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFavoriteRepository(
        dao: FavoriteDao,
        mapper: FavoriteMapper
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(dao, mapper)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        dao: ProfileDao
    ): ProfileRepository {
        return ProfileRepositoryImpl(dao)
    }


    @Provides
    @Singleton
    fun provideLocalizationRepository(
        @ApplicationContext context: Context,
        localizationManager: LocalizationManager
    ): LocalizationRepository = LocalizationRepositoryImpl(context, localizationManager)

    @Provides
    @Singleton
    fun provideThemeRepository(): ThemeRepository = ThemeRepositoryImpl()

    @Provides
    @Singleton
    fun provideLocalizationManager(): LocalizationManager = LocalizationManager

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}