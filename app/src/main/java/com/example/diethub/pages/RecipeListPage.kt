package com.example.diethub.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diethub.RestaurantViewModel
import com.example.diethub.Screen

@Composable
fun RecipeList(navController: NavController,viewModel: RestaurantViewModel) {
    val restaurant = viewModel.restaurant.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        restaurant?.menus?.forEach { menu ->
            Text(
                text = menu.recipeName,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screen.RecipePage.route+"/${restaurant.restaurantId}/${menu.recipeId}")
                    }
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}
