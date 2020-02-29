package com.apdallahy3.basearch.modules.nearby_places.ui

import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.apdallahy3.basearch.R
import com.apdallahy3.basearch.base.BaseActionBarActivity
import com.apdallahy3.basearch.utils.Constants
import com.apdallahy3.basearch.utils.Constants.Companion.TYPE_NEAR_BY
import com.apdallahy3.basearch.utils.Constants.Companion.TYPE_SINGLE_UPDATE
import com.apdallahy3.basearch.utils.PreferenceStorage
import com.apdallahy3.basearch.utils.TypeChangeListner
import org.koin.android.ext.android.inject

class NearbyPlacesActivity : BaseActionBarActivity<ViewDataBinding>() {
    private lateinit var typeChangeListner: TypeChangeListner
    private val sharedPref: PreferenceStorage by inject()
    override fun updateUI(savedInstanceState: Bundle?) {
        loadType()

        setScreenTitle(getString(R.string.app_name))
        supportFragmentManager.beginTransaction()
            .add(R.id.container, NearbyFragment.newInstance(), "NearByPlaces").commit()
        setChangeTypeAction()

    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is NearbyFragment) {
            typeChangeListner = fragment as NearbyFragment
        }
    }

    fun loadType() {
        when (sharedPref.getViewType()) {
            Constants.TYPE_SINGLE_UPDATE -> {
                headerDataBinding.realTime.isChecked = true
            }
            Constants.TYPE_NEAR_BY -> {
                headerDataBinding.neearby.isChecked = true

            }
        }
    }

    fun setChangeTypeAction() {
        headerDataBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (group.checkedRadioButtonId) {
                R.id.realTime -> {
                    setTypeChange(Constants.TYPE_SINGLE_UPDATE)
                    typeChangeListner.onTypeChange(TYPE_SINGLE_UPDATE)

                }
                R.id.neearby -> {
                    setTypeChange(Constants.TYPE_NEAR_BY)
                    typeChangeListner.onTypeChange(TYPE_NEAR_BY)


                }
            }

        }
    }

    fun setTypeChange(type: Int) {
        sharedPref.setViewType(type)

    }


}
