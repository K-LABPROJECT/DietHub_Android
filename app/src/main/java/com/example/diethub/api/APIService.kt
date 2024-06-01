package com.example.diethub.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("restaurants/{restaurantId}/recipes/{recipeId}") // 레시피 조회
    suspend fun getRecipe(@Path("restaurantId") restaurantId: Int, @Path("recipeId") recipeId: Int): Recipe
    @GET("restaurants/{restaurantId}/info") //식당 정보 조회
    suspend fun getRestaurantInfo(@Path("restaurantId") restaurantId: String) :Restaurant

    @GET("restaurants/search")
    suspend fun searchRestaurant(@Query("title") title: String): RestaurantSearchResult

    @POST("restaurants/{restaurantId}/recipes") //레시피 추가
    suspend fun addRecipe(
        @Path("restaurantId") restaurantId: Int,
        @Body recipe: Recipe
    ): Response<Unit>

    @DELETE("restaurants/{restaurantId}/recipes/{recipeId}")
    suspend fun deleteRecipe(
        @Path("restaurantId") restaurantId: Int,
        @Path("recipeId") recipeId: Int
    ) : Response<Unit>

    @GET("members/{memberId}/info") // 마이페이지 정보 조회
    suspend fun getMyinfo(@Path("memberId") memberId : Int) : MyInfo

}