package com.pm.savings.presentation.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.textColor

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarDestinations.Home,
        BottomBarDestinations.Savings,
        BottomBarDestinations.Stats,
        BottomBarDestinations.Profile
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination?.route

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(),
        visible = currentDestination == BottomBarDestinations.Home.route
                || currentDestination == BottomBarDestinations.Savings.route
                || currentDestination == BottomBarDestinations.Stats.route
                || currentDestination == BottomBarDestinations.Profile.route,
        exit = shrinkVertically(
            animationSpec = tween(300)
        ),
        enter = expandVertically(
            animationSpec = tween(300)
        )
    ) {
        NavigationBar(
            containerColor = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .height(Constants.BOTTOM_BAR_HEIGHT)
        ) {
            screens.forEach { destination ->
                val selected = destination.route == currentDestination
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.popBackStack()
                            navController.navigate(destination.route)
                        }
                    },
                    icon = {
                        val iconPath =
                            if (selected) destination.selectedIcon else destination.notSelectedIcon
                        Icon(
                            painter = painterResource(id = iconPath),
                            contentDescription = destination.route
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = textColor,
                        unselectedIconColor = textColor.copy(alpha = 0.5f),
                        indicatorColor = Color.White
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(navController = rememberNavController())
}