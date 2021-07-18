package com.example.nutria.data.model.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientParsedData(
    @Json(name = "food") var name: String? = null,
    @Json(name = "quantity") var quantity: Float = 0f,
    @Json(name = "measure") var unit: String? = null,
    @Json(name = "weight") var weight: Float = 0f,
    @Json(name = "nutrients") var nutrients: IngredientNutrients? = null
): Parcelable {

}