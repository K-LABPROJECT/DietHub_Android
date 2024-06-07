package com.example.diethub

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.diethub.api.Recipe
import com.example.diethub.api.RetrofitInstance

class RecipeViewModel : ViewModel() {
    var recipe by mutableStateOf(Recipe("loading..","loading..", "loading..."))
        private set

    var success by mutableStateOf<Boolean?>(null)

    fun fetchRecipe(restaurantId: Int, recipeId : Int) {
        viewModelScope.launch {
            try {
                recipe = RetrofitInstance.api.getRecipe(restaurantId, recipeId)
                Log.d("recipename",recipe.recipeName)
            } catch (e: Exception) {
                // Handle the error appropriately
                Log.d("recipe",e.toString())
                e.printStackTrace()
            }
        }
    }

    fun addRecipe(restaurantId: Int, recipe: Recipe) : Boolean?{
        viewModelScope.launch {
            try {
                Log.d("recipe add", "$restaurantId" + " ${recipe.recipeName} ${recipe.recipeDetail}")
                val response = RetrofitInstance.api.addRecipe(restaurantId, recipe)
                if (response.isSuccessful) {
                    success = true
                } else {
                    success = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return success
    }
}