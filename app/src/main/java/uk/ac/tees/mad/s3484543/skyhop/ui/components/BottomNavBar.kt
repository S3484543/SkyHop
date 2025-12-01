package uk.ac.tees.mad.s3484543.skyhop.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar(navController: NavHostController) {

    NavigationBar {
        val items = listOf(
            BottomNavItem.Search,
            BottomNavItem.Results,
            BottomNavItem.Bookings
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(0)
                    }
                },
                label = { Text(item.label) },
                icon = { Icon(Icons.Default.Home, contentDescription = item.label) }
            )
        }
    }
}