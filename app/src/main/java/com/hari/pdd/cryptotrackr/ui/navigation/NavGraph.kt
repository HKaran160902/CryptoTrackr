package com.hari.pdd.cryptotrackr.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hari.pdd.cryptotrackr.ui.screens.alerts.AlertsScreen
import com.hari.pdd.cryptotrackr.ui.screens.detail.CoinDetailScreen
import com.hari.pdd.cryptotrackr.ui.screens.home.HomeScreen
import com.hari.pdd.cryptotrackr.ui.screens.settings.SettingsScreen
import com.hari.pdd.cryptotrackr.ui.screens.watchlist.WatchlistScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = modifier
    ) {
        // Home Screen
        composable(
            route = NavRoutes.Home.route,
            enterTransition = NavAnimations.fadeEnterTransition,
            exitTransition = NavAnimations.fadeExitTransition,
            popEnterTransition = NavAnimations.fadeEnterTransition,
            popExitTransition = NavAnimations.fadeExitTransition
        ) {
            HomeScreen(
                onCoinClick = { coinId ->
                    navController.navigate(NavRoutes.CoinDetail.createRoute(coinId))
                },
                onSettingsClick = {
                    navController.navigate(NavRoutes.Settings.route)
                }
            )
        }

        // Watchlist Screen
        composable(
            route = NavRoutes.Watchlist.route,
            enterTransition = NavAnimations.fadeEnterTransition,
            exitTransition = NavAnimations.fadeExitTransition,
            popEnterTransition = NavAnimations.fadeEnterTransition,
            popExitTransition = NavAnimations.fadeExitTransition
        ) {
            WatchlistScreen(
                onCoinClick = { coinId ->
                    navController.navigate(NavRoutes.CoinDetail.createRoute(coinId))
                },
                onNavigateToHome = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        // Alerts Screen
        composable(
            route = NavRoutes.Alerts.route,
            enterTransition = NavAnimations.fadeEnterTransition,
            exitTransition = NavAnimations.fadeExitTransition,
            popEnterTransition = NavAnimations.fadeEnterTransition,
            popExitTransition = NavAnimations.fadeExitTransition
        ) {
            AlertsScreen(
                onCoinClick = { coinId ->
                    navController.navigate(NavRoutes.CoinDetail.createRoute(coinId))
                }
            )
        }

        // Settings Screen
        composable(
            route = NavRoutes.Settings.route,
            enterTransition = NavAnimations.enterTransition,
            exitTransition = NavAnimations.exitTransition,
            popEnterTransition = NavAnimations.popEnterTransition,
            popExitTransition = NavAnimations.popExitTransition
        ) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // Coin Detail Screen
        composable(
            route = NavRoutes.CoinDetail.route,
            arguments = listOf(
                navArgument("coinId") { type = NavType.StringType }
            ),
            enterTransition = NavAnimations.enterTransition,
            exitTransition = NavAnimations.exitTransition,
            popEnterTransition = NavAnimations.popEnterTransition,
            popExitTransition = NavAnimations.popExitTransition
        ) { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
            CoinDetailScreen(
                coinId = coinId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
