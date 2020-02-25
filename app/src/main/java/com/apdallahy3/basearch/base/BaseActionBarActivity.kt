package com.apdallahy3.basearch.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.apdallahy3.basearch.R
import com.apdallahy3.basearch.databinding.AppBarBinding


abstract class BaseActionBarActivity<T : ViewDataBinding> : BaseActivity<T>() {
    override fun getLayoutRes(): Int = R.layout.app_bar
    protected lateinit var headerDataBinding: AppBarBinding

    override fun performDataBinding() {

        binding = DataBindingUtil.inflate(layoutInflater, getLayoutRes(), null, false)
        setContentView(headerDataBinding.root)
        binding.executePendingBindings()
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        headerDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.app_bar,
            null,
            false
        )
        super.onCreate(savedInstanceState)
     }

    fun setScreenTitle(title: Int) {
        headerDataBinding.textHeader.text = (getString(title))
    }

    fun setScreenTitle(title: String) {
        headerDataBinding.textHeader.text = title ?: ""
    }


    fun getScreenTitle(): String = headerDataBinding.textHeader.text.toString()


}