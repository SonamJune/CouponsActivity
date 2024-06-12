package com.example.couponbase.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.couponbase.PurplleApplication
import com.example.couponbase.preference.PurpllePreference


class PurplleEncryptedPreference constructor(
    val context: Context, val preferenceName: String
) {
    private var encryptedSharedPreferences: SharedPreferences? = null
    private lateinit var purplleSharedPreferences: PurpllePreference

    init {

        try {
            val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

            encryptedSharedPreferences = EncryptedSharedPreferences.create(
                context,
                "encrypted_pref",
                masterKey, // masterKey created above
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {

        }
    }

    fun saveEncryptedString(key: String, value: String?) {
        if (encryptedSharedPreferences != null) {
            getEditor()?.putString(key, value)?.commit()
        } else {
            PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(
                key,
                value
            )

        }
    }

    fun getEncryptedString(key: String,defaultValue:String?): String? {
        return if (encryptedSharedPreferences != null) encryptedSharedPreferences?.getString(
            key, defaultValue
        )
        else {
            PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(
                key,
                defaultValue
            )
        }
    }

    private fun getEditor() = encryptedSharedPreferences?.edit()
}