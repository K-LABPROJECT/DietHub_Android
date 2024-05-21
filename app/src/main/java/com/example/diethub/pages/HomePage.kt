package com.example.diethub.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.diethub.Screen
import com.example.diethub.UserViewModel

@Composable
fun HomePage(navController: NavController, userViewModel: UserViewModel) {
    Text("Home")
    Column {
        Button(onClick = { navController.navigate(Screen.MyPage.route) }) {
            Text(text = "MyPage")
        }
        Button(onClick = { navController.navigate(Screen.RankPage.route) }) {
            Text(text = "RankPage")
        }
        Button(onClick = { navController.navigate(Screen.RestaurantPage.route) }) {
            Text(text = "RestaurantPage")
        }
        Button(onClick = { navController.navigate(Screen.RecipePage.route){
            popUpTo(Screen.HomePage.route){
                inclusive = true
            }
        } }) {
            Text(text = "RecipePage")
            /*TODO RecipePage는 RestaurantPage 내부에서 호출*/
        }
    }


}