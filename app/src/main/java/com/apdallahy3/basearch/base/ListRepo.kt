package com.gambia.android.base

import androidx.lifecycle.LiveData
import com.apdallahy3.basearch.data.source.remote.*


abstract class ListRepo<MODEL , RES> constructor(
    private val apiService: ApiService,
    private val coroutineContext: ContextProviders
) {
open val PERSON_ID =5394

    abstract fun getItems(res : RES) : ArrayList<MODEL>
    abstract fun createCall(apiService: ApiService) : LiveData<ApiResponse<RES>>


      open  fun getList(): LiveData<Resource<List<MODEL?>>>? {

        return object : NetworkBoundResource<List<MODEL?>, RES>(coroutineContext) {

            private var items: List<MODEL?>? = null

            override fun saveCallResult(res: RES) {items = getItems(res) as List<MODEL?>?
            }

            override fun createCall(): LiveData<ApiResponse<RES>> = createCall(apiService)

            override fun shouldFetch(data: List<MODEL?>?) = true

            override fun loadFromDb(): LiveData<List<MODEL?>>? = null

            override fun getResult(): List<MODEL?>? = items

        }.asLiveData()
    }




}