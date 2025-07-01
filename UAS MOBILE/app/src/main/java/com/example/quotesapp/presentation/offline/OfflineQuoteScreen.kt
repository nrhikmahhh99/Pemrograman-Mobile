package com.example.quotesapp.presentation.offline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.di.Injection
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.usecase.GetOfflineQuotesUseCase
import com.example.quotesapp.presentation.quote.QuoteCard

@Composable
fun OfflineQuoteScreen() {
    val context = LocalContext.current
    val repository = Injection.provideRepository(context)
    val factory = OfflineQuoteViewModelFactory(repository)
    val viewModel: OfflineQuoteViewModel = viewModel(factory = factory)
    val quotes by viewModel.offlineQuotes.collectAsState()

    OfflineQuoteContent(
        quotes = quotes,
        onDeleteClick = { viewModel.deleteQuote(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineQuoteContent(
    quotes: List<Quote>,
    onDeleteClick: (Quote) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Kutipan Offline",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        if (quotes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Tidak ada kutipan offline yang tersedia.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(quotes) { quote ->
                    QuoteCard(
                        quote = quote,
                        onClick = { },
                        onFavoriteClick = { onDeleteClick(quote) }
                    )
                }
            }
        }
    }
}
