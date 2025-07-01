package com.example.quotesapp.presentation.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.domain.repository.QuoteRepository

class OfflineQuoteViewModelFactory(
    private val repository: QuoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfflineQuoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfflineQuoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
