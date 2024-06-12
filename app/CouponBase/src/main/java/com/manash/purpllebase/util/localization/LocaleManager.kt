package com.manash.purpllebase.util.localization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.annotation.StringDef
import com.manash.purpllebase.preference.PurpllePrefKey
import com.manash.purpllebase.preference.PurpllePrefManager
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.Locale

object LocaleManager {
    const val ENGLISH = "en"
    const val HINDI = "hi"
    const val MARATHI = "mr"
    const val TELUGU = "te"
    const val BENGALI = "bn"
    const val TAMIL = "ta"
    const val MALAYALAM = "ml"
    const val KANNADA = "kn"


    const val HINDI_LITE ="hif"
    const val TELUGU_LITE ="tl"

    /**
     * SharedPreferences Key
     */
    private const val LANGUAGE_KEY = "language_key"
    var SUPPORTED_LOCALES = arrayOf(ENGLISH, HINDI, TELUGU)

    /**
     * set current pref locale
     */
    @JvmStatic
    fun setLocale(mContext: Context): Context {
        return updateResources(mContext, getLanguagePref(mContext))
    }

    /**
     * Set new Locale with context
     */
    @JvmStatic
    fun setNewLocale(mContext: Context, @LocaleDef language: String): Context {
        setLanguagePref(mContext, language)
        return updateResources(mContext, language)
    }

    /**
     * Get saved Locale from SharedPreferences
     *
     * @param mContext current context
     * @return current locale key by default return english locale
     */
    @JvmStatic
    fun getLanguagePref(mContext: Context?): String? {
        return PurpllePrefManager.getInstance(mContext).GLOBAL.getString(
            PurpllePrefKey.LANGUAGE_KEY,
            ENGLISH
        )
    }

    @JvmStatic
    fun getVernacularLiteEnabled(mContext: Context?): String? {
        return PurpllePrefManager.getInstance(mContext).GLOBAL.getString(
            PurpllePrefKey.VERNACULAR_LITE_KEY,
            VernacularEnableOptions.none.value
        )
    }

    @JvmStatic
    fun setVernacularLiteEnabled(mContext: Context?, value: String) {
        return PurpllePrefManager.getInstance(mContext).GLOBAL.putString(
            PurpllePrefKey.VERNACULAR_LITE_KEY,
            value
        )
    }

    /**
     * set pref key
     */
    @JvmStatic
    fun setLanguagePref(mContext: Context, localeKey: String) {
        PurpllePrefManager.getInstance(mContext).GLOBAL.putString(
            PurpllePrefKey.LANGUAGE_KEY,
            localeKey
        )

    }

    /**
     * update resource
     */
    private fun updateResources(context: Context, language: String?): Context {
        var context = context
        var staticStringLanguage = language
        getVernacularLiteEnabled(context)?.let {
            when (VernacularEnableOptions.valueOf(it.lowercase())) {

                VernacularEnableOptions.all -> {

                    staticStringLanguage = ENGLISH
                }
                VernacularEnableOptions.mix -> {
                    if(SUPPORTED_LOCALES.contains(language)){
                        when(language){
                            ENGLISH ->
                                staticStringLanguage = ENGLISH
                            HINDI ->
                                staticStringLanguage = HINDI_LITE
                            TELUGU ->
                                staticStringLanguage = TELUGU_LITE
                        }
                    }
                }
                VernacularEnableOptions.none -> {
                    staticStringLanguage = language
                }
                else ->{
                    staticStringLanguage = language
                }
            }
        }

        val locale = Locale(staticStringLanguage)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
            context = context.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
        }
        return context
    }

    /**
     * get current locale
     */
    @JvmStatic
    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= 24) config.locales[0] else config.locale
    }

    @JvmStatic
    fun setLanguageChanged(mContext: Context, languageChanged: Boolean) {
        PurpllePrefManager.getInstance(mContext).GLOBAL.putBoolean(
            PurpllePrefKey.LANGUAGE_CHANGED,
            languageChanged
        )
    }

    @JvmStatic
    fun getLanguageChanged(mContext: Context): Boolean {
        return PurpllePrefManager.getInstance(mContext).GLOBAL.getBoolean(
            PurpllePrefKey.LANGUAGE_CHANGED,
            false
        )
    }

    /*
          Returns true if user has seen language selection scren
            */
    @JvmStatic
    fun userSeenLanguageSelectionScreen(context: Context): Boolean {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(
            PurpllePrefKey.USER_SEEN_LANGUAGE_SELECTION,
            false
        )
    }

    @JvmStatic
    fun setUserSeenLanguageSelectionScreen(context: Context, screenSeen: Boolean) {
        return PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(
            PurpllePrefKey.USER_SEEN_LANGUAGE_SELECTION,
            screenSeen
        )
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef(ENGLISH, HINDI, MARATHI, TELUGU, BENGALI, TAMIL, KANNADA, MALAYALAM)
    annotation class LocaleDef {
        companion object {
        }
    }
}