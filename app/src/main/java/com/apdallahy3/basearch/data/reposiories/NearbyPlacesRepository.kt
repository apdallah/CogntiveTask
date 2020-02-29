package com.apdallahy3.basearch.data.reposiories

import android.location.Location
import androidx.lifecycle.LiveData
import com.apdallahy3.basearch.data.response.GroupItemResponse
import com.apdallahy3.basearch.data.response.Item
import com.apdallahy3.basearch.data.response.NearbyPlacesResponse
import com.apdallahy3.basearch.data.response.PhotoResponse
import com.apdallahy3.basearch.data.source.local.NearbyDatabse
import com.apdallahy3.basearch.data.source.local.daos.PlacesDao
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy
import com.apdallahy3.basearch.data.source.remote.*


class NearbyPlacesRepository(
    private val apiService: ApiService, private val coroutineContext: ContextProviders
    , private val database: NearbyDatabse
) {
    private val placesDao = database.getPlacesDao()
    fun getNearBys(
        location: Location
    ): LiveData<Resource<List<PlacesEntitiy>>> {

        return object :
            NetworkBoundResource<List<PlacesEntitiy>, NearbyPlacesResponse>(coroutineContext) {

            private var items: List<PlacesEntitiy>? = null

            override fun saveCallResult(item: NearbyPlacesResponse) {

                item.response?.let { rsponse ->
                    rsponse.groups?.let { groupResonse ->
                        groupResonse[0].items?.let { groupItems ->
                            val result = getEntities(groupItems)
                            result.also {
                                if (it.isNotEmpty()) {
                                    clearPlaces()
                                    cachePlaces(it)
                                }
                            }
                            items = result
                        }
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<NearbyPlacesResponse>> =
                apiService.getNearBys(
                    ClientConstants.CLIENT_ID,
                    ClientConstants.CLIENT_SECERT,
                    ClientConstants.VERSION,
                    "${location.latitude},${location.longitude}"
                )

            override fun shouldFetch(data: List<PlacesEntitiy>?) = true

            override fun loadFromDb(): LiveData<List<PlacesEntitiy>>? = placesDao.getPlaces()

            override fun getResult(): List<PlacesEntitiy>? = items

        }.asLiveData()
    }

    fun getEntities(responseList: List<GroupItemResponse>): List<PlacesEntitiy> {
        val result = ArrayList<PlacesEntitiy>()
        responseList.map {
            it.venue?.let { venue ->
                result.add(
                    PlacesEntitiy(
                        venue.id!!, venue.name,
                        "${venue.categories!!.get(0).name} - ${venue.location!!.address} ( ${venue.location.state} - ${venue.location.city} )"

                    )
                )

            }

        }
        return result
    }

    //call this from adapter on binding
    //after return update db
    fun getPlaceThumbinal(placeItem: PlacesEntitiy): LiveData<Resource<String>> {
        return object : NetworkBoundResource<String, PhotoResponse>(coroutineContext) {
            private var result: String? = null
            override fun saveCallResult(item: PhotoResponse) {
                item.response?.let { photoResponseItem ->
                    photoResponseItem.photos?.let { photoItem ->
                        photoItem.items?.let { item ->
                            if (item.isNotEmpty())
                                result =
                                    "${item.get(0).prefix}${item.get(0).width}x${item.get(0).height}${item.get(
                                        0
                                    ).suffix} " else ""
                        }
                    }

                }
                placeItem.thumbinal = result
                updatePlaceItem(placeItem)
            }

            override fun getResult(): String? = result


            override fun createCall(): LiveData<ApiResponse<PhotoResponse>> {
                return apiService.getThumbinalByID(
                    id = placeItem.id,
                    clienid = ClientConstants.CLIENT_ID,
                    clientSecert = ClientConstants.CLIENT_SECERT,
                    version = ClientConstants.VERSION
                )
            }

            override fun shouldFetch(data: String?): Boolean = true
            override fun loadFromDb(): LiveData<String>? = null

        }.asLiveData()

    }

    fun updatePlaceItem(placeItem: PlacesEntitiy) {
        placesDao.updateEntitiy(placeItem)

    }

    fun cachePlaces(placesList: List<PlacesEntitiy>) {
        placesDao.inserPlaces(placesList)
    }

    fun clearPlaces() {
        placesDao.clearPlcaes()
    }
}