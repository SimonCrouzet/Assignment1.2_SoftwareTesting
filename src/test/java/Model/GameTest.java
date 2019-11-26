package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {
    private Game SUT;
    InputStream systemBackup = System.in;
    ByteArrayInputStream in = new ByteArrayInputStream("1 2".getBytes());

    @BeforeEach
    void setUp() {
        System.setIn( in );
        SUT = new Game();
    }

    @Test
    void initializeTest() {
        assertNotNull(SUT.getPlayer1());
        assertNotNull(SUT.getPlayer2());
    }


    /**
     * Here we mock a player who have enough points to win the game. We set this mockPlayer in our Game, and we
     * check if the method getWinner() will correctly return this player
     */
    @Test
    void getWinnerReturnPlayer1IfWinning() {
        Player mock = mock(Player.class);
        when(mock.getScore()).thenReturn(SUT.getWinningScoreLimit());
        SUT.setPlayer1(mock);
        assertEquals(SUT.getPlayer1(), SUT.getWinner());
    }

    @Test
    void fightShouldReturnAWinner () {
        Player player1 = new Player();
        Player player2 = new Player();
        Fighter fighter1 = mock(Fighter.class);
        Fighter fighter2 = mock(Fighter.class);

        player1.setCurrentFighter(fighter1);
        player2.setCurrentFighter(fighter2);

        SUT.setPlayer1(player1);
        SUT.setPlayer2(player2);

        Player actual = SUT.fight();
        boolean isOneOfThePlayers = (actual.equals( SUT.getPlayer1() ) || actual.equals(SUT.getPlayer2()));

        assertTrue( isOneOfThePlayers, "The winner of the fight should be one of the game players." );
    }

    @Test
    void fightReturnStrongerFighter () {
        Fighter fighter1 = mock(Fighter.class);
        Fighter fighter2 = mock(Fighter.class);

        when(fighter1.getAttack()).thenReturn(50);
        when(fighter2.getAttack()).thenReturn(1);

        when(fighter1.getOriginalHealth()).thenReturn(100);
        when(fighter2.getOriginalHealth()).thenReturn(10);

        when(fighter1.getCurrentHealth())
                .thenReturn(100)
                .thenReturn(99)
                .thenReturn(98)
                .thenReturn(97)
                .thenReturn(96)
                .thenReturn(95);

        when(fighter2.getCurrentHealth())
                .thenReturn(10)
                .thenReturn(0);

        when(fighter1.isAlive())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true);

        when(fighter2.isAlive())
                .thenReturn(true)
                .thenReturn(false);

        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getCurrentFighter()).thenReturn( fighter1 );
        when(player2.getCurrentFighter()).thenReturn( fighter2 );

        SUT.setPlayer1(player1);
        SUT.setPlayer2(player2);

        Player actual = SUT.fight();

        assertEquals(actual, player1);

    }

    @Test
    void fightReturnFighterWhoStayedAlive () {
        Fighter fighter1 = mock(Fighter.class);
        Fighter fighter2 = mock(Fighter.class);

        when(fighter1.getAttack()).thenReturn(50);
        when(fighter2.getAttack()).thenReturn(1);

        when(fighter1.getOriginalHealth()).thenReturn(1);
        when(fighter2.getOriginalHealth()).thenReturn(10000);

        when(fighter1.getCurrentHealth())
                .thenReturn(1)
                .thenReturn(0);

        when(fighter2.getCurrentHealth())
                .thenReturn(10000)
                .thenReturn(99950)
                .thenReturn(99900)
                .thenReturn(99850);

        when(fighter1.isAlive())
                .thenReturn(true)
                .thenReturn(false);

        when(fighter2.isAlive())
                .thenReturn(true);

        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getCurrentFighter()).thenReturn( fighter1 );
        when(player2.getCurrentFighter()).thenReturn( fighter2 );

        SUT.setPlayer1(player1);
        SUT.setPlayer2(player2);

        Player actual = SUT.fight();

        assertEquals(actual, player2);
    }

    @Test
    void testIfADeadFighterCantAttack() {
        Fighter fighter1 = new Fighter(1,1);
        Fighter fighter2 = new Fighter(1,1);

        Player player1 = new Player();
        Player player2 = new Player();

        player1.setCurrentFighter(fighter1);
        player2.setCurrentFighter(fighter2);

        SUT.setPlayer1(player1);
        SUT.setPlayer2(player2);

        SUT.fight();

        assertFalse(!player1.getCurrentFighter().isAlive() && !player2.getCurrentFighter().isAlive());
    }

    @Test
    void playersWithNoFightersShouldSendException() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getCurrentFighter()).thenReturn(null);
        when(player2.getCurrentFighter()).thenReturn(null);

        SUT.setPlayer1(player1);
        SUT.setPlayer2(player2);

        assertThrows(IllegalArgumentException.class, () -> SUT.fight());
    }

    @Test
    void playersHave0PointsAtTheBeginning() {
        assertEquals(0, SUT.getPlayer1().getScore(), "Players should start with 0 points!");
        assertEquals(0, SUT.getPlayer2().getScore(), "Players should start with 0 points!");
    }

    @Test
    void playerScoresOnePointWhenWinsFight() {
        Fighter fighter1 = mock(Fighter.class);
        Fighter fighter2 = mock(Fighter.class);

        when(fighter1.getAttack()).thenReturn(50);
        when(fighter2.getAttack()).thenReturn(100);

        when(fighter1.getOriginalHealth()).thenReturn(1);
        when(fighter2.getOriginalHealth()).thenReturn(1);

        SUT.getPlayer1().setCurrentFighter(fighter1);
        SUT.getPlayer2().setCurrentFighter(fighter2);

        Player winner = SUT.round();
        assertEquals(1, winner.getScore(), "Winner should now have 1 point!");
    }

    @Test
    void playersHaveFighterAtEveryRound() {
        while (!SUT.isGameOver()) {
            SUT.round();

            assertNotNull(SUT.getPlayer1().getCurrentFighter());
            assertNotNull(SUT.getPlayer2().getCurrentFighter());
        }
    }

    @Test
    void fightersAreNotAlwaysTheSame() {
        List<Fighter> fighters = new ArrayList<>();

        while (!SUT.isGameOver()) {
            SUT.round();

            if (!fighters.contains(SUT.getPlayer1().getCurrentFighter()))
                fighters.add(SUT.getPlayer1().getCurrentFighter());
            if (!fighters.contains(SUT.getPlayer2().getCurrentFighter()))
                fighters.add(SUT.getPlayer2().getCurrentFighter());
        }

        assertTrue(fighters.stream().distinct().count()>=3);
    }

    @Test
    void chooseFighterShouldSetPlayersFighters() {
        InputStream systemBackup = System.in;
        in = new ByteArrayInputStream("1 2".getBytes());
        System.setIn( in );

        Player p1 = spy(SUT.getPlayer1());
        Player p2 = spy( SUT.getPlayer2());

        SUT.chooseFighter(p1,p2);

        verify( p1, atLeastOnce() ).setCurrentFighter( any() );
        verify( p2, atLeastOnce() ).setCurrentFighter( any() );

        System.setIn( systemBackup );
    }

    @Test
    void chooseFighterShouldChooseANumber() {
        InputStream systemBackup = System.in;
        in = new ByteArrayInputStream("a".getBytes());
        System.setIn( in );

        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        assertThrows( InputMismatchException.class, () -> SUT.chooseFighter(p1, p2) );

        System.setIn( systemBackup );
    }

}