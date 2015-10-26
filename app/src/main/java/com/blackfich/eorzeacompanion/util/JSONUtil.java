package com.blackfich.eorzeacompanion.util;

import android.util.Log;

import com.blackfich.eorzeacompanion.util.bean.JSONable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Blackfich on 19/10/2015.
 */
public class JSONUtil {

    public static boolean getBoolean(JSONObject jsonObject, String key, boolean defaultValue) {
        try {
            return jsonObject != null ? jsonObject.getBoolean(key) : defaultValue;
        } catch (JSONException e) {
            Log.e(null, e.toString());
            return defaultValue;
        }
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        try {
            return jsonObject != null ? jsonObject.getInt(key) : defaultValue;
        } catch (JSONException e) {
            Log.e(null, e.toString());
            return defaultValue;
        }
    }

    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        try {
            return jsonObject != null ? jsonObject.getString(key) : defaultValue;
        } catch (JSONException e) {
            Log.e(null, e.toString());
            return defaultValue;
        }
    }

    public static void put(JSONObject jsonObject, String key, boolean value) {
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            Log.e(null, e.toString());
        }
    }

    public static void put(JSONObject jsonObject, String key, int value) {
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            Log.e(null, e.toString());
        }
    }

    public static void put(JSONObject jsonObject, String key, String value) {
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            Log.e(null, e.toString());
        }
    }

    public static void fromJSON(JSONable jsonable, String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            jsonable.fromJSON(jsonObject);
        } catch (JSONException e) {
            Log.e(null, e.toString());
        }
    }
}
