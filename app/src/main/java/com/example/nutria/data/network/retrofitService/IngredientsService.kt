package com.example.nutria.data.network.retrofitService

import com.example.nutria.data.model.api.IngredientDetailsRequest
import com.example.nutria.data.model.api.IngredientDetailsResponse
import retrofit2.Call
import retrofit2.http.*

interface IngredientsService {
    @Headers("Content-Type: application/json")
    @POST("api/nutrition-details")
    fun getIngredientDetails(
        @Query("app_id")  appId: String?,
        @Query("app_key") appKey: String?,
        @Body request: IngredientDetailsRequest
    ): Call<IngredientDetailsResponse>
}