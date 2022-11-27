package ru.vsibi.data

import android.content.Context

class SharedPreferenceService (context: Context) {

    var mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    fun setSpString(key: String, value: String?) = mSettings.edit().putString(key, value).apply()

    fun getSpString(key: String): String? = mSettings.getString(key, null)

    fun setSpInt(key: String, value: Int) = mSettings.edit().putInt(key, value).apply()

    fun getSpInt(key: String): Int = mSettings.getInt(key, -1)

    fun setSpBool(key: String, value: Boolean) = mSettings.edit().putBoolean(key, value).apply()
    fun getSpBool(key: String): Boolean = mSettings.getBoolean(key, false)

    fun clear() = mSettings.edit().clear().apply()

    companion object {
        const val APP_PREFERENCES = "my_settings"
        const val KEY_ACCESS_TOKEN = "key_access_token"
        const val KEY_AUTH = "key_access_token"
        const val KEY_EMAIL = "key_access_token"
    }

}