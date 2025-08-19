package com.example.bookapp.di

import com.example.bookapp.domain.repository.BookRepository
import com.example.bookapp.domain.repository.FavoriteRepository
import com.example.bookapp.domain.repository.LocalizationRepository
import com.example.bookapp.domain.repository.ProfileRepository
import com.example.bookapp.domain.repository.ThemeRepository
import com.example.bookapp.domain.useCases.Favorite.RemoveFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.GetFavoriteUseCase
import com.example.bookapp.domain.useCases.Favorite.ToggleFavoriteUseCase
import com.example.bookapp.domain.useCases.Home.GetBooksDetailsUseCase
import com.example.bookapp.domain.useCases.Home.GetBooksUseCase
import com.example.bookapp.domain.useCases.Profile.GetProfileUseCase
import com.example.bookapp.domain.useCases.Profile.UpdateProfileUseCase
import com.example.bookapp.domain.useCases.Settings.ChangeAppLanguageUseCase
import com.example.bookapp.domain.useCases.Settings.ChangeAppThemeUseCase
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
    fun provideGetFavoriteUseCase(repository: FavoriteRepository): GetFavoriteUseCase {
        return GetFavoriteUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideToggleFavoriteUseCase(repository: FavoriteRepository): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideCheckFavoriteUseCase(repository: FavoriteRepository): RemoveFavoriteUseCase {
        return RemoveFavoriteUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetProfileUseCase(repository: ProfileRepository): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateProfileUseCase(repository: ProfileRepository): UpdateProfileUseCase {
        return UpdateProfileUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideChangeLanguageUseCase(localizationRepository: LocalizationRepository): ChangeAppLanguageUseCase =
        ChangeAppLanguageUseCase(localizationRepository)

    @Provides
    @ViewModelScoped
    fun provideChangeThemeUseCase(themeRepository: ThemeRepository): ChangeAppThemeUseCase =
        ChangeAppThemeUseCase(themeRepository)
}