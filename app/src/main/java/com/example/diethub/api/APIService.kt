package com.example.diethub.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("restaurants/{restaurantId}/recipes/{recipeId}") // 레시피 조회
    suspend fun getRecipe(@Path("restaurantId") restaurantId: Int, @Path("recipeId") recipeId: Int): Recipe
    @GET("restaurants/{restaurantId}/info") //식당 정보 조회
    suspend fun getRestaurantInfo(@Path("restaurantId") restaurantId: String) :Restaurant

    @PUT("restaurants/{restaurantId}/recipes") //레시피 추가
    suspend fun addRecipe(
        @Path("restaurantId") restaurantId: Int,
        @Body recipe: Recipe
    ): Response<Unit>

    //아래에 api 추가해주세요
}