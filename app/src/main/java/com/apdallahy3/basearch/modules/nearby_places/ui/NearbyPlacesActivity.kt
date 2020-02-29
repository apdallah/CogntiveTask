package com.apdallahy3.basearch.modules.nearby_places.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.apdallahy3.basearch.R
import com.apdallahy3.basearch.base.BaseActionBarActivity
import com.apdallahy3.basearch.modules.nearby_places.ui.NearbyFragment

class NearbyPlacesActivity : BaseActionBarActivity<ViewDataBinding>() {


    override fun updateUI(savedInstanceState: Bundle?) {
        setScreenTitle(getString(R.string.app_name))
        supportFragmentManager.beginTransaction()
            .add(R.id.container, NearbyFragment.newInstance(), "NearByPlaces").commit()
    }
}
