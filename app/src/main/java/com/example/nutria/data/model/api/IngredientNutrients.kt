package com.example.nutria.data.model.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientNutrients(
    @Json(name = "ENERC_KCAL") var energyData: IngredientEnergyData? = null,
): Parcelable {

}