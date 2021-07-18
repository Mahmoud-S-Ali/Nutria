package com.example.nutria.data.model.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class Ingredient(
    @Json(name = "text") var ingredientText: String? = null,
    @Json(name = "parsed") var ingredientsParsedData: List<IngredientParsedData>? = null
): Parcelable {

}