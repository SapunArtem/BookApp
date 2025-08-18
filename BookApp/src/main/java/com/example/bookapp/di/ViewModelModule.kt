package com.example.bookapp.di

import com.example.bookapp.domain.repository.BookRepository
import com.example.bookapp.domain.useCases.GetBooksDetailsUseCase
import com.example.bookapp.domain.useCases.GetBooksUseCase
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
}