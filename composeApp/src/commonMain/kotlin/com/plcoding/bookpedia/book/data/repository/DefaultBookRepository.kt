package com.plcoding.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.plcoding.bookpedia.book.data.database.FavoriteBookDao
import com.plcoding.bookpedia.book.data.mapper.toBook
import com.plcoding.bookpedia.book.data.mapper.toBookEntity
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
//    private val favoriteBookDao: FavoriteBookDao
) : BookRepository {

    override suspend fun searchBooks(
        query: String,
    ): Result<List<Book>, DataError.Remote> =
        remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map {
                    it.toBook()
                }
            }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
//        val localResult = favoriteBookDao.getFavoriteBook(
//            id = bookId
//        )
//        return if (localResult == null) {
        return remoteBookDataSource
            .getBookDetails(bookId)
            .map { it.description }
//        } else {
//            Result.Success(localResult.description)
//        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> =
        flow { }
//        favoriteBookDao
//            .getFavoriteBooks()
//            .map { bookEntities ->
//                bookEntities.map {
//                    it.toBook()
//                }
//            }

    override fun isBookFavorite(id: String): Flow<Boolean> =
        flow {

        }
//        favoriteBookDao
//            .getFavoriteBooks()
//            .map { bookEntities ->
//                bookEntities.any { it.id == id }
//            }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        //
        return Result.Success(Unit)
    }
//        try {
//            favoriteBookDao.upsert(book.toBookEntity())
//            Result.Success(Unit)
//        } catch (e: SQLiteException) {
//            Result.Error(DataError.Local.DISK_FULL)
//        }

    override suspend fun deleteFromInstance(id: String) {
//        favoriteBookDao.deleteFavoriteBook(id)
    }
}
