package com.apdallahy3.basearch.koin

import com.apdallahy3.basearch.data.reposiories.NearbyPlacesRepository
import com.apdallahy3.basearch.data.source.local.NearbyDatabse
import org.koin.dsl.module


val repo = module {
    single {
        NearbyPlacesRepository(
            get(),
            get(),
            get()
        )
    }
}