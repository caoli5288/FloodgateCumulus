package com.github.caoli5288.floodgate.cumulus.util;

import com.google.gson.Gson;

import java.util.Collection;
import java.util.Map;

public class Utils {

    public static final Gson GSON = new Gson();

    public static boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static <T> T asObject(Map<String, Object> map, Class<T> cls) {
        return GSON.fromJson(GSON.toJsonTree(map), cls);
    }
}
