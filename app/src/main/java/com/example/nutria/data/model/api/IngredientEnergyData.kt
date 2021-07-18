package com.example.nutria.data.model.api

import com.squareup.moshi.Json

class IngredientEnergyData(
    @Json(name = "quantity") var energyQuantity: Int? = null,
    @Json(name = "unit") var energyUnit: String? = null,
) {

}