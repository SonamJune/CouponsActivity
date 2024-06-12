package com.manash.purpllebase.helper;

import com.manash.purpllebase.BuildConfig;

public class PurplleTrace {
    private static boolean isLogEnabled = BuildConfig.DEBUG;

    public static void setLogEnabled(boolean isEnabled) {
        isLogEnabled = isEnabled;
    }

    public static void i(String tag, String string) {
        if (isLogEnabled) android.util.Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (isLogEnabled) android.util.Log.e(tag, string);
    }

    public static void d(String tag, String string) {
        if (isLogEnabled) android.util.Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (isLogEnabled) android.util.Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (isLogEnabled) android.util.Log.w(tag, string);
    }
}