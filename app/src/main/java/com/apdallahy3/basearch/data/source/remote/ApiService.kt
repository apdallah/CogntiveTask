package com.apdallahy3.basearch.data.source.remote

import androidx.lifecycle.LiveData
import com.apdallahy3.basearch.data.response.NearbyPlacesResponse
import retrofit2.http.*


interface ApiService {

    @GET("venues/explore")
    fun getNearBys(
        @Query("client_id") clienid: String,
        @Query("client_secret") clientSecert: String,
        @Query("v") version: Int,
        @Query("ll") location: String
    ): LiveData<ApiResponse<NearbyPlacesResponse>>

}