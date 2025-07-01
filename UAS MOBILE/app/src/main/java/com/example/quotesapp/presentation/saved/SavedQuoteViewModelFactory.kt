package com.example.quotesapp.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.domain.repository.QuoteRepository

class SavedQuoteViewModelFactory(
    private val repository: QuoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedQuoteViewModel(repository) as T
    }
}
