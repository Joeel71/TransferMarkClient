package com.transfermark.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

    public static JsonObject deserialize(String jsonString){
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }
}
