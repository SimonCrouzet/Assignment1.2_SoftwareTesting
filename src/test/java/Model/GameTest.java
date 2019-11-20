package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void figthShouldReturnAWinner () {
        Player actual = SUT.fight();
        boolean isOneOfThePlayers = (actual.equals( SUT.getPlayer1() ) || actual.equals(SUT.getPlayer2()));

        assertTrue( isOneOfThePlayers, "The winner of the fight should be one of the game players." );
    }
}