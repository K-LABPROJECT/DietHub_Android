package com.example.diethub

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diethub.pages.ChangeMySpec
import com.example.diethub.pages.HomePage
import com.example.diethub.pages.MyPage
import com.example.diethub.pages.RankPage
import com.example.diethub.pages.Recipe
import com.example.diethub.pages.Restaurant

@Composable
fun NavGraph(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    NavHost(navController = navController, startDestination = Screen.HomePage.route) {
        composable(Screen.MyPage.route) {
            MyPage(navController = navController, viewModel = userViewModel)
        }
        composable(Screen.ChangeMySpec.route) {
            ChangeMySpec(navController = navController, viewModel = userViewModel)
        }
        composable(route = Screen.RecipePage.route) {
            Recipe(navController = navController)
        }
        composable(route = Screen.RankPage.route){
            RankPage(navController = navController)
        }
        composable(route = Screen.RestaurantPage.route) {
            Restaurant(navController = navController)
        }
        composable(route = Screen.HomePage.route){
            HomePage(navController = navController, userViewModel = userViewModel)

        }
    }
}