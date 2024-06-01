package com.example.diethub.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.diethub.RecipeViewModel
import com.example.diethub.Screen
import com.example.diethub.api.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipePage(viewModel: RecipeViewModel = viewModel(), navController: NavHostController, restaurantId: Int) {
    var recipeName by remember { mutableStateOf("") }
    var recipeDetail by remember { mutableStateOf("") }
    var addRecipeResult : Boolean? = null
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            label = { Text("Recipe Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = recipeDetail,
            onValueChange = { recipeDetail = it },
            label = { Text("Recipe Detail") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                addRecipeResult = viewModel.addRecipe(
                    restaurantId,
                    Recipe(recipeName, recipeDetail)
                )
                navController.popBackStack(route = Screen.RestaurantPage.route+"/$restaurantId", inclusive = false)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Recipe")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (addRecipeResult == true)
            Toast.makeText(context, "레시피 등록 완료!", Toast.LENGTH_LONG).show()
        else if (addRecipeResult == false)
            Toast.makeText(context, "레시피 등록 실패..", Toast.LENGTH_LONG).show()





    }

}