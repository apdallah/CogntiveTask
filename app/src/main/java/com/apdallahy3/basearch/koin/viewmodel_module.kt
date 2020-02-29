package com.apdallahy3.basearch.koin

import com.apdallahy3.basearch.modules.nearby_places.NearbyPlacesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//define list view model
val viewModule = module {
    viewModel { NearbyPlacesViewModel(get()) }
}