package com.example.bookapp.di

import com.example.bookapp.data.remote.dataSources.BooksRemoteDataSources
import com.example.bookapp.data.remote.dataSources.BooksRemoteDataSourcesImpl
import com.example.bookapp.data.remote.repository.BooksRepositoryImpl
import com.example.bookapp.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Модуль для привязки реализаций к интерфейсам (Binds).
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    /**
     * Привязка реализации удалённого источника данных книг к интерфейсу.
     */
    @Binds
    fun bindBooksRemoteDataSource(impl: BooksRemoteDataSourcesImpl): BooksRemoteDataSources

    /**
     * Привязка реализации репозитория книг к интерфейсу.
     */
    @Binds
    fun bindBooksRepository(impl: BooksRepositoryImpl): BookRepository
}