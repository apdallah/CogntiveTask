package com.apdallahy3.basearch.modules.nearby_places.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.GnssNavigationMessage
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.apdallahy3.basearch.R
import com.apdallahy3.basearch.data.source.remote.Status
import com.apdallahy3.basearch.databinding.FragmentNearbyBinding
import com.apdallahy3.basearch.modules.nearby_places.NearbyPlacesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel

class NearbyFragment : Fragment() {

    lateinit var dataBinding: FragmentNearbyBinding
    private val REQUEST_LOCATION_PERMISION = 500
    private val viewModel: NearbyPlacesViewModel by viewModel()
    private lateinit var adapter: NearByAdapter
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_nearby, container, false)
        dataBinding.placesRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.fusedLocation = LocationServices.getFusedLocationProviderClient(activity!!)

        observeNearbysListRes()
        if (activity!!.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            activity!!.requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_LOCATION_PERMISION
            )
        } else {
            viewModel.fetchLocation()

        }

        return dataBinding.root

    }


    private fun observeNearbysListRes() {
        viewModel.nearbysListResource.observe(viewLifecycleOwner, Observer {
            adapter = NearByAdapter(context!!, it) {
                fetchNearBys()
            }
            dataBinding.placesRecycler.adapter = adapter
        })
    }

    private fun fetchNearBys() {
        viewModel.fetchLocation()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            viewModel.fetchLocation()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NearbyFragment()
    }
}
