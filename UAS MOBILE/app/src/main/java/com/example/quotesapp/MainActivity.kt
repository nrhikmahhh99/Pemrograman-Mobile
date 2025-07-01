package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.presentation.components.BottomBar
import com.example.quotesapp.presentation.navigation.NavigationGraph

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: ""

            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController, currentRoute = currentRoute)
                }
            ) {paddingValues ->
                NavigationGraph(navController = navController, paddingValues = paddingValues)
            }
        }
    }
}
