package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {
    private Game SUT;

    @BeforeEach
    void setUp() {
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
    void playUntilWeHaveAWinner() {
        Player winner = SUT.play();

        assertEquals(SUT.getWinningScoreLimit(), winner.getScore(), "The winner should reach the score limit!");

        if (!SUT.getPlayer1().equals(winner))
            assertTrue(SUT.getPlayer1().getScore()<SUT.getWinningScoreLimit(), "The looser should not reach the score" +
                    " limit!");
        else if (!SUT.getPlayer2().equals(winner))
            assertTrue(SUT.getPlayer2().getScore()<SUT.getWinningScoreLimit(), "The looser should not reach the score" +
                    " limit!");

        assertTrue(SUT.getWinner().equals(SUT.getPlayer1()) || SUT.getWinner().equals(SUT.getPlayer2()));
    }

    @Test
    void playersHaveFighterAtEveryRound() {
        while (!SUT.getGameOver()) {
            SUT.round();

            assertNotNull(SUT.getPlayer1().getCurrentFighter());
            assertNotNull(SUT.getPlayer2().getCurrentFighter());
        }
    }

    @Test
    void fightersAreNotAlwaysTheSame() {
        List<Fighter> fighters = new ArrayList<>();

        while (!SUT.getGameOver()) {
            SUT.round();

            if (!fighters.contains(SUT.getPlayer1().getCurrentFighter()))
                fighters.add(SUT.getPlayer1().getCurrentFighter());
            if (!fighters.contains(SUT.getPlayer2().getCurrentFighter()))
                fighters.add(SUT.getPlayer2().getCurrentFighter());
        }

        assertTrue(fighters.size()>=3);
    }
}