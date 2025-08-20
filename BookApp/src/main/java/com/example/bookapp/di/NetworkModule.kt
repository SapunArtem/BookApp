package com.example.bookapp.di

import com.example.bookapp.data.remote.api.BooksServiceApi
import com.example.bookapp.data.remote.api.RetryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.bookapp.utils.Constant.Companion.BASE_URL
import javax.inject.Singleton

/**
 * Модуль для предоставления сетевых зависимостей (Retrofit, OkHttp, API).
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Предоставление Retrofit.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Предоставление OkHttpClient с RetryInterceptor.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideRetryInterceptor())
            .build()
    }

    /**
     * Предоставление интерсептора для повторных попыток сетевых запросов.
     */
    @Provides
    @Singleton
    fun provideRetryInterceptor(): RetryInterceptor {
        return RetryInterceptor()
    }

    /**
     * Предоставление BooksServiceApi.
     */
    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit): BooksServiceApi {
        return retrofit.create(BooksServiceApi::class.java)
    }
}