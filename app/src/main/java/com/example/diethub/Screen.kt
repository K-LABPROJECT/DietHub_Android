package com.example.diethub

sealed class Screen(val route: String) {
    object MyPage : Screen("my_page")
    object ChangeMySpec : Screen("change_my_spec")
    object RestaurantPage : Screen("restaurant_page")
    object RecipePage : Screen("recipe_page")
    object RankPage : Screen("rank_page")
    object HomePage : Screen("home_page")
    object LoginPage : Screen("login_page")
    object SignupPage : Screen("signup_page")
}