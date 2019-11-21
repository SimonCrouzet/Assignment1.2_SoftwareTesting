package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterTest {
    Fighter SUT;

    @BeforeEach
    void setUp() {
        SUT = new Fighter(10, 50);
    }

    @Test
    void getCurrentHealthWhenUndamaged() {
        assertEquals(50, SUT.getCurrentHealth());
    }

    @Test
    void getCurrentHealthAfterDamage() {
        SUT.takeDamage(10);
        assertEquals(40, SUT.getCurrentHealth());
    }

    @Test
    void getAttack() {
        assertEquals(10, SUT.getAttack());
    }

    @Test
    void getOriginalHealth() {
        assertEquals(50, SUT.getOriginalHealth());
    }
}