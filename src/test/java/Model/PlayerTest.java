package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player SUT;

    @BeforeEach
    void setUp() {
        SUT = new Player();
    }

    @Test
    void returnPlayerNumberTest() {

        int actual = SUT.getPlayerNumber();

        assertEquals(1, actual);
    }
}