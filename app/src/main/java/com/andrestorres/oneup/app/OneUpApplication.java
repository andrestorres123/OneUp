package com.andrestorres.oneup.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by andrestorres on 12/15/16.
 */

public class OneUpApplication extends Application {

    private static OneUpApplication sOneUpApplicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sOneUpApplicationInstance = this;
    }

    public static synchronized OneUpApplication getInstance() {
        return sOneUpApplicationInstance;
    }

    public static class Session {

        private static Session cacheSingleInstance;

        private Session() {
        }

        public static synchronized Session getCache() {
            if (cacheSingleInstance == null) {
                cacheSingleInstance = new Session();
            }

            return cacheSingleInstance;
        }

        public static void clearCache() {
            cacheSingleInstance = null;
        }
    }

    public static class Settings {

        private static final String ONEUP_SHARED_PREFERENCES = "ONEUP_SHARED_PREFERENCES";

        private static Settings settingsInstance;

        private static SharedPreferences sharedPreferences;
        private static SharedPreferences.Editor editor;

        private Settings() {
        }

        public static synchronized Settings getPreferences() {
            return getPreferences(ONEUP_SHARED_PREFERENCES);
        }

        public static synchronized Settings getPreferences(String name) {
            if (settingsInstance == null) {
                settingsInstance = new Settings();
                sharedPreferences = getInstance().getSharedPreferences(name, MODE_PRIVATE);
                editor = sharedPreferences.edit();
            }

            return settingsInstance;
        }

        public static void clearPreferences() {
            if (editor == null) return;

            editor.clear()
                    .commit();
        }

        public void putBoolean(String key, boolean value) {
            editor.putBoolean(key, value)
                    .commit();
        }

        public void putFloat(String key, float value) {
            editor.putFloat(key, value)
                    .commit();
        }

        public void putInt(String key, int value) {
            editor.putInt(key, value)
                    .commit();
        }

        public void putLong(String key, long value) {
            editor.putLong(key, value)
                    .commit();
        }

        public void putString(String key, String value) {
            editor.putString(key, value)
                    .commit();
        }

        public void putStringSet(String key, Set<String> values) {
            editor.putStringSet(key, values)
                    .commit();
        }

        public void putObject(String key, Object object) {
            Gson gson = new Gson();
            String json = gson.toJson(object);

            editor.putString(key, json)
                    .commit();
        }

        public boolean getBoolean(String key) {
            return sharedPreferences.getBoolean(key, false);
        }

        public float getFloat(String key) {
            return sharedPreferences.getFloat(key, 0.0f);
        }

        public int getInt(String key) {
            return sharedPreferences.getInt(key, 0);
        }

        public long getLong(String key) {
            return sharedPreferences.getLong(key, 0);
        }

        public String getString(String key) {
            return sharedPreferences.getString(key, null);
        }

        public Set<String> getStringSet(String key) {
            return sharedPreferences.getStringSet(key, null);
        }

        public <T> T getObject(String key, Type type) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(key, null);

            return gson.fromJson(json, type);
        }
    }
}
