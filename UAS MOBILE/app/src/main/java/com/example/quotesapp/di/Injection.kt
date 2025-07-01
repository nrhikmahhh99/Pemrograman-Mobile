package com.example.quotesapp.di

import android.content.Context
import com.example.quotesapp.data.local.QuoteDatabase
import com.example.quotesapp.data.remote.QuoteApiService
import com.example.quotesapp.data.remote.RetrofitInstance
import com.example.quotesapp.data.repository.QuoteRepositoryImpl
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.usecase.GetOfflineQuotesUseCase
import com.example.quotesapp.domain.usecase.GetQuotesUseCase
import com.example.quotesapp.domain.usecase.GetRandomQuoteUseCase
import com.example.quotesapp.presentation.offline.OfflineQuoteViewModelFactory
import com.example.quotesapp.presentation.quote.QuoteViewModelFactory

object Injection {

    fun provideRepository(context: Context): QuoteRepository {
        val db = QuoteDatabase.getDatabase(context)
        val api = RetrofitInstance.api
        return QuoteRepositoryImpl(
            api = api,
            dao = db.quoteDao()
        )
    }

    fun provideGetQuotesUseCase(context: Context): GetQuotesUseCase {
        return GetQuotesUseCase(provideRepository(context))
    }

    fun provideGetRandomQuoteUseCase(context: Context): GetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(provideRepository(context))
    }

    fun provideGetOfflineQuotesUseCase(context: Context): GetOfflineQuotesUseCase {
        return GetOfflineQuotesUseCase(provideRepository(context))
    }

    fun provideQuoteViewModelFactory(context: Context): QuoteViewModelFactory {
        return QuoteViewModelFactory(context)
    }

}