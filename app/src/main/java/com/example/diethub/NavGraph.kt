package com.example.diethub

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Restaurant") {
        composable(route = "Restaurant"){
            Restaurant(navController)
        }
        composable(route = "Recipe") {
            Recipe()
        }
    }
}