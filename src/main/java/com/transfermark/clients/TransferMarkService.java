package com.transfermark.clients;

import com.google.gson.JsonObject;
import com.transfermark.builders.ClubBuilder;
import com.transfermark.builders.InjuryBuilder;
import com.transfermark.builders.PlayerBuilder;
import com.transfermark.deserializers.Deserializer;
import com.transfermark.deserializers.DeserializerController;
import com.transfermark.model.Club;
import com.transfermark.model.Injury;
import com.transfermark.model.Player;
import com.transfermark.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferMarkService {

    private static final String PLAYER_DESERIALIZER = "player";
    private static final String INJURY_DESERIALIZER = "injury";
    private static final String CLUB_DESERIALIZER = "club";
    private static final String VALUE_DESERIALIZER = "value";

    private final HttpClient client;
    private final DeserializerController deserializerController;

    public TransferMarkService() {
        this.client = new TransferMarkClient();
        this.deserializerController = new DeserializerController();
    }

    public List<Injury> getPlayerInjuries(String playerId){
        String path = "/players/" + playerId + "/injuries";
        List<InjuryBuilder> injuryBuilders = (List<InjuryBuilder>) getDeserializer(INJURY_DESERIALIZER)
                .deserialize(sendRequest(path));
        return injuryBuilders.stream().map(InjuryBuilder::build).collect(Collectors.toList());
    }

    public List<Player> getPlayersFromClub(String clubId){
        String path = "/clubs/" + clubId + "/players";
        List<PlayerBuilder> playerBuilders = (List<PlayerBuilder>) getDeserializer(PLAYER_DESERIALIZER)
                .deserialize(sendRequest(path));
        return playerBuilders.stream()
                .map(this::fillPlayerWithValuesInjuries)
                .map(PlayerBuilder::build).collect(Collectors.toList());
    }

    public List<Player> getPlayersFromClub(String clubId, int year){
        String path = "/clubs/" + clubId + "/players?season_id=" + year;
        List<PlayerBuilder> playerBuilders = (List<PlayerBuilder>) getDeserializer(PLAYER_DESERIALIZER)
                .deserialize(sendRequest(path));
        return playerBuilders.stream()
                .map(this::fillPlayerWithValuesInjuries)
                .map(PlayerBuilder::build).collect(Collectors.toList());
    }

    private PlayerBuilder fillPlayerWithValuesInjuries(PlayerBuilder builder) {
        return builder.injury(getPlayerInjuries(builder.id())).value(getPlayerValues(builder.id()));
    }

    public List<Integer> getPlayerValues(String playerId){
        String path = "/players/" + playerId + "/market_value";
        List<Integer> values = (List<Integer>) getDeserializer(VALUE_DESERIALIZER)
                .deserialize(sendRequest(path));
        return inverseList(values);
    }

    private List<Integer> inverseList(List<Integer> values) {
        List<Integer> newValues = new ArrayList<>();
        int size = values.size();
        for (int i = size - 1; i >= Math.max(0, size - Player.MAX_VALUES); i--)
            newValues.add(values.get(i));
        return newValues;
    }

    public List<Club> getClubsFromLeague(String leagueId){
        String path = "/competitions/" + leagueId + "/clubs";
        List<ClubBuilder> clubBuilders = (List<ClubBuilder>) getDeserializer(CLUB_DESERIALIZER).deserialize(sendRequest(path));
        return clubBuilders.stream()
                .map(this::fillClubWithPlayers)
                .map(ClubBuilder::build)
                .collect(Collectors.toList());
    }

    public List<Club> getClubsFromLeague(String leagueId, int year){
        String path = "/competitions/" + leagueId + "/clubs?season_id=" + year;
        List<ClubBuilder> clubBuilders = (List<ClubBuilder>) getDeserializer(CLUB_DESERIALIZER).deserialize(sendRequest(path));
        return clubBuilders.stream()
                .map(clubBuilder -> fillClubWithPlayers(clubBuilder, year))
                .map(ClubBuilder::build)
                .collect(Collectors.toList());
    }

    public int getAge(String id) {
        String path = "/players/" + id + "/profile";
        JsonObject age = JsonUtils.deserialize(sendRequest(path));
        return age.get("age").getAsInt();
    }

    private ClubBuilder fillClubWithPlayers(ClubBuilder builder){
        return builder.players(getPlayersFromClub(builder.id()));
    }

    private ClubBuilder fillClubWithPlayers(ClubBuilder builder, int year){
        return builder.players(getPlayersFromClub(builder.id(), year));
    }

    private Deserializer getDeserializer(String deserializer){
        return deserializerController.getDeserializer(deserializer);
    }

    private String sendRequest(String path) {
        try {
            return client.request(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
