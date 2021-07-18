package com.example.nutria.ui.totalNutrients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.util.Util
import com.example.nutria.BR
import com.example.nutria.R
import com.example.nutria.base.BaseFragment
import com.example.nutria.databinding.FragmentTotalNutrientsBinding
import com.example.nutria.di.component.FragmentComponent
import com.example.nutria.ui.summary.SummaryFragmentArgs
import com.example.nutria.utils.Utils
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlinx.android.synthetic.main.fragment_total_nutrients.*

class TotalNutrientsFragment: BaseFragment<FragmentTotalNutrientsBinding, TotalNutrientsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        arguments?.let {
            viewModel?.ingredientsDetails =
                TotalNutrientsFragmentArgs.fromBundle(it).ingredientsDetails
        }

        initViews()

        return view
    }

    override fun getBindingVariable(): Int {
        return BR.totalNutrientsViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_total_nutrients
    }

    override fun getViewModelClass(): Class<TotalNutrientsViewModel> {
        return TotalNutrientsViewModel::class.java
    }

    override fun performDependencyInjection(buildComponent: FragmentComponent?) {
        buildComponent?.inject(this)
    }


    private fun initViews() {
        viewModel?.ingredientsDetails?.let {
            with(viewDataBinding) {
                this?.tvCaloriesValue?.text = Utils.getTruncatedFloatStr(it.totalCalories)

                it.totalNutrients?.let {
                    this?.tvFatValue?.text =
                        Utils.getTruncatedFloatStr(it.fatData?.quantity ?: 0f) + " " + it.fatData?.unit

                    this?.tvCholesterolValue?.text =
                        Utils.getTruncatedFloatStr(it.cholesterolData?.quantity ?: 0f) + " " + it.cholesterolData?.unit

                    this?.tvSodiumValue?.text =
                        Utils.getTruncatedFloatStr(it.sodiumData?.quantity ?: 0f) + " " + it.sodiumData?.unit

                    this?.tvCarbohydrateValue?.text =
                        Utils.getTruncatedFloatStr(it.carbohydrateData?.quantity ?: 0f) + " " + it.carbohydrateData?.unit

                    this?.tvProteinValue?.text =
                        Utils.getTruncatedFloatStr(it.proteinData?.quantity ?: 0f) + " " + it.proteinData?.unit

                    this?.tvVitaminValue?.text =
                        Utils.getTruncatedFloatStr(it.vitaminData?.quantity ?: 0f) + " " + it.vitaminData?.unit

                    this?.tvCalciumValue?.text =
                        Utils.getTruncatedFloatStr(it.calciumData?.quantity ?: 0f) + " " + it.calciumData?.unit

                    this?.tvIronValue?.text =
                        Utils.getTruncatedFloatStr(it.ironData?.quantity ?: 0f) + " " + it.ironData?.unit

                    this?.tvPotassiumValue?.text =
                        Utils.getTruncatedFloatStr(it.potassiumData?.quantity ?: 0f) + " " + it.potassiumData?.unit
                }
            }
        }
    }
}