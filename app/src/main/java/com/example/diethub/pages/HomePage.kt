package com.example.diethub.pages

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diethub.R
import com.example.diethub.Screen
import com.example.diethub.UserViewModel

@Composable
fun HomePage(navController: NavController, userViewModel: UserViewModel) {
    Text("Home")
    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            IconButton(onClick = { navController.navigate(Screen.MyPage.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.my_button),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            IconButton(onClick = { navController.navigate(Screen.RankPage.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.search_button),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            IconButton(onClick = { navController.navigate(Screen.RestaurantPage.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.menu_button),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            Button(onClick = {
                navController.navigate(Screen.RecipePage.route+"/2/2") {
                    popUpTo(Screen.HomePage.route) {
//                        inclusive = true
                    }
                }
            }) {
                Text(text = "RecipePage(To be deleted)")
                /*TODO RecipePage는 RestaurantPage 내부에서 호출*/
            }
            Button(onClick = {
                navController.navigate(Screen.RecipePage.route+"/1/1") {
                    popUpTo(Screen.HomePage.route) {
//                        inclusive = true
                    }
                }
            }) {
                Text(text = "RecipePage2(To be deleted)")
                /*TODO RecipePage는 RestaurantPage 내부에서 호출*/
            }
        }
    }
}