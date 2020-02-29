package com.apdallahy3.basearch.utils

import android.content.Context

/**
 * Storage for app and user preferences.
 */
class PreferenceStorage(val context: Context) {

    private val VIEW_TYPE = "view_type"
    private val PREF: String = "pref"

    fun setViewType(viewType: Int) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt(VIEW_TYPE, viewType)
        editor.commit()
    }


    fun getViewType(): Int? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getInt(VIEW_TYPE, Constants.TYPE_NEAR_BY)
    }

}
