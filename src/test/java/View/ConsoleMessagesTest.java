package View;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleMessagesTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOutput = System.out;

    private ConsoleMessages SUT;

    @BeforeEach
    void setUp() {
        SUT = new ConsoleMessages();
    }

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOutput);
    }


    @Test
    void showWelcomeMessageTest () throws IOException {
        SUT.welcomeMessage();
        String expected = "Welcome to the BIG FIGHT game!" +
                "\nChoose how many rounds you want to play.\n";

        assertEquals( expected, outContent.toString() );
    }

}