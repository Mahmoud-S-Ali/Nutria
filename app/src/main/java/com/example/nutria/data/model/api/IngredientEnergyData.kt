package com.example.nutria.data.model.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientEnergyData(
    @Json(name = "quantity") var energyQuantity: Float = 0f,
    @Json(name = "unit") var energyUnit: String? = null,
): Parcelable {
}