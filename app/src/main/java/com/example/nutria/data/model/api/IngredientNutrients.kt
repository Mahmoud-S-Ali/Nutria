package com.example.nutria.data.model.api

import com.squareup.moshi.Json

class IngredientNutrients(
    @Json(name = "ENERC_KCAL") var energyData: IngredientEnergyData? = null,
) {

}