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
    private Player mockPlayer;
    private ConsoleMessages mockConsole;

    @BeforeEach
    void setUp() {
        SUT = new PlayGame();

        mockGame = mock(Game.class);
        mockPlayer = mock(Player.class);

        SUT.setGame(mockGame);

        when(mockGame.getWinner()).thenReturn(mockPlayer);
        when(mockPlayer.getPlayerNumber()).thenReturn(1);
        when(mockPlayer.getScore()).thenReturn(3);
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

        verify(mockConsole).printGameWinner(mockPlayer);
    }
}