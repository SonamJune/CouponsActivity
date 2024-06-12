package com.example.couponbase.preference

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PurpllePreference constructor(
    private val mContext: Context,
    private val mPrefName: String
) {
    private val sharedPreferences: SharedPreferences
        private get() = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)

    fun edit(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    fun putBoolean(key: String?, value: Boolean) {
        edit().putBoolean(key, value).commit()
    }

    fun putInt(key: String?, value: Int) {
        edit().putInt(key, value).commit()
    }

    fun putString(key: String?, value: String?) {
        edit().putString(key, value).commit()
    }

    fun putFloat(key: String?, value: Float) {
        edit().putFloat(key, value).commit()
    }

    fun putLong(key: String?, value: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            edit().putLong(key, value).apply()
        }
    }

    fun putStringSet(key: String?, value: Set<String?>?) {
        edit().putStringSet(key, value).commit()
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        val globalPreference = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)
        return globalPreference.getInt(key, defaultValue)
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        val globalPreference = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)
        return globalPreference.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String?, defaultValue: Float): Float {
        val globalPreference = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)
        return globalPreference.getFloat(key, defaultValue)
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        val globalPreference = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)
        return globalPreference.getLong(key, defaultValue)
    }

    fun getStringSet(key: String?, defaultValue: Set<String?>?): Set<String>? {
        val globalPreference = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)
        return globalPreference.getStringSet(key, defaultValue)
    }

    fun getString(key: String?, defaultValue: String?): String? {
        val globalPreference = mContext.getSharedPreferences(mPrefName, Context.MODE_MULTI_PROCESS)
        return globalPreference.getString(key, defaultValue)
    }

    fun remove(key: String?) {
        edit().remove(key).commit()
    }

    operator fun contains(key: String?): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clear() {
        edit().clear().commit()
    }
}