package com.pm.savings.presentation.core.navigation

import com.pm.savings.R


sealed class BottomBarDestinations(
    val route: String,
    val notSelectedIcon: Int,
    val selectedIcon: Int
) {
    object Home : BottomBarDestinations(
        route = Routes.HOME,
        notSelectedIcon = R.drawable.home,
        selectedIcon = R.drawable.home_filled
    )

    object Savings : BottomBarDestinations(
        route = Routes.SAVINGS,
        notSelectedIcon = R.drawable.savings,
        selectedIcon = R.drawable.savings_filled
    )

    object Stats : BottomBarDestinations(
        route = Routes.STATS,
        notSelectedIcon = R.drawable.stats,
        selectedIcon = R.drawable.stats_filled
    )

    object Profile : BottomBarDestinations(
        route = Routes.PROFILE,
        notSelectedIcon = R.drawable.profile,
        selectedIcon = R.drawable.profile_filled
    )
}

