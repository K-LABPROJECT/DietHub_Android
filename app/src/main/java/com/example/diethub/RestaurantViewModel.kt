package com.example.diethub

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diethub.api.Restaurant
import com.example.diethub.api.RetrofitInstance
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val _restaurant = mutableStateOf<Restaurant?>(null)
    val restaurant: State<Restaurant?> = _restaurant

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
}