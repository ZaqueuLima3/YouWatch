package dev.zaqueu.youwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.zaqueu.core.navigation.BottomNavigationBar
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.presentation.screens.episodedetails.EpisodeDetailsScreen
import dev.zaqueu.moviefinder.presentation.screens.episodes.EpisodesScreen
import dev.zaqueu.moviefinder.presentation.screens.favorites.FavoritesScreen
import dev.zaqueu.moviefinder.presentation.screens.home.HomeScreen
import dev.zaqueu.moviefinder.presentation.screens.search.SearchScreen
import dev.zaqueu.moviefinder.presentation.screens.showdetails.ShowDetailsScreen
import dev.zaqueu.onboarding.presentation.screens.welcome.WelcomeScreen
import dev.zaqueu.ui.theme.YouWatchTheme
import dev.zaqueu.youwatch.navigation.navigate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        startDestination = NavRoutes.WELCOME.route
                    ) {
                        composable(NavRoutes.WELCOME.route) {
                            WelcomeScreen(onNavigate = navController::navigate)
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
                                FavoritesScreen()
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
                                scaffoldState,
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
