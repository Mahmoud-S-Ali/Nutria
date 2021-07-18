package com.example.nutria.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutria.data.model.api.IngredientDetailsResponse

interface ApiHelper {
    fun getIngredientsDetails(ingredients: List<String>,
                              result: MutableLiveData<IngredientDetailsResponse>,
                              dataState: MutableLiveData<StateCodes>)
}