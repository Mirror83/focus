package com.app.focus

import routes.FocusScreen
import routes.HomeScreen
import routes.SettingsScreen
import routes.StatsScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FocusApp() {
    Scaffold { innerPadding: PaddingValues ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = FocusScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(FocusScreen.Home.name) {
                HomeScreen(
                    onSettingsClicked = { navController.navigate(FocusScreen.Settings.name) },
                    onStatsClicked = { navController.navigate(FocusScreen.Statistics.name) },
                    onTimerClicked = { navController.navigate(FocusScreen.Timer.name) }
                )
            }
            composable(FocusScreen.Timer.name) {
                ClockAndroid()
            }
            composable(FocusScreen.Statistics.name) {
                StatsScreen(onNavigateBack = { navController.navigateUp() })
            }
            composable(FocusScreen.Settings.name) {
                SettingsScreen(onNavigateBack = { navController.navigateUp() })
            }
        }
    }

}