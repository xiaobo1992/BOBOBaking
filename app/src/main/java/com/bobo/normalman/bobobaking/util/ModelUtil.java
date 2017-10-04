package com.bobo.normalman.bobobaking.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class ModelUtil {
    static Gson gson = new Gson();

    public static <T> T toObject(String str, TypeToken<T> token) {
        return gson.fromJson(str, token.getType());
    }

    public static <T> String toString(T obj, TypeToken<T> token) {
        return gson.toJson(obj, token.getType());
    }
}
