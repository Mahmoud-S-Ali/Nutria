package com.example.nutria.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nutria.NutriaApp
import com.example.nutria.data.network.ErrorHandler
import com.example.nutria.data.network.StateCodes
import com.example.nutria.di.component.DaggerFragmentComponent
import com.example.nutria.di.component.FragmentComponent
import com.example.nutria.di.module.FragmentModule
import com.example.nutria.ui.base.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding?, V : BaseViewModel?> : Fragment() {

    private var activity: BaseActivity<T>? = null
    private var rootView: View? = null
    protected var viewDataBinding: T? = null

    @set:Inject
    protected var viewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable() : Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId() : Int


    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection(buildComponent)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)

        ViewModelProvider(this).get(getViewModelClass()).let {
            setupMainObservers(it)
        }
    }

    abstract fun getViewModelClass():Class<V>

    @MainThread
    private fun <N: BaseViewModel> setupDataStateObserver(viewModel: N?){
        viewModel?.dataState?.observe(this, Observer { result ->
            if(result == StateCodes.LOADING)
                showLoading()
            else {
                hideLoading()
                if (result != StateCodes.SUCCESSFUL)
                    ErrorHandler.showAlertForError(result, context)
            }
        })
    }

    @MainThread
    @CallSuper
    protected open fun setupMainObservers(viewModel: V?){
        setupDataStateObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = viewDataBinding?.root
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity: BaseActivity<T> = context as BaseActivity<T>
            this.activity = activity
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.setVariable(getBindingVariable(), viewModel)
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.executePendingBindings()
    }

    val baseActivity: BaseActivity<T>?
        get() = activity

    fun hideKeyboard() {
        activity?.hideKeyboard()
    }


    abstract fun performDependencyInjection(buildComponent: FragmentComponent?)

    private val buildComponent: FragmentComponent
        get() = DaggerFragmentComponent.builder()
            .appComponent(((context?.applicationContext) as NutriaApp).appComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    protected fun showLoading() {
        activity?.showLoading()
    }

    protected fun hideLoading() {
        activity?.hideLoading()
    }
}