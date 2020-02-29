package com.apdallahy3.basearch.data.source.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy

@Dao
interface PlacesDao {
    @Query("select * from places_tabel")
    fun getPlaces(): LiveData<List<PlacesEntitiy>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserPlaces(data: List<PlacesEntitiy>)

    @Query("delete from places_tabel")
    fun clearPlcaes()

    @Update
    fun updateEntitiy(item: PlacesEntitiy)


}