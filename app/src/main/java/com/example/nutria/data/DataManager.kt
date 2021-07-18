package com.example.nutria.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.data.network.StateCodes

interface DataManager {
    fun getIngredientsDetails(ingredients: List<String>,
                              result: MutableLiveData<IngredientDetailsResponse>,
                              dataState: MutableLiveData<StateCodes>
    )
}