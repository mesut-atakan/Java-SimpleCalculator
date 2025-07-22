package com.aventra.calculator;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageManager {
    private SharedPreferences prefs;

    public StorageManager(Context context){
        prefs = context.getSharedPreferences("com.aventra.calculator", Context.MODE_PRIVATE);
    }

    public void save(String key, int value){
        prefs.edit().putInt(key, value).apply();
    }

    public void remove(String key){
        prefs.edit().remove(key).apply();
    }

    public int load (String key){
        return prefs.getInt(key, 0);
    }

    public boolean has(String key){
        return prefs.contains(key);
    }
}
