package com.example.nutria.ui.summary

import com.example.nutria.data.DataManager
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.ui.base.BaseViewModel

class SummaryViewModel(dataManager: DataManager): BaseViewModel() {
    var ingredientsDetails: IngredientDetailsResponse? = null
}