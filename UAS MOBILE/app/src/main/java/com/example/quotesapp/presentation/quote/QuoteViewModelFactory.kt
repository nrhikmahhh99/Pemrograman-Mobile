package com.example.quotesapp.presentation.quote

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.di.Injection
import com.example.quotesapp.domain.usecase.GetQuotesUseCase
import com.example.quotesapp.domain.usecase.GetRandomQuoteUseCase

class QuoteViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = Injection.provideRepository(context) // pastikan ada
        return QuoteViewModel(
            getQuotesUseCase = GetQuotesUseCase(repository),
            getRandomQuoteUseCase = GetRandomQuoteUseCase(repository),
            repository = repository
        ) as T
    }
}
