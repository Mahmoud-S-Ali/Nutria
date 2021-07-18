package com.example.nutria.ui.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutria.BuildConfig
import com.example.nutria.data.DataManager
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.data.network.StateCodes
import com.example.nutria.ui.base.BaseCallBack
import com.example.nutria.ui.base.BaseViewModel
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class IngredientsViewModel(private val dataManager: DataManager): BaseViewModel() {

    private var _ingredientsDetailsResult: MutableLiveData<IngredientDetailsResponse> = MutableLiveData()
    val ingredientsDetailsResult: LiveData<IngredientDetailsResponse>
        get() = _ingredientsDetailsResult


    fun getIngredientsDetails(ingredientsStr: String) {
        dataManager?.getIngredientsDetails(parseIngredientsStr(ingredientsStr),
            _ingredientsDetailsResult, _dataState)
    }

    /*
    * Making sure the entered ingredients string is valid
    * */
    fun validateIngredientsStr(ingredientsStr: String): Boolean {
        return !ingredientsStr.trim().isEmpty()
    }

    /*
    * Converting the entered ingredients string to a list of ingredients
    * */
    private fun parseIngredientsStr(ingredientsStr: String): List<String> {
        val ingredients = mutableListOf<String>()
        for (ingr in ingredientsStr.split("\n"))
            ingredients.add(ingr)

        return ingredients
    }

    /*
    * If malformed data is sent, the ingredient parsed data is returned with null
    * This is how we know we should show error message in this scenario
    * */
    fun validateResult(ingredientsDetailsResult: IngredientDetailsResponse?): Boolean {
        if (ingredientsDetailsResult == null)
            return false

        if (ingredientsDetailsResult?.ingredients == null)
            return false

        for (ingr in ingredientsDetailsResult?.ingredients!!)
            if (ingr.ingredientsParsedData == null)
                return false

        return true
    }
}