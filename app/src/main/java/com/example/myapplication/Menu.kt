package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

class NavItem(val name: String, val route: String, val icon: ImageVector)

class Menu {
    @Composable
    fun DrawerView() {
        val navItems = listOf(
            NavItem(
                name = "Home",
                route = "home",
                icon = Icons.Default.Info,
            ),
            NavItem(
                name = "Search",
                route = "search",
                icon = Icons.Default.Search,
            )
        )

        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            navItems.forEach { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        Icon(imageVector = item.icon, contentDescription = null)
                        Text(item.name, modifier = Modifier.padding(horizontal = 5.dp))
                    }
                }
            }
        }

    }
}