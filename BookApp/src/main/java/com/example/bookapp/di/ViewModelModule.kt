package com.example.bookapp.di

import com.example.bookapp.domain.repository.BookRepository
import com.example.bookapp.domain.repository.FavoriteRepository
import com.example.bookapp.domain.useCases.Favorite.RemoveFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.GetFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import com.example.bookapp.domain.useCases.Home.GetBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideGetBooksUseCase(repository: BookRepository): GetBooksUseCase {
        return GetBooksUseCase(repository)
    }


    @Provides
    @ViewModelScoped
    fun provideGetBooksDetailsUseCase(repository: BookRepository): GetBooksDetailsUseCase {
        return GetBooksDetailsUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFavoriteUseCase(repository: FavoriteRepository):GetFavoriteUseCase{
        return GetFavoriteUseCase(repository)
    }
    @Provides
    @ViewModelScoped
    fun provideToggleFavoriteUseCase(repository: FavoriteRepository): ToggleFavoriteUseCase{
        return ToggleFavoriteUseCase(repository)
    }
    @Provides
    @ViewModelScoped
    fun provideCheckFavoriteUseCase(repository: FavoriteRepository): RemoveFavoriteUseCase {
        return RemoveFavoriteUseCase(repository)
    }
}