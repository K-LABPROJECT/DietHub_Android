package com.example.diethub

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.diethub.api.Recipe
import com.example.diethub.api.RetrofitInstance

class RecipeViewModel : ViewModel() {
    var recipe by mutableStateOf(Recipe("loading..","loading.."))
        private set

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
}