package com.jamescoggan.sample.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf

val NAV_ITEMS = persistentListOf(BottomNavItem.Recipes, BottomNavItem.Favorites)

@Composable
fun BottomNavigationBar(selectedIndex: Int, onSelectedIndex: (Int) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        NAV_ITEMS.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selected = selectedIndex == index,
                onClick = { onSelectedIndex(index) },
            )
        }
    }
}
