package com.example.nutria.data.model.api

import com.squareup.moshi.Json

class IngredientParsedData(
    @Json(name = "food") var name: String? = null,
    @Json(name = "quantity") var quantity: Float = 0f,
    @Json(name = "measure") var unit: String? = null,
    @Json(name = "nutrients") var nutrients: IngredientNutrients? = null
) {

}