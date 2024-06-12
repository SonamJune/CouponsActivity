package com.example.couponbase.preference;

import android.content.Context;

public class PurpllePrefManager {

    private String PREF_GLOBAL = "navigation";
    private String PREF_SESSION = "session";

    public final PurpllePreference GLOBAL;
    public final PurpllePreference USER;
    public final PurplleEncryptedPreference ENCRYPTED;

    private static PurpllePrefManager mInstance;
    private static final Object lock = new Object();

    public static PurpllePrefManager getInstance(Context applicationContext) {
        if (mInstance == null) {
            synchronized (lock) {
                if (mInstance == null) {
                    mInstance = new PurpllePrefManager(applicationContext);
                }
            }
        }
        return mInstance;
    }

    private PurpllePrefManager(Context applicationContext) {
        GLOBAL = new PurpllePreference(applicationContext, PREF_GLOBAL);
        USER = new PurpllePreference(applicationContext, PREF_SESSION);
        ENCRYPTED = new PurplleEncryptedPreference(applicationContext, "");
    }
}