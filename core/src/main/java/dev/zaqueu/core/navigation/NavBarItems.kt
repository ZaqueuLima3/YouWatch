package dev.zaqueu.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

object NavBarItems {
    val BarItems = listOf(
        BottomBarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = NavRoutes.HOME.route
        ),
        BottomBarItem(
            title = "Search",
            image = Icons.Filled.Search,
            route = NavRoutes.SEARCH.route
        ),
        BottomBarItem(
            title = "Favorites",
            image = Icons.Filled.Favorite,
            route = NavRoutes.FAVORITE.route
        )
    )
}
