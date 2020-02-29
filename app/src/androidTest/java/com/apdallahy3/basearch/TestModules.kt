package com.apdallahy3.basearch

import androidx.room.Room
import com.apdallahy3.basearch.data.source.local.NearbyDatabse
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomTestModule = module {
//    single {
//        Room.inMemoryDatabaseBuilder(
//            androidContext(),
//            NearbyDatabse::class.java
//        ).allowMainThreadQueries().build()
//    }

}