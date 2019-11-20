package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}