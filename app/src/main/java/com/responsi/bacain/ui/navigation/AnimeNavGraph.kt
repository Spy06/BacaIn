package com.responsi.bacain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.responsi.bacain.ui.screen.DetailScreen
import com.responsi.bacain.ui.screen.HomeScreen

// ── Route constants ───────────────────────────────────────────────────────────

object Routes {
    const val HOME   = "home"
    const val DETAIL = "detail/{animeId}"

    fun detailRoute(animeId: Int) = "detail/$animeId"
}

// ── NavGraph ──────────────────────────────────────────────────────────────────

@Composable
fun AnimeNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        // Home Screen
        composable(route = Routes.HOME) {
            HomeScreen(
                onAnimeClick = { animeId ->
                    navController.navigate(Routes.detailRoute(animeId))
                }
            )
        }

        // Detail Screen
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("animeId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("animeId") ?: return@composable
            DetailScreen(
                animeId = animeId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
