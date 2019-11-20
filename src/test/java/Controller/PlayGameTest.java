package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayGameTest {
    private PlayGame SUT;

    @BeforeEach
    void setUp() {
        SUT = new PlayGame();
    }

    @Test
    void startTest() {
        assertTrue("start() should return true", SUT.start());
    }
}