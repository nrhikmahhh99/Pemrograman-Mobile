package com.example.quotesapp.presentation.quote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.domain.model.Quote
import androidx.navigation.NavController

@Composable
fun ListQuoteScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: QuoteViewModel = viewModel(factory = QuoteViewModelFactory(context))
    val quotes by viewModel.quoteList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadQuotes()
    }

    QuoteListContent(
        quotes = quotes,
        onQuoteClick = { quote ->
            // Navigasi ke detail saat item diklik
            navController.navigate("detail/${quote.text}/${quote.author}")
        },
        onFavoriteClick = { quote ->
            viewModel.saveQuoteToFavorite(quote)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteListContent(
    quotes: List<Quote>,
    onQuoteClick: (Quote) -> Unit,
    onFavoriteClick: (Quote) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Kumpulan Kutipan", color = MaterialTheme.colorScheme.onPrimary)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        if (quotes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(quotes) { quote ->
                    QuoteCard(
                        quote = quote,
                        onClick = { onQuoteClick(quote) },
                        onFavoriteClick = { onFavoriteClick(quote) }
                    )
                }
            }
        }
    }
}

@Composable
fun QuoteCard(
    quote: Quote,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "\"${quote.text}\"", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "- ${quote.author}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (quote.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (quote.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                    )
                }
                Button(onClick = onFavoriteClick) {
                    Text("Simpan")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteListPreview() {
    val dummyQuotes = listOf(
        Quote("Belajarlah dari kemarin, hiduplah untuk hari ini, berharaplah untuk besok.", "Albert Einstein"),
        Quote("Jangan menunggu; waktu tidak akan pernah 'tepat'.", "Napoleon Hill"),
        Quote("Kebahagiaan tidak datang dari apa yang kita miliki, tetapi dari siapa kita bersama.", "Anonymous")
    )
    QuoteListContent(
        quotes = dummyQuotes,
        onQuoteClick = {},
        onFavoriteClick = {}
    )
}
@Preview(showBackground = true)
@Composable
fun QuoteCardPreview() {
    val quote = Quote("Hidup adalah seni menggambar tanpa penghapus.", "John W. Gardner")
    QuoteCard(quote = quote, onClick = {}, onFavoriteClick = {})
}

