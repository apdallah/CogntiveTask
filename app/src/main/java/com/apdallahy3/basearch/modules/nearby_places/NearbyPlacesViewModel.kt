package com.apdallahy3.basearch.modules.nearby_places

import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.apdallahy3.basearch.data.reposiories.NearbyPlacesRepository
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy
import com.apdallahy3.basearch.data.source.remote.Resource
import com.apdallahy3.basearch.koin.repo
import com.gambia.android.base.BaseViewModel
import com.google.android.gms.location.*


class NearbyPlacesViewModel(val repository: NearbyPlacesRepository) : BaseViewModel() {
    private lateinit var locationCallback: LocationCallback
    private val lastLocation = MutableLiveData<Location>()
    val locationNotEnabled = MutableLiveData<Boolean>()
    lateinit var fusedLocation: FusedLocationProviderClient
    lateinit var locationManager: LocationManager
    val nearbysListResource = Transformations.switchMap(lastLocation) {
        repository.getNearBys(it)
    }


    init {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                Log.i("onLocationResult", locationResult.toString())
                locationResult ?: return
                fetchLocation()

            }

        }
    }

    fun getPlaceThumbinal(placesEntitiy: PlacesEntitiy): LiveData<Resource<String>> {
        return repository.getPlaceThumbinal(placesEntitiy)
    }

    fun stopLocationUpdates() {
        fusedLocation.removeLocationUpdates(locationCallback)
    }

    fun setLocationUpdateListener() {
        fusedLocation.requestLocationUpdates(
            buildLocationRequest(),
            locationCallback,
            Looper.getMainLooper()
        )

    }

    private fun buildLocationRequest(): LocationRequest? {

        return LocationRequest()
            .setNumUpdates(1)
            .setExpirationDuration(60000)
            .setInterval(1000)
            .setFastestInterval(1000)
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)

    }

    fun fetchLocation() {
        fusedLocation.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) getNearbys(location)

            }
    }

    fun getNearbys(location: Location) {
        lastLocation.postValue(location)
    }
}