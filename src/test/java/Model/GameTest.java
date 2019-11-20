package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}