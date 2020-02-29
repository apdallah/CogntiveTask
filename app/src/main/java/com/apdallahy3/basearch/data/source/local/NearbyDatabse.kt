package com.apdallahy3.basearch.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apdallahy3.basearch.data.source.local.daos.PlacesDao
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy

@Database(
    entities = [PlacesEntitiy::class],
    version = 1,
    exportSchema = false
)
abstract class NearbyDatabse : RoomDatabase() {
    abstract fun getPlacesDao(): PlacesDao
}