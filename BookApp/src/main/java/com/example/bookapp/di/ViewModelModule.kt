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

/**
 * Модуль для предоставления зависимостей ViewModel.
 * Все зависимости живут на время жизни ViewModel.
 */
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    /**
     * Предоставление use case для получения списка книг.
     */
    @Provides
    @ViewModelScoped
    fun provideGetBooksUseCase(repository: BookRepository): GetBooksUseCase {
        return GetBooksUseCase(repository)
    }

    /**
     * Предоставление use case для получения детальной информации о книге.
     */
    @Provides
    @ViewModelScoped
    fun provideGetBooksDetailsUseCase(repository: BookRepository): GetBooksDetailsUseCase {
        return GetBooksDetailsUseCase(repository)
    }

    /**
     * Предоставление use case для получения списка избранного.
     */
    @Provides
    @ViewModelScoped
    fun provideGetFavoriteUseCase(repository: FavoriteRepository): GetFavoriteUseCase {
        return GetFavoriteUseCase(repository)
    }

    /**
     * Предоставление use case для переключения состояния избранного (добавление/удаление).
     */
    @Provides
    @ViewModelScoped
    fun provideToggleFavoriteUseCase(repository: FavoriteRepository): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }

    /**
     * Предоставление use case для удаления книги из избранного.
     */
    @Provides
    @ViewModelScoped
    fun provideCheckFavoriteUseCase(repository: FavoriteRepository): RemoveFavoriteUseCase {
        return RemoveFavoriteUseCase(repository)
    }

    /**
     * Предоставление use case для получения профиля пользователя.
     */
    @Provides
    @ViewModelScoped
    fun provideGetProfileUseCase(repository: ProfileRepository): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }

    /**
     * Предоставление use case для обновления профиля пользователя.
     */
    @Provides
    @ViewModelScoped
    fun provideUpdateProfileUseCase(repository: ProfileRepository): UpdateProfileUseCase {
        return UpdateProfileUseCase(repository)
    }

    /**
     * Предоставление use case для смены языка приложения.
     */
    @Provides
    @ViewModelScoped
    fun provideChangeLanguageUseCase(localizationRepository: LocalizationRepository): ChangeAppLanguageUseCase =
        ChangeAppLanguageUseCase(localizationRepository)

    /**
     * Предоставление use case для смены темы приложения.
     */
    @Provides
    @ViewModelScoped
    fun provideChangeThemeUseCase(themeRepository: ThemeRepository): ChangeAppThemeUseCase =
        ChangeAppThemeUseCase(themeRepository)
}