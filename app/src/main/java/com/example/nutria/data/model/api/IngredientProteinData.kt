package com.example.nutria.data.model.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientProteinData(
    @Json(name = "quantity") var quantity: Float = 0f,
    @Json(name = "unit") var unit: String? = null
) : Parcelable {
}