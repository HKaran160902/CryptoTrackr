package com.hari.pdd.cryptotrackr.ui.navigation

sealed class NavRoutes(val route: String) {

    // Main bottom navigation screens
    data object Home : NavRoutes("home")
    data object Watchlist : NavRoutes("watchlist")
    data object Alerts : NavRoutes("alerts")

    // Other screens
    data object Settings : NavRoutes("settings")

    // Detail screens with arguments
    data object CoinDetail : NavRoutes("coin_detail/{coinId}") {
        fun createRoute(coinId: String) = "coin_detail/$coinId"
    }

    data object AddHolding : NavRoutes("add_holding/{coinId}") {
        fun createRoute(coinId: String) = "add_holding/$coinId"
    }

    data object AddAlert : NavRoutes("add_alert/{coinId}") {
        fun createRoute(coinId: String) = "add_alert/$coinId"
    }

    companion object {
        // Bottom navigation routes
        val bottomNavRoutes = listOf(
            Home.route,
            Watchlist.route,
            Alerts.route
        )

        fun isBottomNavRoute(route: String?): Boolean {
            return route in bottomNavRoutes
        }
    }
}
