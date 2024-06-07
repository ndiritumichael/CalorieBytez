package com.devmike.caloriebytez.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devmike.domain.models.AppDestinations
import com.devmike.fooddetails.FoodDetailsScreen
import com.devmike.saveditems.SavedItemsScreen
import com.devmike.search.SearchScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestinations = AppDestinations.SearchScreen,
) {
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
        composable<AppDestinations.SearchScreen> {
            SearchScreen { foodName ->
                navController.navigate(AppDestinations.FoodDetails(name = foodName))
            }
        }

        composable<AppDestinations.FoodDetails> { backStackEntry ->
            FoodDetailsScreen {
                navController.navigateUp()
            }
        }

        composable<AppDestinations.SavedFood> {
            SavedItemsScreen { foodName ->
                navController.navigate(AppDestinations.FoodDetails(name = foodName))
            }
        }
    }
}
