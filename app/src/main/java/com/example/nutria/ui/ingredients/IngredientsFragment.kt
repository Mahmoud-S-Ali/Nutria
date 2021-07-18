package com.example.nutria.ui.ingredients

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nutria.BR
import com.example.nutria.R
import com.example.nutria.base.BaseFragment
import com.example.nutria.data.model.api.IngredientDetailsRequest
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.data.network.ErrorHandler
import com.example.nutria.data.network.StateCodes
import com.example.nutria.databinding.FragmentIngredientsBinding
import com.example.nutria.di.component.FragmentComponent
import kotlinx.android.synthetic.main.fragment_ingredients.*
import java.lang.Error

class IngredientsFragment : BaseFragment<FragmentIngredientsBinding, IngredientsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initViews()
        return view
    }

    private fun initViews() {
        addAnalyzeBtnClickListener()
        addIngrEditTextChangeListener()
    }

    override fun setupMainObservers(viewModel: IngredientsViewModel?) {
        super.setupMainObservers(viewModel)

        viewModel?.ingredientsDetailsResult?.observe(this, { ingredientDetails ->
            with(viewModel) {
                if (!validateResult(ingredientDetails))
                    ErrorHandler.showAlertForError(StateCodes.UNPROCESSABLE_ENTITY, context)
                else
                    navigateToSummaryFragment(ingredientDetails)
            }
        })
    }

    override fun getBindingVariable(): Int {
        return BR.ingredientsDetailsViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_ingredients
    }

    override fun getViewModelClass(): Class<IngredientsViewModel> {
        return IngredientsViewModel::class.java
    }

    override fun performDependencyInjection(buildComponent: FragmentComponent?) {
        buildComponent?.inject(this)
    }

    private fun addAnalyzeBtnClickListener() {
        viewDataBinding?.btnAnalyze?.setOnClickListener {
            viewModel?.getIngredientsDetails(tie_ingredients?.text.toString())
        }
    }

    private fun addIngrEditTextChangeListener() {
        viewDataBinding?.tieIngredients?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewDataBinding?.btnAnalyze?.isEnabled =
                    viewModel?.validateIngredientsStr(p0.toString()) == true
            }
        })
    }

    private fun navigateToSummaryFragment(ingredientsDetails: IngredientDetailsResponse) {
        val action = IngredientsFragmentDirections.actionIngredientsFragmentToSummaryFragment(ingredientsDetails)
        findNavController().navigate(action)
    }
}