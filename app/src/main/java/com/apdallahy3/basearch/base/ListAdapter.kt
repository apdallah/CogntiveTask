package com.gambia.android.base

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class ListAdapter<MODEL , BINDING : ViewDataBinding>(ctx: Context?, private val viewModel: ListViewModel<MODEL , *>?) :
    
    BaseRVAdapter<MODEL?, BINDING>(ctx, null) {
    
    abstract fun onBind(binding: BINDING?, item: MODEL?, position: Int)
    
    
    override fun bindDataViewHolder(binding: BINDING?, item: MODEL?, position: Int) {
        onBind(binding , item , position)
    }

    override fun onRetry() {
        viewModel?.retry()
    }

    abstract  override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

}
