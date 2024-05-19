package com.example.diethub

sealed class Screen(val route: String) {
    object MyPage : Screen("my_page")
    object ChangeMySpec : Screen("change_my_spec")
}