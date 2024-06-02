package com.example.diethub.api

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("recipeName") val recipeName: String,
    @SerializedName("recipeDetail") val recipeDetail: String
)