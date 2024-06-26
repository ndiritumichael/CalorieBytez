package com.devmike.caloriebytez.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CalorieBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    val showBottomNav =
        TopLevelDestinations.entries.map { it.route::class }.any { route ->
            currentDestination?.hierarchy?.any {
                it.hasRoute(route)
            } == true
        }

    AnimatedVisibility(visible = showBottomNav) {
        BottomAppBar {
            TopLevelDestinations.entries.map { bottomNavigationItem ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.hasRoute(bottomNavigationItem.route::class) } == true

                if (currentDestination != null) {
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(bottomNavigationItem.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector =
                                    if (isSelected) {
                                        bottomNavigationItem.selectedIcon
                                    } else {
                                        bottomNavigationItem.unselectedIcon
                                    },
                                contentDescription = bottomNavigationItem.label,
                            )
                        },
                        alwaysShowLabel = isSelected,
                        label = {
                            Text(bottomNavigationItem.label)
                        },
                    )
                }
            }
        }
    }
}
