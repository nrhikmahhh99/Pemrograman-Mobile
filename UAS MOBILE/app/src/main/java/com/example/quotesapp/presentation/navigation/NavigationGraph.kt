package com.example.quotesapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quotesapp.presentation.about.AboutScreen
import com.example.quotesapp.presentation.quote.ListQuoteScreen
import com.example.quotesapp.presentation.quote.RandomQuoteScreen
import com.example.quotesapp.presentation.saved.SavedQuoteScreen
import com.example.quotesapp.presentation.offline.OfflineQuoteScreen
import com.example.quotesapp.presentation.quote.DetailQuoteScreen
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Home.route) {
            RandomQuoteScreen()
        }
        composable(Screen.ListQuote.route) {
            ListQuoteScreen(navController = navController)
        }
        composable(Screen.SavedQuote.route) {
            SavedQuoteScreen()
        }
        composable(Screen.OfflineQuote.route) {
            OfflineQuoteScreen()
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(
            route = "detail/{text}/{author}",
            arguments = listOf(
                navArgument("text") { type = NavType.StringType },
                navArgument("author") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            val author = backStackEntry.arguments?.getString("author") ?: ""
            DetailQuoteScreen(text = text, author = author)
        }
    }
}
