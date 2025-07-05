package com.transfermark.deserializers;

import java.util.HashMap;
import java.util.Map;

public class DeserializerController {

    private static final Map<String, Deserializer> DESERIALIZER_MAP = new HashMap<>(){{
        put("player", new PlayerJsonDeserializer());
        put("injury", new InjuryJsonDeserializer());
        put("club", new ClubJsonDeserializer());
        put("value", new ValueJsonDeserializer());
    }};

    public Deserializer getDeserializer(String deserializer){
        return DESERIALIZER_MAP.get(deserializer);
    }
}
