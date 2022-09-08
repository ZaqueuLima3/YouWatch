package dev.zaqueu.youwatch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.zaqueu.core.domain.preferences.Preferences
import dev.zaqueu.core.navigation.BottomNavigationBar
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.presentation.screens.episodedetails.EpisodeDetailsScreen
import dev.zaqueu.moviefinder.presentation.screens.episodes.EpisodesScreen
import dev.zaqueu.moviefinder.presentation.screens.favorites.FavoritesScreen
import dev.zaqueu.moviefinder.presentation.screens.home.HomeScreen
import dev.zaqueu.moviefinder.presentation.screens.search.SearchScreen
import dev.zaqueu.moviefinder.presentation.screens.showdetails.ShowDetailsScreen
import dev.zaqueu.onboarding.presentation.screens.pinscreet.PinSecretScreen
import dev.zaqueu.onboarding.presentation.screens.welcome.WelcomeScreen
import dev.zaqueu.ui.theme.YouWatchTheme
import dev.zaqueu.youwatch.navigation.navigate
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowWelcomeScreen = preferences.loadShouldShowWelcomeScreen()
        setContent {
            YouWatchTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val showBottomNavigation = currentRoute != null
                        && currentRoute.contains(NavRoutes.DETAILS.route).not()
                        && currentRoute.contains(NavRoutes.EPISODES.route).not()
                        && currentRoute != NavRoutes.WELCOME.route
                        && currentRoute != NavRoutes.PIN.route

                val startDestination =
                    if (shouldShowWelcomeScreen) NavRoutes.WELCOME.route else NavRoutes.PIN.route

                Scaffold(
                    bottomBar = {
                        if (showBottomNavigation) {
                            BottomNavigationBar(navController = navController)
                        }
                    },
                    scaffoldState = scaffoldState
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        composable(NavRoutes.WELCOME.route) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }

                        composable(NavRoutes.PIN.route) {
                            PinSecretScreen(onNavigate = navController::navigate)
                        }

                        composable(NavRoutes.HOME.route) {
                            InnerScreen(innerPadding) {
                                HomeScreen(onNavigate = navController::navigate)
                            }
                        }

                        composable(NavRoutes.SEARCH.route) {
                            InnerScreen(innerPadding) {
                                SearchScreen(
                                    onNavigate = navController::navigate
                                )
                            }
                        }

                        composable(NavRoutes.FAVORITE.route) {
                            InnerScreen(innerPadding) {
                                FavoritesScreen(
                                    onNavigate = navController::navigate
                                )
                            }
                        }

                        composable(
                            route = "${NavRoutes.DETAILS.route}/{${NavRoutes.DETAILS_SHOW_ID}}",
                            arguments = listOf(
                                navArgument(NavRoutes.DETAILS_SHOW_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            ShowDetailsScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                                showId = backStackEntry.arguments?.getString(NavRoutes.DETAILS_SHOW_ID)
                            )
                        }

                        composable(
                            route = "${NavRoutes.EPISODES.route}/{${NavRoutes.EPISODES_SHOW_ID}}",
                            arguments = listOf(
                                navArgument(NavRoutes.EPISODES_SHOW_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            EpisodesScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                                showId = backStackEntry.arguments?.getString(NavRoutes.EPISODES_SHOW_ID)
                            )
                        }

                        composable(
                            route = "${NavRoutes.EPISODEDETAILS.route}/{${NavRoutes.EPISODE_DETAILS_ID}}",
                            arguments = listOf(
                                navArgument(NavRoutes.EPISODE_DETAILS_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            EpisodeDetailsScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                                episodeId = backStackEntry.arguments?.getString(NavRoutes.EPISODE_DETAILS_ID)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InnerScreen(
    innerPadding: PaddingValues,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        content = { content() },
    )
}
