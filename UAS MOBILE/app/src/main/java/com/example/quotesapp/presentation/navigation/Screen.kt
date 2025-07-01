package com.example.quotesapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ListQuote : Screen("list_quote")
    object SavedQuote : Screen("saved_quote")
    object OfflineQuote : Screen("offline_quote")
    object About : Screen("about")
}
