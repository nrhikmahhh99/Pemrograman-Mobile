package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getQuotes(): List<Quote>
    suspend fun getRandomQuote(): Quote
    fun getAllQuotesFromLocal(): Flow<List<Quote>>
    suspend fun getQuotesFromApi(): List<Quote>
    fun getSavedQuotes(): Flow<List<Quote>>
    suspend fun saveQuotes(quotes: List<Quote>)
    suspend fun saveSingleQuote(quote: Quote)
    suspend fun insertOfflineQuote(quote: Quote)
    fun getOfflineQuotes(): Flow<List<Quote>>
    suspend fun removeFromFavorite(quote: Quote)
    suspend fun deleteQuote(quote: Quote)
}
