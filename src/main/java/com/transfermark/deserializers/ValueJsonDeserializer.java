package com.transfermark.deserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.transfermark.utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ValueJsonDeserializer implements Deserializer{

    @Override
    public List<Integer> deserialize(String valuesString) {
        List<JsonElement> valuesJsons = getPlayersValuesJsons(JsonUtils.deserialize(valuesString));
        return valuesJsons.stream()
                .map(JsonElement::getAsJsonObject)
                .map(this::deserializeValue).collect(Collectors.toList());
    }

    private Integer deserializeValue(JsonObject valueJson) {
        return valueJson.has("marketValue") && !valueJson.get("marketValue").isJsonNull()
                ? valueJson.get("marketValue").getAsInt()
                : 0;
    }


    private List<JsonElement> getPlayersValuesJsons(JsonObject playerJson) {
        return playerJson.get("marketValueHistory").getAsJsonArray().asList();
    }
}
