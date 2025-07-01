package com.example.quotesapp.domain.usecase

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository

class GetRandomQuoteUseCase(private val repository: QuoteRepository) {
    suspend operator fun invoke(): Quote {
        return repository.getRandomQuote()
    }
}
