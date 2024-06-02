package com.example.diethub.pages

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeList(navController: NavController,viewModel: RestaurantViewModel) {
    var restaurant by remember{mutableStateOf(viewModel.restaurant.value)}
    var deleteMenu by remember {mutableStateOf<Menu?>(null)}
    val deleteRecipeStatus by viewModel.deleteRecipeStatus.collectAsState()

    LaunchedEffect(deleteRecipeStatus) {
        deleteRecipeStatus?.let { result ->
            if (result.isSuccess) {
                Log.d("RecipeList", "Delete Success")
                viewModel.loadRestaurantInfo(restaurant!!.restaurantId.toString())
            }
        }
    }
    LazyColumn {
        items(viewModel.restaurant.value!!.menus) {menu ->
            Text(
                text = menu.recipeName,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { navController.navigate(Screen.RecipePage.route+"/${restaurant!!.restaurantId}/${menu.recipeId}") },
                        onLongClick = { deleteMenu = menu

                            },
                    )
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }

    if (deleteMenu != null){
        MenuDeleteAlertDialog(
            onDismissRequest = {deleteMenu = null},
            onConfirmation = {
                viewModel.deleteRecipe(restaurant!!.restaurantId, deleteMenu!!.recipeId)
                deleteMenu = null
            },
            dialogTitle = "\"${deleteMenu!!.recipeName}\"레시피를 삭제하시겠습니까?",
            dialogText = "삭제 후 복구할 수 없습니다.",
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
            Icon(icon, contentDescription = "delete")
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