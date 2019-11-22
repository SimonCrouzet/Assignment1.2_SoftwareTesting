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
    void returnPlayerNumberTestShouldReturnOne() {
        SUT.setPlayerNumber(1);
        int actual = SUT.getPlayerNumber();

        assertEquals(1, actual);
    }

    @Test
    void returnPlayerNumberShouldReturnTwo() {
        SUT.setPlayerNumber( 2 );
        int actual = SUT.getPlayerNumber();

        assertEquals(2, actual);
    }
}