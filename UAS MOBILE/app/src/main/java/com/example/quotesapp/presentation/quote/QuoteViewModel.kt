package com.example.quotesapp.presentation.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.usecase.GetQuotesUseCase
import com.example.quotesapp.domain.usecase.GetRandomQuoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val repository: QuoteRepository
) : ViewModel() {

    private val _quoteList = MutableStateFlow<List<Quote>>(emptyList())
    val quoteList: StateFlow<List<Quote>> = _quoteList

    private val _randomQuote = MutableStateFlow<Quote?>(null)
    val randomQuote: StateFlow<Quote?> = _randomQuote

    private val _savedQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val savedQuotes: StateFlow<List<Quote>> = _savedQuotes

    init {
        viewModelScope.launch {
            repository.getSavedQuotes().collect { result ->
                _savedQuotes.value = result
            }
        }
    }

    fun loadQuotes() {
        viewModelScope.launch {
            try {
                _quoteList.value = getQuotesUseCase()
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    fun loadRandomQuote() {
        viewModelScope.launch {
            try {
                _randomQuote.value = getRandomQuoteUseCase()
            } catch (e: Exception) {
                // handle error
            }
        }
    }
    fun saveQuoteToFavorite(quote: Quote) {
        viewModelScope.launch {
            val updated = quote.copy(isFavorite = true)
            repository.insertOfflineQuote(updated)
            // Update list di UI juga
            _quoteList.value = _quoteList.value.map {
                if (it.text == quote.text && it.author == quote.author) updated else it
            }
        }
    }
    fun removeFromFavorite(quote: Quote) {
        viewModelScope.launch {
            val updated = quote.copy(isFavorite = false)
            repository.removeFromFavorite(updated)
            _savedQuotes.value = _savedQuotes.value.filterNot {
                it.text == quote.text && it.author == quote.author
            }
        }
    }

    fun toggleFavorite(quote: Quote) {
        viewModelScope.launch {
            val updatedQuote = quote.copy(isFavorite = !quote.isFavorite)
            repository.saveSingleQuote(updatedQuote)
            loadQuotes() // refresh ulang list-nya
        }
    }

}
