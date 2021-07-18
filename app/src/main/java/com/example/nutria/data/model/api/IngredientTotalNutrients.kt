package com.example.nutria.data.model.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientTotalNutrients(
    @Json(name = "FAT")     var fatData: IngredientFatData? = null,
    @Json(name = "CHOLE")   var cholesterolData: IngredientCholesterolData? = null,
    @Json(name = "NA")      var sodiumData: IngredientSodiumData? = null,
    @Json(name = "CHOCDF")  var carbohydrateData: IngredientCarbohydrateData? = null,
    @Json(name = "PROCNT")  var proteinData: IngredientProteinData? = null,
    @Json(name = "VITC")    var vitaminData: IngredientVitaminData? = null,
    @Json(name = "CA")      var calciumData: IngredientCalciumData? = null,
    @Json(name = "FE")      var ironData: IngredientIronData? = null,
    @Json(name = "K")       var potassiumData: IngredientPotassiumData? = null
): Parcelable {

}