package dev.zaqueu.core.navigation

sealed class NavRoutes(val route: String) {
    object WELCOME : NavRoutes("welcome")
    object HOME : NavRoutes("home")
    object SEARCH : NavRoutes("search")
    object FAVORITE : NavRoutes("favorite")
    object DETAILS : NavRoutes("details")
    object EPISODES : NavRoutes("episodes")

    companion object {
        const val DETAILS_SHOW_ID = "showId"
        const val EPISODES_SHOW_ID = "showId"
    }
}
