package com.example.diethub.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diethub.RestaurantViewModel
import com.example.diethub.Screen
import com.example.diethub.api.Menu

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeList(navController: NavController,viewModel: RestaurantViewModel) {
    val restaurant = viewModel.restaurant.value
    var deleteMenu by remember {mutableStateOf<Menu?>(null)}
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        restaurant?.menus?.forEach { menu ->
            Text(
                text = menu.recipeName,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { navController.navigate(Screen.RecipePage.route+"/${restaurant.restaurantId}/${menu.recipeId}") },
                        onLongClick = { deleteMenu = menu },
                    )
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }

    if (deleteMenu != null){
        MenuDeleteAlertDialog(
            onDismissRequest = {deleteMenu = null},
            onConfirmation = {


                deleteMenu = null
            },
            dialogTitle = "",
            dialogText = "",
            icon = Icons.Default.Delete)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDeleteAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}