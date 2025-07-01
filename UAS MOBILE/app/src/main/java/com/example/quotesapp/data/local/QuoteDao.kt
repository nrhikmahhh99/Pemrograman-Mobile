package com.example.quotesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotesapp.data.local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<QuoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("DELETE FROM quotes")
    suspend fun deleteAll()

    @Query("SELECT * FROM quotes WHERE isFavorite = 1 ORDER BY id DESC")
    fun getFavoriteQuotes(): Flow<List<QuoteEntity>>

    @Query("DELETE FROM quotes WHERE text = :text AND author = :author")
    suspend fun removeFromFavorite(text: String, author: String)

    @Query("DELETE FROM quotes WHERE text = :text AND author = :author")
    suspend fun deleteQuote(text: String, author: String)

    @Query("SELECT * FROM quotes WHERE isFavorite = 1 ORDER BY id DESC")
    fun getSavedQuotes(): Flow<List<QuoteEntity>>

}