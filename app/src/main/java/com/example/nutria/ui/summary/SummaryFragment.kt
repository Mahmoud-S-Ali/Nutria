package com.example.nutria.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutria.BR
import com.example.nutria.R
import com.example.nutria.base.BaseFragment
import com.example.nutria.data.model.api.IngredientParsedData
import com.example.nutria.databinding.FragmentSummaryBinding
import com.example.nutria.di.component.FragmentComponent
import javax.inject.Inject

class SummaryFragment : BaseFragment<FragmentSummaryBinding, SummaryViewModel>() {

    @set:Inject
    internal var summaryAdapter: SummaryRecyclerAdapter? = null

    @set:Inject
    internal var layoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        arguments?.let {
            viewModel?.ingredientsDetails =
                SummaryFragmentArgs.fromBundle(it).ingredientsDetails
        }

        initViews()
        loadData()

        return view
    }

    override fun getBindingVariable(): Int {
        return BR.summaryViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_summary
    }

    override fun getViewModelClass(): Class<SummaryViewModel> {
        return SummaryViewModel::class.java
    }

    override fun performDependencyInjection(buildComponent: FragmentComponent?) {
        buildComponent?.inject(this)
    }

    private fun initViews() {
        with(viewDataBinding?.rvSummary) {
            this?.adapter = summaryAdapter
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun loadData() {
        viewModel?.ingredientsDetails?.ingredients?.let { summaryAdapter?.notifyWithNewItems(it) }
    }
}