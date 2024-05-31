package com.example.diethub


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diethub.pages.ChangeMySpec
import com.example.diethub.pages.DietHubForm
import com.example.diethub.pages.HomePage
import com.example.diethub.pages.LoginPage
import com.example.diethub.pages.MyPage
import com.example.diethub.pages.RankPage
import com.example.diethub.pages.Recipe
import com.example.diethub.pages.Restaurant

@Composable
fun NavGraph(navController: NavHostController, userViewModel: UserViewModel = viewModel(), RestaurantViewModel: RestaurantViewModel = viewModel()) {
    NavHost(navController = navController, startDestination = Screen.RestaurantPage.route) {
        composable(Screen.MyPage.route) {
            MyPage(navController = navController, viewModel = userViewModel)
        }
        composable(Screen.ChangeMySpec.route) {
            ChangeMySpec(navController = navController, viewModel = userViewModel)
        }
        composable(route = Screen.RecipePage.route+"/{restaurantId}/{recipeId}", arguments = listOf(
            navArgument("restaurantId"){
                type = NavType.IntType
            },
            navArgument("recipeId"){
                type = NavType.IntType
            }
        )) {
            Recipe(navController = navController, recipeViewModel = viewModel(), it.arguments!!.getInt("restaurantId"), it.arguments!!.getInt("recipeId"))
        }
        composable(route = Screen.RankPage.route){
            RankPage(navController = navController)
        }
        composable(route = Screen.RestaurantPage.route) {
            Restaurant(navController = navController, viewModel = RestaurantViewModel)
        }
        composable(route = Screen.HomePage.route){
            HomePage(navController = navController, userViewModel = userViewModel)
        }
        composable(route = Screen.LoginPage.route) {
            LoginPage(navController = navController)
        }
        composable(route = Screen.SignupPage.route) {
            DietHubForm(navController = navController)
        }
    }
}