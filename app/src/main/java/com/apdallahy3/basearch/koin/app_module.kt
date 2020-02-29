package com.apdallahy3.basearch.koin

import androidx.room.Room
import com.apdallahy3.basearch.data.source.local.NearbyDatabse
import com.apdallahy3.basearch.data.source.remote.ApiServiceFactory
import com.apdallahy3.basearch.data.source.remote.ContextProviders
import com.apdallahy3.basearch.utils.PreferenceStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {


    single { ApiServiceFactory.getService() }
    single { ContextProviders.getInstance() }
    single { PreferenceStorage(androidContext()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            NearbyDatabse::class.java, "nearby-database"
        ).fallbackToDestructiveMigration().build()
    }

}


