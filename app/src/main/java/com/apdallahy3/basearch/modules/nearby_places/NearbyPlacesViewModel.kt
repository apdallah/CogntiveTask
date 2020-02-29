package com.apdallahy3.basearch.modules.nearby_places

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.apdallahy3.basearch.data.reposiories.NearbyPlacesRepository
import com.gambia.android.base.BaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class NearbyPlacesViewModel(val repository: NearbyPlacesRepository) : BaseViewModel() {

    private val lastLocation = MutableLiveData<Location>()
    lateinit var fusedLocation: FusedLocationProviderClient
    val nearbysListResource = Transformations.switchMap(lastLocation) {
        repository.getNearBys(it)
    }

    init {
        val location = Location("Default")
        location.latitude = 30.0396
        location.longitude = 31.2056
        getNearbys(location)
    }

    fun fetchLocation() {
        fusedLocation.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    getNearbys(it)
                }
            }
    }

    fun getNearbys(location: Location) {
        lastLocation.postValue(location)
    }
}