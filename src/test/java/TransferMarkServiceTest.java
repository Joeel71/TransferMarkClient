import com.transfermark.builders.ClubBuilder;
import com.transfermark.builders.LeagueBuilder;
import com.transfermark.builders.PlayerBuilder;
import com.transfermark.clients.TransferMarkService;
import com.transfermark.model.Club;
import com.transfermark.model.Injury;
import com.transfermark.model.League;
import com.transfermark.model.Player;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class TransferMarkServiceTest {

    private static final TransferMarkService service = new TransferMarkService();

    @Test
    void should_get_the_player_injuries() {
        Player player = new PlayerBuilder().id("342229").build();
        List<Injury> playerInjuries = service.getPlayerInjuries(player.id());
        playerInjuries.forEach(injury -> System.out.println(injury.injuryName()));
    }

    @Test
    void should_get_player_from_club() {
        Club club = new ClubBuilder().id("418").build();
        List<Player> playersFromClub = service.getPlayersFromClub(club.id());
        playersFromClub.forEach(player -> System.out.println(player.name()));
    }

    @Test
    void should_get_clubs_from_league() {
        League league = new LeagueBuilder().id("ES1").build();
        List<Club> clubsFromLeague = service.getClubsFromLeague(league.id());
        clubsFromLeague.forEach(club -> System.out.println(club.name()));
    }

    @Test
    void should_save_a_club() {
        Club club = new ClubBuilder().id("12321").name("Girona FC").build();
        List<Player> playersFromClub = service.getPlayersFromClub(club.id(), 2022);
        playersFromClub.forEach(club::addPlayer);
        club.players().stream().filter(player -> !player.id().equals("1006551")).forEach(player -> System.out.println(player.name()));
        club.save(new File ("C:/Users/Joel/Desktop/ES1.tsv"));
    }

    @Test
    void should_save_a_league() {
        League league = new LeagueBuilder().id("ES2").build();
        List<Club> clubsFromLeague = service.getClubsFromLeague(league.id(), 2022);
        clubsFromLeague.forEach(league::addClub);
        league.save(new File ("C:/Users/Joel/Desktop"));
    }
}
