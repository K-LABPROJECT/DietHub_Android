package com.example.diethub.api
import com.google.gson.annotations.SerializedName

data class Restaurant (
    @SerializedName("restaurantId") val restaurantId : Int,
    @SerializedName("title") val title : String,
    @SerializedName("ownerId") val ownerId : Int,
    @SerializedName("ownerUsername") val ownerUsername : String,
    @SerializedName("menus") val menus : List<Menu>

)
data class Menu(
    @SerializedName("recipeId") val recipeId: Int,
    @SerializedName("recipeName") val recipeName: String
)


