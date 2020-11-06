package com.janfranco.bookstore.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.janfranco.bookstore.helpers.UnknownTypeException;

public class SharedPreferencesService {

    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesService(Activity activity) {
        mSharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public SharedPreferencesService(Context context, String fileKey) {
        mSharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE);
    }

    @SuppressWarnings("all")
    public <T> T readData(String key, T defaultValue) throws UnknownTypeException {
        if (defaultValue instanceof Boolean) {
            Boolean data = mSharedPreferences.getBoolean(key, (Boolean) defaultValue);
            return (T) data;
        } else if (defaultValue instanceof Integer) {
            Integer data = mSharedPreferences.getInt(key, (Integer) defaultValue);
            return (T) data;
        } else if (defaultValue instanceof Long) {
            Long data = mSharedPreferences.getLong(key, (Long) defaultValue);
            return (T) data;
        } else if (defaultValue instanceof String) {
            String data = mSharedPreferences.getString(key, (String) defaultValue);
            return (T) data;
        } else {
            throw new UnknownTypeException();
        }
    }

    public <T> void writeData(String key, T data) throws UnknownTypeException {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
            editor.apply();
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
            editor.apply();
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
            editor.apply();
        } else if (data instanceof String) {
            editor.putString(key, (String) data);
            editor.apply();
        } else {
            throw new UnknownTypeException();
        }
    }

}
