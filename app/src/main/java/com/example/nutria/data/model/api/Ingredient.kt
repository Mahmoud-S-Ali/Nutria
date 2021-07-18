package com.example.nutria.data.model.api

import com.squareup.moshi.Json

class Ingredient(
    @Json(name = "text") var ingredientText: String? = null,
    @Json(name = "parsed") var ingredientsParsedData: List<IngredientParsedData>? = null
) {

}