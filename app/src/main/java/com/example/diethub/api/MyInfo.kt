package com.example.diethub.api

import com.google.gson.annotations.SerializedName

data class MyInfo(
    @SerializedName("username") val username : String,
    @SerializedName("height") val height : Float,
    @SerializedName("weight") val weight : Float,
    @SerializedName("muscleMass") val muscleMass : Float,
    @SerializedName("weightLoss") val weightLoss : Float
)
