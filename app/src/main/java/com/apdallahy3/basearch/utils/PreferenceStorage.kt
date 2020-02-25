package com.apdallahy3.basearch.utils

import android.content.Context
import java.util.*
import kotlin.properties.ReadWriteProperty

/**
 * Storage for app and user preferences.
 */
class PreferenceStorage(val context: Context) {

    private val USERNAME = "username2"
    private val USERNAME_Menu = "USERNAME_Menu"
    private val USERID = "USERID"
    private val LATEST_ITEM_ID = "latest_item_id"
    private val JOB_SECHDULED = "job_sechduled"
    private val LATEST_TRANS_ID = "latest_trans_id"
    private val PASSWORD: String = "password"
    private val ISLOGGEDIN: String = "loggedin2"
    private val ACCESS_TOKEN: String = "access_token"
    private val INVOICE_NUMBER: String = "invoice_number"


    private val PREF: String = "pref"


    fun isLoggedin(): Boolean {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getBoolean(ISLOGGEDIN, false)
    }

    fun setLoggedin(isLoggedin: Boolean) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean(ISLOGGEDIN, isLoggedin)
        if (!isLoggedin)
            editor.remove(USERNAME)
        editor.commit()
    }


    fun setUser(username: String) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString(USERNAME, username)
        editor.commit()
    }

    fun setUserName(username: String) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString(USERNAME_Menu, username)
        editor.commit()
    }

    fun setUserId(userID: Int) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt(USERID, userID)
        editor.commit()
    }


    fun getUser(): String? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getString(USERNAME, "")
    }

    fun getUserName(): String? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getString(USERNAME_Menu, "")
    }

    fun getUserId(): Int? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getInt(USERID, -1)
    }

    fun getInvoiceNumber(): String? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        var number = preference.getInt(INVOICE_NUMBER, 1)
        if (number > 9999)
            number = 1
        return java.lang.String.format(Locale.US, "%04d", number)
    }

    fun getIntegerInvoiceNumber(): Int {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getInt(INVOICE_NUMBER, 1)
    }

    fun setInvoiceNumber() {
        var number = getIntegerInvoiceNumber()
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt(INVOICE_NUMBER, number + 1)
        editor.commit()
    }

    fun getLatestTransID(): Int? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getInt(LATEST_TRANS_ID, -1)
    }

    fun setLatestTransID(id: Int) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt(LATEST_TRANS_ID, id)
        editor.commit()
    }

    fun getLatestItemID(): Int? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getInt(LATEST_ITEM_ID, -1)
    }

    fun saveJobSechduled(stat: Boolean) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean(JOB_SECHDULED, stat)
        editor.commit()
    }

    fun checkJobSechduled(): Boolean? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getBoolean(JOB_SECHDULED, false)
    }

    fun setLatestItemID(id: Int) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt(LATEST_ITEM_ID, id)
        editor.commit()
    }

    fun setPassword(password: String) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString(PASSWORD, password)
        editor.commit()
    }

    fun getPassword(): String? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getString(PASSWORD, "")
    }

    fun setAccessToken(accessToken: String?) {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.commit()

    }

    fun getAccessToken(): String? {
        val preference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preference.getString(ACCESS_TOKEN, "")
    }

}
