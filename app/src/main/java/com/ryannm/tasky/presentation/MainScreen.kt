package com.ryannm.tasky.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ryannm.tasky.presentation.detail.DetailScreen
import com.ryannm.tasky.presentation.list.ListScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ListScreen.route) {
        composable(ListScreen.route) {
            ListScreen.screen { id ->
                navController.navigateSingleTopTo(DetailScreen.route(id))
            }
        }

        composable(DetailScreen.routeWithArguments, arguments = DetailScreen.arguments) {
            DetailScreen.screen {
                navController.navigateSingleTopTo(ListScreen.route)
            }
        }
    }
}


private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }
