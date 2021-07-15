package com.example.nutria.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.nutria.NutriaApp
import com.example.nutria.di.component.ActivityComponent
import com.example.nutria.di.component.DaggerActivityComponent
import com.example.nutria.di.module.ActivityModule
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {

    protected var binding: T? = null
        private set

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection(buildComponent)
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private val buildComponent: ActivityComponent
        get() = DaggerActivityComponent.builder()
            .appComponent((application as NutriaApp).appComponent)
            .activityModule(ActivityModule(this))
            .build()

    abstract fun performDependencyInjection(buildComponent: ActivityComponent?)

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding?.executePendingBindings()
    }

    open fun showLoading() {
    }

    open fun hideLoading() {
    }
}