package com.gambia.android.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.apdallahy3.basearch.data.source.remote.Resource


abstract class ListViewModel<MODEL , RES>(val repo : ListRepo<MODEL , RES>) :BaseViewModel(){

    private val getCert = MutableLiveData<Boolean>()

    val data : MutableLiveData<Resource<List<MODEL?>>> = MutableLiveData()

    open var result: LiveData<Resource<List<MODEL?>>> = Transformations.switchMap(getCert) {
        repo.getList()
    }

    private val TAG: String? = "CertsViewModel"


   open fun refreshMatches() {
        getList()
    }

    fun getList(){
        getCert.value = true
    }
    fun getItem(): LiveData<Resource<List<MODEL?>>> {
      return repo.getList()!!
    }

   /* fun filter(context:Context , keyWord:String):List<MODEL?>{
        var serviceName:ArrayList<MODEL?> = ArrayList()

        getItem().observe(context as BaseActivity, Observer {
            when(it.status){
                Status.SUCCESS->{
                    it.data!!.forEach {
                        serviceName.add(it)
                    }
                }
            }
        })
        return serviceName.filterIndexed { index, servescisModel -> serviceName[index]!!.equals(keyWord)}
    }*/

    abstract fun  onItemClick(item : MODEL, position : Int)


    fun retry() {
        getList()
    }




}