package com.apdallahy3.basearch.koin

import com.apdallahy3.basearch.utils.PreferenceStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {


    single { PreferenceStorage(androidContext()) }
}


