package com.apdallahy3.basearch.modules.nearby_places.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.GnssNavigationMessage
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.Image
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.apdallahy3.basearch.R
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy
import com.apdallahy3.basearch.data.source.remote.Resource
import com.apdallahy3.basearch.data.source.remote.Status
import com.apdallahy3.basearch.databinding.FragmentNearbyBinding
import com.apdallahy3.basearch.modules.nearby_places.NearbyPlacesViewModel
import com.apdallahy3.basearch.utils.Constants
import com.apdallahy3.basearch.utils.TypeChangeListner
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NearbyFragment : Fragment(), TypeChangeListner {

    lateinit var dataBinding: FragmentNearbyBinding
    private val REQUEST_LOCATION_PERMISION = 500
    private val viewModel: NearbyPlacesViewModel by viewModel()
    private lateinit var adapter: NearByAdapter
    private var view_type: Int? = null

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_nearby, container, false)
        dataBinding.placesRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.locationManager = activity!!.getSystemService(LOCATION_SERVICE) as LocationManager
        viewModel.fusedLocation = LocationServices.getFusedLocationProviderClient(context!!)
        arguments?.let {
            view_type = it.getInt(Constants.VIEWTYPE_KEY)
        }
        observeNearbysListRes()
        if (activity!!.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_LOCATION_PERMISION
            )
        } else {
            viewModel.setLocationUpdateListener()

        }

        return dataBinding.root

    }


    private fun observeNearbysListRes() {
        viewModel.nearbysListResource.observe(viewLifecycleOwner, Observer {
            adapter = NearByAdapter(context!!, it, {
                fetchNearBys()
            }, { item, view ->
                getItemThumbinal(item, view)
            })
            dataBinding.placesRecycler.adapter = adapter
            if (view_type == Constants.TYPE_SINGLE_UPDATE) viewModel.stopLocationUpdates()
        })
    }

    private fun getItemThumbinal(placesEntitiy: PlacesEntitiy, view: ImageView) {

        viewModel.getPlaceThumbinal(placesEntitiy)
            .observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> {
                        Glide.with(view.context).load(R.drawable.loading)
                            .into(view)
                    }
                    Status.SUCCESS -> {
                        if (it.data!!.isNotEmpty())
                            Glide.with(view.context).load(it.data).into(view)
                        else
                            Glide.with(view.context).load(R.drawable.error).into(
                                view
                            )

                    }
                    Status.ERROR -> {
                        Glide.with(view.context).load(R.drawable.error)
                            .into(view)
                    }
                    else ->
                        Glide.with(view.context).load(R.drawable.error).into(view)

                }
            })
    }

    private fun fetchNearBys() {
        viewModel.fetchLocation()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopLocationUpdates()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.i("onRequestResult", requestCode.toString())
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            viewModel.setLocationUpdateListener()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NearbyFragment()
    }

    override fun onTypeChange(type: Int) {
       when(type){
           Constants.TYPE_SINGLE_UPDATE->{
               viewModel.stopLocationUpdates()
           }
           Constants.TYPE_NEAR_BY->{
               viewModel.setLocationUpdateListener()
           }
       }

    }
}
