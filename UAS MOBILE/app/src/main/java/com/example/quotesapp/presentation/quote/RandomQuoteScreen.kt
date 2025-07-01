package com.example.quotesapp.presentation.quote

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.di.Injection

// Fungsi utama (dijalankan saat runtime)
@ExperimentalMaterial3Api
@Composable
fun RandomQuoteScreen() {
    val context = LocalContext.current
    val viewModel: QuoteViewModel = viewModel(factory = QuoteViewModelFactory(context))
    val quote by viewModel.randomQuote.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRandomQuote()
    }

    QuoteContent(
        text = quote?.text,
        author = quote?.author
    )
}

@ExperimentalMaterial3Api
@Composable
fun QuoteContent(text: String?, author: String?) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Kutipan Hari Ini",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (text == null) {
                CircularProgressIndicator()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "\"$text\"",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "- ${author ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun QuoteContentPreview() {
    QuoteContent(
        text = "Hidup itu seperti naik sepeda. Agar tetap seimbang, kamu harus terus bergerak.",
        author = "Albert Einstein"
    )
}