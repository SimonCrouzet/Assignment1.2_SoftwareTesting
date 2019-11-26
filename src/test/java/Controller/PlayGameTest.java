package Controller;

import Model.Game;
import Model.Player;
import View.ConsoleMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

class PlayGameTest {
    private PlayGame SUT;
    private Game mockGame;
    private Player mockPlayerWinner;
    private Player mockPlayerLoser;
    private ConsoleMessages mockConsole;

    /**
     * mock the SUT's attributes and the methods called, to avoid any NullPointerException during our tests
     */
    @BeforeEach
    void setUp() {
        SUT = new PlayGame();

        mockGame = mock(Game.class);
        mockPlayerWinner = mock(Player.class);
        mockPlayerLoser = mock(Player.class);


        mockGame.setPlayer1(mockPlayerWinner);
        mockGame.setPlayer2(mockPlayerLoser);
        SUT.setGame(mockGame);

        when(mockGame.getWinner()).thenReturn(mockPlayerWinner);

        when(mockPlayerWinner.getPlayerNumber()).thenReturn(1);
        when(mockPlayerLoser.getPlayerNumber()).thenReturn(2);

        when(mockPlayerWinner.getScore())
                .thenReturn(1)
                .thenReturn(1)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);
        when(mockPlayerWinner.getScore())
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(2);

        when(mockGame.round())
                .thenReturn(mockPlayerWinner)
                .thenReturn(mockPlayerLoser)
                .thenReturn(mockPlayerLoser)
                .thenReturn(mockPlayerWinner)
                .thenReturn(mockPlayerWinner);
    }

    @Test
    void startTest() {
        assertTrue(SUT.start(), "start() should return true");
    }

    @Test
    void shouldBePolite() {
        mockConsole = new ConsoleMessages();
        mockConsole = Mockito.spy(mockConsole);

        SUT.setConsole(mockConsole);

        SUT.start();

        verify(mockConsole).welcomeMessage();
        verify(mockConsole).goodbyeMessage();
    }

    @Test
    void shouldPrintGameWinner() {
        mockConsole = new ConsoleMessages();
        mockConsole = Mockito.spy(mockConsole);

        SUT.setConsole(mockConsole);

        SUT.start();

        verify(mockConsole).printGameWinner(mockPlayerWinner);
    }

    @Test
    void shouldPrintCorrectInformationsAtEachRound() {
        mockConsole = new ConsoleMessages();
        mockConsole = Mockito.spy(mockConsole);

        SUT.setConsole(mockConsole);

        SUT.start();

        verify(mockConsole).newRoundMessage(1);
        verify(mockConsole).printTheFightWinner(mockPlayerWinner);
        verify(mockConsole).printStatistics(mockGame);
    }
}