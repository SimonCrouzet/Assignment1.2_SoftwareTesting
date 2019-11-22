package View;

import Model.Player;
import static org.mockito.Mockito.*;

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
        outContent.reset();
        SUT.welcomeMessage();
        String expected = "Welcome to the BIG FIGHT game!\n";

        assertEquals( expected, outContent.toString() );

    }

    @Test
    void showThwWinnerOfTheFight () throws IOException {

        outContent.reset();
        Player p = mock(Player.class);
        when(p.getPlayerNumber()).thenReturn(1);

        SUT.printTheFightWinner( p );
        String expected = "In this round the winner was player number " + 1 + "!\n";

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void showGoodbyeMessage() {
        outContent.reset();
        SUT.goodbyeMessage();
        String expected = "What a game it was!\n" +
                "\tStay STRONG fighters!" +
                "\n GOODBYE!";

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void showNewRoundMessage() {
        outContent.reset();
        SUT.newRoundMessage(4);
        String expected = "Time for ROUND number "+ 4 +"!\n";

        assertEquals( expected, outContent.toString() );
    }

}