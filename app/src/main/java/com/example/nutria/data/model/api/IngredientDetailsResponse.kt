package com.example.nutria.data.model.api

import com.squareup.moshi.Json

class IngredientDetailsResponse(
    @Json(name = "calories") var totalCalories: Int = 0,
    @Json(name = "totalWeight") var totalWeight: Float = 0f,
    @Json(name = "ingredients") var ingredients: List<Ingredient>? = null
) {

}