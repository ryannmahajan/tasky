package com.ryannm.tasky.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ryannm.tasky.presentation.detail.DetailScreen
import com.ryannm.tasky.presentation.list.ListScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = DetailScreen.route) {
        composable(ListScreen.route) {
            ListScreen.screen()
        }

        composable(DetailScreen.route) {
            DetailScreen.screen {
                navController.navigate(ListScreen.route)
            }
        }
    }
}