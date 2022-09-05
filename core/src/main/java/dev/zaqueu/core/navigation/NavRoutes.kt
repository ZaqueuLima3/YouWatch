package dev.zaqueu.core.navigation

sealed class NavRoutes(val route: String) {
    object WELCOME : NavRoutes("welcome")
    object HOME : NavRoutes("home")
    object SEARCH : NavRoutes("search")
    object FAVORITE : NavRoutes("favorite")
    object DETAILS : NavRoutes("details")
}
