package com.example.nutria.ui.totalNutrients

import com.example.nutria.data.DataManager
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.ui.base.BaseViewModel

class TotalNutrientsViewModel(dataManager: DataManager): BaseViewModel() {
    var ingredientsDetails: IngredientDetailsResponse? = null
}