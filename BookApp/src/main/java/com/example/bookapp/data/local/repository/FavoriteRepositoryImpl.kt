package com.example.bookapp.data.local.repository

import com.example.bookapp.data.local.dao.FavoriteDao
import com.example.bookapp.data.mapper.FavoriteMapper
import com.example.bookapp.domain.models.Book
import com.example.bookapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Репозиторий для работы с избранными книгами.
 * Использует FavoriteDao и FavoriteMapper.
 */
class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val mapper: FavoriteMapper
) : FavoriteRepository {

    override fun getAllFavorites(): Flow<List<Book>> {
        return dao.getAll().map { list ->
            list.map { mapper.mapToDomain(it) }
        }
    }

    override suspend fun addFavorite(book: Book) {
        dao.insert(mapper.mapToEntity(book))
    }

    override suspend fun removeFavorite(book: Book) {
        dao.delete(mapper.mapToEntity(book))
    }

    override fun isFavorite(bookId: String): Flow<Boolean> {
        return dao.isFavorite(bookId)
    }


}