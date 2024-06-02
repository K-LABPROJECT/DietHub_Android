package com.example.diethub

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diethub.api.Restaurant
import com.example.diethub.api.RetrofitInstance
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    var searchResults by mutableStateOf<List<Restaurant>>(emptyList())
        private set
    fun searchRestaurant(title: String) {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.searchRestaurant(title)
                Log.d("search",result.restaurants.toString())
                    searchResults = result.restaurants
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error searching restaurant", e)
                e.printStackTrace()
            }
        }
    }
}