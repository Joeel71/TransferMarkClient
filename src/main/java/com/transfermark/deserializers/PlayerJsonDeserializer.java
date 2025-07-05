package com.transfermark.deserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.transfermark.builders.PlayerBuilder;
import com.transfermark.utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerJsonDeserializer implements Deserializer {

    public List<PlayerBuilder> deserialize(String playerString){
        List<JsonElement> playersJson = getPlayersJsons(JsonUtils.deserialize(playerString));
        return playersJson.stream()
                .map(JsonElement::getAsJsonObject)
                .map(this::deserializePlayer).collect(Collectors.toList());
    }

    private PlayerBuilder deserializePlayer(JsonObject playerJson) {
        return new PlayerBuilder().id(deserializeId(playerJson))
                .name(deserializeName(playerJson))
                .position(deserializePosition(playerJson));
    }

    private List<JsonElement> getPlayersJsons(JsonObject playerJson) {
        return playerJson.get("players").getAsJsonArray().asList();
    }

    private String deserializeId(JsonObject playerJson) {
        return playerJson.get("id").getAsString();
    }

    private String deserializeName(JsonObject playerJson) {
        return playerJson.get("name").getAsString();
    }

    private String deserializePosition(JsonObject playerJson) {
        return playerJson.get("position").getAsString();
    }

}
