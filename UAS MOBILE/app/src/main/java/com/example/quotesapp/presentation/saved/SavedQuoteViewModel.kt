package com.example.quotesapp.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.model.Quote
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedQuoteViewModel(
    private val repository: QuoteRepository
) : ViewModel() {

    val savedQuotes: StateFlow<List<Quote>> = repository.getSavedQuotes()
        .map { quotes -> quotes.filter { it.isFavorite } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    fun removeFromFavorite(quote: Quote) {
        viewModelScope.launch {
            repository.removeFromFavorite(quote)
        }
    }
}
