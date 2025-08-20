package com.example.bookapp.data.remote.repository

import com.example.bookapp.data.mapper.toDomain
import com.example.bookapp.data.remote.dataSources.BooksRemoteDataSources
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Реализация репозитория книг.
 * Работает с удалённым источником данных и преобразует DTO в доменную модель.
 *
 * @param remoteDataSources источник данных (API).
 */
class BooksRepositoryImpl @Inject constructor(
    private val remoteDataSources: BooksRemoteDataSources
) : BookRepository {

    /**
     * Получение списка книг по запросу.
     *
     * @param query строка поиска.
     * @return результат: список книг или ошибка.
     */
    override suspend fun getBooks(query: String): Result<List<Book>> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = remoteDataSources.getBooks(query)
                Result.success(dto.items.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    /**
     * Получение деталей книги.
     *
     * @param id идентификатор книги.
     * @return результат: книга или ошибка.
     */
    override suspend fun getBookDetails(id: String): Result<Book> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = remoteDataSources.getBookDetails(id)
                Result.success(dto.toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}