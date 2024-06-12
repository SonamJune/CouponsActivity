package com.manash.purpllebase.preference;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0086\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u000eJ\u0018\u0010\u0014\u001a\u00020\u00152\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0017J\u0018\u0010\u0018\u001a\u00020\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0019J\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005J*\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001c2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001cJ\u0018\u0010\u001d\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u000eJ\u0018\u0010\u001f\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u0015J\u0018\u0010 \u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u0017J\u0018\u0010!\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u0019J\u001a\u0010\"\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005J\"\u0010#\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001cJ\u0010\u0010$\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006%"}, d2 = {"Lcom/manash/purpllebase/preference/PurpllePreference;", "", "mContext", "Landroid/content/Context;", "mPrefName", "", "(Landroid/content/Context;Ljava/lang/String;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "clear", "", "contains", "", "key", "edit", "Landroid/content/SharedPreferences$Editor;", "getBoolean", "defaultValue", "getFloat", "", "getInt", "", "getLong", "", "getString", "getStringSet", "", "putBoolean", "value", "putFloat", "putInt", "putLong", "putString", "putStringSet", "remove", "PurplleBase_debug"})
public final class PurpllePreference {
    private final android.content.Context mContext = null;
    private final java.lang.String mPrefName = null;
    
    public PurpllePreference(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, @org.jetbrains.annotations.NotNull()
    java.lang.String mPrefName) {
        super();
    }
    
    private final android.content.SharedPreferences getSharedPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences.Editor edit() {
        return null;
    }
    
    public final void putBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.String key, boolean value) {
    }
    
    public final void putInt(@org.jetbrains.annotations.Nullable()
    java.lang.String key, int value) {
    }
    
    public final void putString(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final void putFloat(@org.jetbrains.annotations.Nullable()
    java.lang.String key, float value) {
    }
    
    public final void putLong(@org.jetbrains.annotations.Nullable()
    java.lang.String key, long value) {
    }
    
    public final void putStringSet(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.String> value) {
    }
    
    public final int getInt(@org.jetbrains.annotations.Nullable()
    java.lang.String key, int defaultValue) {
        return 0;
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.String key, boolean defaultValue) {
        return false;
    }
    
    public final float getFloat(@org.jetbrains.annotations.Nullable()
    java.lang.String key, float defaultValue) {
        return 0.0F;
    }
    
    public final long getLong(@org.jetbrains.annotations.Nullable()
    java.lang.String key, long defaultValue) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.String> getStringSet(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.String> defaultValue) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getString(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultValue) {
        return null;
    }
    
    public final void remove(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
    }
    
    public final boolean contains(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return false;
    }
    
    public final void clear() {
    }
}