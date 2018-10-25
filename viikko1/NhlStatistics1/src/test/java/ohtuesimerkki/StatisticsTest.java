package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    Player yzerman;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
        yzerman = stats.search("Yzerman");
    }
    
    
    @Test
    public void teamTesting() {
        assertEquals("Edmonton has 3 players", 3, stats.team("EDM").size());
        assertEquals("Pittsburg has 1 player", 1, stats.team("PIT").size());
        
        List<Player> detroit = stats.team("DET");
        assertEquals(yzerman, detroit.get(0));
    }
    
    @Test
    public void topScorerTest() {
        List<Player> topScrorers = stats.topScorers(1);
        assertEquals("Gretzky", topScrorers.get(0).getName());
    }
    
    // ai statisticille vain testaukset,,,
    
    @Test
    public void gettersTesting() {
        // name
        assertEquals("Yzerman", yzerman.getName());
        
        // team
        assertEquals("DET", yzerman.getTeam());
        
        // goals
        assertEquals(42, yzerman.getGoals());
        
        // assists
        assertEquals(56, yzerman.getAssists());
        
        // points
        assertEquals(98, yzerman.getPoints());
    }
    
    @Test
    public void settersTesting() {
        // goals
        yzerman.setGoals(88);
        assertEquals(88, yzerman.getGoals());
        
        // asssists
        yzerman.setAssists(61);
        assertEquals(61, yzerman.getAssists());
        
        // team
        yzerman.setTeam("DAL");
        assertEquals("DAL", yzerman.getTeam());
        
        // name
        yzerman.setName("Aho");
        assertEquals("Aho", yzerman.getName());
    }
    
    @Test
    public void compareTesting() {
        Player semenko = stats.search("Semenko");
        int expectation = yzerman.getPoints()-semenko.getPoints();
        assertEquals(expectation, semenko.compareTo(yzerman));
    }
    
    @Test
    public void toStringTesting() {
        String name = "Yzerman";
        String team = "DET";
        int goals = 42;
        int assists = 56;
        int points = 98;

        String exp = String.format("%-20s",name) + " " + team + " " + String.format("%2d",goals) + " + " 
                + String.format("%2d",assists) + " = " + points;
        
        assertEquals(exp, yzerman.toString());
    }
}
