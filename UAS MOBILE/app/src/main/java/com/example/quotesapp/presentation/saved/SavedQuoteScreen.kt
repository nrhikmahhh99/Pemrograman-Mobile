package com.example.quotesapp.presentation.saved

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
import com.example.quotesapp.presentation.quote.QuoteCard

@Composable
fun SavedQuoteScreen() {
    val context = LocalContext.current
    val repository = Injection.provideRepository(context)
    val factory = SavedQuoteViewModelFactory(repository)
    val viewModel: SavedQuoteViewModel = viewModel(factory = factory)
    val savedQuotes by viewModel.savedQuotes.collectAsState()

    SavedQuoteContent(
        savedQuotes = savedQuotes,
        onFavoriteClick = { viewModel.removeFromFavorite(it) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedQuoteContent(
    savedQuotes: List<Quote>,
    onFavoriteClick: (Quote) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Kutipan Terfavorit",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        if (savedQuotes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Belum ada kutipan yang disimpan.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(savedQuotes) { quote ->
                    QuoteCard(
                        quote = quote,
                        onClick = {},
                        onFavoriteClick = { onFavoriteClick(quote) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedQuotePreview() {
    val dummyQuotes = listOf(
        Quote("Bersyukur itu kunci kebahagiaan.", "Anonim"),
        Quote("Langit tidak selalu cerah, begitu juga hidup.", "Anonim")
    )
    SavedQuoteContent(
        savedQuotes = dummyQuotes,
        onFavoriteClick = {} // Tidak melakukan apa-apa di preview
    )
}