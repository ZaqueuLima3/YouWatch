package dev.zaqueu.youwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zaqueu.core.navigation.Route
import dev.zaqueu.onboarding.presentation.screens.welcome.WelcomeScreen
import dev.zaqueu.youwatch.navigation.navigate
import dev.zaqueu.ui.theme.YouWatchTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YouWatchTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME
                ) {
                    composable(Route.WELCOME) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                }
            }
        }
    }
}
