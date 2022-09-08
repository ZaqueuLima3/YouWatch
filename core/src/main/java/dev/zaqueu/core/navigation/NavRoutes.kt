package dev.zaqueu.core.navigation

sealed class NavRoutes(val route: String) {
    object WELCOME : NavRoutes("welcome")
    object PIN : NavRoutes("pin")
    object HOME : NavRoutes("home")
    object SEARCH : NavRoutes("search")
    object FAVORITE : NavRoutes("favorite")
    object DETAILS : NavRoutes("details")
    object EPISODES : NavRoutes("episodes")
    object EPISODEDETAILS : NavRoutes("episodedetails")

    companion object {
        const val DETAILS_SHOW_ID = "showId"
        const val EPISODES_SHOW_ID = "showId"
        const val EPISODE_DETAILS_ID = "episodeId"
    }
}
