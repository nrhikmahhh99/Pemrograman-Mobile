package com.example.quotesapp.data.repository

import com.example.quotesapp.data.local.QuoteDao
import com.example.quotesapp.data.local.entity.QuoteEntity
import com.example.quotesapp.data.local.entity.toDomain
import com.example.quotesapp.data.local.entity.toEntity
import com.example.quotesapp.data.remote.QuoteApiService
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepositoryImpl(
    private val api: QuoteApiService,
    private val dao: QuoteDao
) : QuoteRepository {

    override suspend fun getQuotesFromApi(): List<Quote> = withContext(Dispatchers.IO) {
        val response = api.getQuotes()
        response.map {
            Quote(text = it.q, author = it.a)
        }
    }

    override fun getSavedQuotes(): Flow<List<Quote>> {
        return dao.getSavedQuotes().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveQuotes(quotes: List<Quote>) = withContext(Dispatchers.IO) {
        val entities = quotes.map {
            QuoteEntity(text = it.text, author = it.author)
        }
        dao.insertAll(entities)
    }

    override suspend fun saveSingleQuote(quote: Quote) = withContext(Dispatchers.IO) {
        val entity = QuoteEntity(text = quote.text, author = quote.author)
        dao.insertQuote(entity)
    }

    override suspend fun getQuotes(): List<Quote> = withContext(Dispatchers.IO) {
        api.getQuotes().map {
            Quote(text = it.q, author = it.a)
        }
    }

    override suspend fun getRandomQuote(): Quote = withContext(Dispatchers.IO) {
        val response = api.getQuotes()
        val randomQuote = response.random()
        Quote(text = randomQuote.q, author = randomQuote.a)
    }

    override fun getAllQuotesFromLocal(): Flow<List<Quote>> {
        return dao.getAllQuotes().map { entities ->
            entities.map { entity ->
                Quote(text = entity.text, author = entity.author)
            }
        }
    }
    override suspend fun insertOfflineQuote(quote: Quote) = withContext(Dispatchers.IO) {
        dao.insertQuote(quote.toEntity())
    }

    override fun getOfflineQuotes(): Flow<List<Quote>> {
        return dao.getAllQuotes().map { entities ->
            entities.map { Quote(text = it.text, author = it.author) }
        }
    }

    override suspend fun removeFromFavorite(quote: Quote) = withContext(Dispatchers.IO) {
        dao.removeFromFavorite(quote.text, quote.author)
    }

    override suspend fun deleteQuote(quote: Quote) = withContext(Dispatchers.IO) {
        dao.deleteQuote(quote.text, quote.author)
    }
}
