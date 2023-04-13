package com.orange.feature.home

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import com.orange.feature.home.components.ProgramSearchScreen

@Composable
fun Navigation(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
) {
    val startingRoute = ScreenDestination.Search.route
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startingRoute) {
        composable(startingRoute) {
            val viewModel: ProgramViewModel = hiltViewModel()
            ProgramSearchScreen(
                viewModel.isShowingDetails,
                { isBackPressed ->
                    viewModel.onBackPress(isBackPressed)
                },
                windowSizeClass,
                displayFeatures,
            )
        }
    }
}

sealed class ScreenDestination(val route: String) {
    object Search : ScreenDestination(route = "search")
}
