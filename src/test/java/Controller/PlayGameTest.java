package Controller;

import View.ConsoleMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

class PlayGameTest {
    private PlayGame SUT;

    @BeforeEach
    void setUp() {
        SUT = new PlayGame();
    }

    @Test
    void startTest() {
        assertTrue(SUT.start(), "start() should return true");
    }

    @Test
    void shouldBePolite() {
        ConsoleMessages mockConsole = new ConsoleMessages();
        mockConsole = Mockito.spy(mockConsole);

        SUT.setConsole(mockConsole);

        SUT.start();

        verify(mockConsole).welcomeMessage();
        verify(mockConsole).goodbyeMessage();
    }
}