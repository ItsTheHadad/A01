package com.example.l01redo.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.l01redo.Models.Score;
import com.example.l01redo.Models.ScoresList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class SPutil {

    private static final String DB_FILE = "DB_FILE";
    private static SPutil instance = null;
    private SharedPreferences sharedPreferences;

    private SPutil(Context context) {
        sharedPreferences = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
    }

    public static void initSP(Context context){
        if (instance == null){
            instance = new SPutil(context);
        }
    }

    public static SPutil getInstance() {
        return instance;
    }


    public String getString(String key, String defValue){
        return sharedPreferences.getString(key,defValue);
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public <T> ArrayList<T> getArrayList(String key, ArrayList<T> defValue) {
        String json = getString(key, "");
        if (json.isEmpty())
            return defValue;
        Log.d("Scores loaded", "getArrayList: "+ json);
        Type type = new TypeToken<T[]>(){}.getType();
        return new ArrayList<T>(Arrays.asList(new Gson().fromJson(json,type)));
    }

    public <T> void putArrayList(String key, ArrayList<T> value) {
        Log.d("Scores Saved", "getArrayList: "+ (T[]) value.toArray());
        putString(key, new Gson().toJson((T[]) value.toArray()));
    }

    public void putObject(String key, Object value) {
        Log.d("Scores Saved", "getArrayList: "+ value);
        putString(key, new Gson().toJson(value));

    }


    public Object getObject(String key, Object defValue, Type T) {
        String json = getString(key, "");
        if (json.isEmpty())
            return defValue;
        return new Gson().fromJson(json, T);
    }
}

