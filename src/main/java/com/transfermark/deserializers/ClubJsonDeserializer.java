package com.transfermark.deserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.transfermark.builders.ClubBuilder;
import com.transfermark.utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClubJsonDeserializer implements Deserializer{

    @Override
    public List<ClubBuilder> deserialize(String clubsString) {
        List<JsonElement> clubsJsons = getClubsJsons(JsonUtils.deserialize(clubsString));
        return clubsJsons.stream()
                .map(JsonElement::getAsJsonObject)
                .map(this::deserializeClub).collect(Collectors.toList());
    }

    private ClubBuilder deserializeClub(JsonObject clubJson) {
        return new ClubBuilder().id(deserializeId(clubJson))
                .name(deserializeName(clubJson));
    }

    private List<JsonElement> getClubsJsons(JsonObject playerJson) {
        return playerJson.get("clubs").getAsJsonArray().asList();
    }

    private String deserializeId(JsonObject clubJson) {
        return clubJson.get("id").getAsString();
    }

    private String deserializeName(JsonObject clubJson) {
        return clubJson.get("name").getAsString();
    }
}
