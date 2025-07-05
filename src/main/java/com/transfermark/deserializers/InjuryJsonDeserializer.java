package com.transfermark.deserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.transfermark.builders.InjuryBuilder;
import com.transfermark.model.Injury;
import com.transfermark.utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class InjuryJsonDeserializer implements Deserializer{

    @Override
    public List<InjuryBuilder> deserialize(String injuriesString) {
        List<JsonElement> injuriesJson = getJsonInjuries(JsonUtils.deserialize(injuriesString));
        return injuriesJson.stream()
                .map(JsonElement::getAsJsonObject)
                .map(this::deserializeInjury).collect(Collectors.toList());
    }

    private List<JsonElement> getJsonInjuries(JsonObject playerJson) {
        return playerJson.get("injuries").getAsJsonArray().asList();
    }

    private InjuryBuilder deserializeInjury(JsonObject injuryJson) {
        return new InjuryBuilder().injuryName(deserializeInjuryName(injuryJson))
                .from(deserializeFrom(injuryJson))
                .to(deserializeTo(injuryJson))
                .daysMissed(deserializeDayMissed(injuryJson))
                .gamesMissed(deserializeGamesMissed(injuryJson));
    }

    private String deserializeGamesMissed(JsonObject injuryJson) {
        return injuryJson.has("gamesMissed") && !injuryJson.get("gamesMissed").isJsonNull()
                ? injuryJson.get("gamesMissed").getAsString()
                : "0";
    }

    private String deserializeDayMissed(JsonObject injuryJson) {
        return injuryJson.get("days").getAsString();
    }

    private String deserializeTo(JsonObject injuryJson) {
        return injuryJson.has("untilDate") && !injuryJson.get("untilDate").isJsonNull()
                ? injuryJson.get("untilDate").getAsString()
                : null;
    }

    private String deserializeFrom(JsonObject injuryJson) {
        return injuryJson.get("fromDate").getAsString();
    }

    private String deserializeInjuryName(JsonObject injuryJson) {
        return injuryJson.get("injury").getAsString();
    }
}
