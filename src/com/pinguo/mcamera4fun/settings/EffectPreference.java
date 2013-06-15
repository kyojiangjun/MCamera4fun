
package com.pinguo.mcamera4fun.settings;

import java.util.Map;
import java.util.Set;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;

public class EffectPreference implements SharedPreferences, OnSharedPreferenceChangeListener {

    private static EffectPreference INSTENCE = null;
    private SharedPreferences mEffectPreference; // global preferences
    private static final String prefName = "effect";
    public static final String KEY_EFFECT_SELECT_VALUE = "pref_key_key_effect_select_";
    
    
    private EffectPreference(Context context) {
        context = context.getApplicationContext();
        mEffectPreference = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        mEffectPreference.registerOnSharedPreferenceChangeListener(this);
    }

    public static EffectPreference getInstance(Context context) {
        if (INSTENCE == null) {
            INSTENCE = new EffectPreference(context);
        }
        return INSTENCE;
    }

    @Override
    public boolean contains(String key) {
        return mEffectPreference.contains(key);
    }

    @Override
    public Editor edit() {
        return mEffectPreference.edit();
    }

    @Override
    public Map<String, ?> getAll() {
        return mEffectPreference.getAll();
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mEffectPreference.getBoolean(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mEffectPreference.getFloat(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mEffectPreference.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mEffectPreference.getLong(key, defValue);
    }

    @Override
    public String getString(String key, String defValue) {
        return mEffectPreference.getString(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return mEffectPreference.getStringSet(key, defValues);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        mEffectPreference.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        mEffectPreference.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

}
