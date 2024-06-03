package com.example.diethub

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diethub.api.Restaurant
import com.example.diethub.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val _restaurant = mutableStateOf<Restaurant?>(null)
    val restaurant: State<Restaurant?> = _restaurant

    private val _deleteRecipeStatus = MutableStateFlow<Result<Unit>?>(null)
    val deleteRecipeStatus: StateFlow<Result<Unit>?> = _deleteRecipeStatus.asStateFlow()

    // 식당 정보 로드하는 함수
    fun loadRestaurantInfo(restaurantId: String) {
        viewModelScope.launch {
            try {
                val restaurantInfo = RetrofitInstance.api.getRestaurantInfo(restaurantId)
                _restaurant.value = restaurantInfo
            } catch (e: Exception) {
                Log.e("RestaurantViewModel", "Error loading restaurant info", e)
            }
        }
    }

    // 레시피 삭제 함수
    fun deleteRecipe(restaurantId:Int, recipeId: Int){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deleteRecipe(restaurantId, recipeId)
                if (response.isSuccessful) {
                    _deleteRecipeStatus.value = Result.success(Unit)
                } else {
                    _deleteRecipeStatus.value = Result.failure(Exception("Failed to delete recipe"))
                }
            } catch (e: Exception) {
                Log.e("RestaurantViewModel", "Error deleting menu", e)
            }
        }
    }
    
}