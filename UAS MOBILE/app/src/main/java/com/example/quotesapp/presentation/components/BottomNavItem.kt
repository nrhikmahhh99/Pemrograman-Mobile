package com.example.quotesapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.quotesapp.presentation.navigation.Screen

data class BottomNavItem(val label: String, val icon: ImageVector, val screen: Screen)

@Composable
fun BottomBar(navController: NavController, currentRoute: String) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Screen.Home),
        BottomNavItem("List", Icons.Default.List, Screen.ListQuote),
        BottomNavItem("Favorite", Icons.Default.Favorite, Screen.SavedQuote),
        BottomNavItem("Offline", Icons.Default.CloudOff, Screen.OfflineQuote),
        BottomNavItem("About", Icons.Default.Info, Screen.About)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    if (currentRoute != item.screen.route) {
                        navController.navigate(item.screen.route)
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
