package com.apdallahy3.basearch.data.source.remote

import androidx.lifecycle.LiveData
import com.apdallahy3.basearch.data.response.MatchesResponse
import retrofit2.http.*


interface ApiService {

    @GET("eventsnextleague.php")
    fun getNextMatch(@Header("token") token: String, @Query("id") leagueId: String): LiveData<ApiResponse<MatchesResponse>>


}