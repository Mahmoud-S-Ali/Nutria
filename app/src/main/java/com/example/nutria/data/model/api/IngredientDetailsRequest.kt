package com.example.nutria.data.model.api

import com.squareup.moshi.Json

class IngredientDetailsRequest(
    @Json(name = "ingr") val ingredients: List<String>
) {
}