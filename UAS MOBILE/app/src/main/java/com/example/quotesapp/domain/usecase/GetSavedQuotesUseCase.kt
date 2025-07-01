package com.example.quotesapp.domain.usecase

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class GetSavedQuotesUseCase(private val repository: QuoteRepository) {
    operator fun invoke(): Flow<List<Quote>> = repository.getSavedQuotes()
}
