package dev.zaqueu.youwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zaqueu.core.navigation.BottomNavigationBar
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.presentation.screens.favorites.FavoritesScreen
import dev.zaqueu.moviefinder.presentation.screens.home.HomeScreen
import dev.zaqueu.moviefinder.presentation.screens.search.SearchScreen
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                Scaffold(
                    bottomBar = {
                        if (currentRoute != null && currentRoute != NavRoutes.WELCOME.route) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.WELCOME.route
                    ) {
                        composable(NavRoutes.WELCOME.route) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }

                        composable(NavRoutes.HOME.route) {
                            HomeScreen(onNavigate = navController::navigate)
                        }

                        composable(NavRoutes.SEARCH.route) {
                            SearchScreen()
                        }

                        composable(NavRoutes.FAVORITE.route) {
                            FavoritesScreen()
                        }
                    }
                }
            }
        }
    }
}
