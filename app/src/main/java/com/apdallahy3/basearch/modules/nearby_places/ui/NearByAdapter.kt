package com.apdallahy3.basearch.modules.nearby_places.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.apdallahy3.basearch.R
import com.apdallahy3.basearch.data.response.GroupItemResponse
import com.apdallahy3.basearch.data.response.Venue
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy
import com.apdallahy3.basearch.data.source.remote.Resource
import com.apdallahy3.basearch.databinding.NearbyItemBinding
import com.bumptech.glide.Glide
import com.gambia.android.base.BaseRVAdapter

class NearByAdapter(
    context: Context,
    resource: Resource<List<PlacesEntitiy>>,
    val retry: (() -> Unit)? = null
) :
    BaseRVAdapter<PlacesEntitiy, NearbyItemBinding>(context, resource) {


    override fun bindDataViewHolder(
        binding: NearbyItemBinding?,
        item: PlacesEntitiy?,
        position: Int
    ) {

        binding?.let { bind ->
            bind.item = item
            item!!.thumbinal?.let { thumbnial ->
                Glide.with(bind.thumbinal.context).load(thumbnial).centerCrop().into(bind.thumbinal)
            }
            bind.executePendingBindings()
        }
    }

    override fun onRetry() {
        retry?.let {
            it.invoke()
        }
    }

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.nearby_item,
                parent,
                false
            )
        )
    }


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}