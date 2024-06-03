package com.example.diethub.api

import com.google.gson.annotations.SerializedName

data class MyInfo(
    @SerializedName("username") val username : String,
    @SerializedName("height") val height : Float,
    @SerializedName("firstWeight") val firstWeight : Float,
    @SerializedName("weight") val weight : Float,
    @SerializedName("muscleMass") val muscleMass : Float,
    @SerializedName("targetWeight") val targetWeight : Float,
    @SerializedName("weightLoss") val weightLoss : Float,
    @SerializedName("followers") val followers : Int,
    @SerializedName("following") val following : Int,
    @SerializedName("characterProfileId") val characterProfileId : Int
)
