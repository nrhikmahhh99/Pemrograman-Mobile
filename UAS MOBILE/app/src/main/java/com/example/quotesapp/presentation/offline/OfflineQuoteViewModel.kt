package com.example.quotesapp.presentation.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.usecase.GetOfflineQuotesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OfflineQuoteViewModel(private val repository: QuoteRepository) : ViewModel() {
    val offlineQuotes: StateFlow<List<Quote>> = repository.getOfflineQuotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    fun deleteQuote(quote: Quote) {
        viewModelScope.launch {
            repository.deleteQuote(quote)
        }
    }
}