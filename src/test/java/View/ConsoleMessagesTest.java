package View;

import Model.Game;
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
                "\nGOODBYE!";

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void show4thRoundMessage() {
        outContent.reset();
        SUT.newRoundMessage(4);
        String expected = "Time for ROUND number "+ 4 +"!\n";

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void show2ndRoundMessage() {
        outContent.reset();
        SUT.newRoundMessage(2);
        String expected = "Time for ROUND number "+ 2 +"!\n";

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void statisticsBeforeGameTest() {
        outContent.reset();
        Game gameMock = mock(Game.class);
        when(gameMock.getPlayer1()).thenReturn( mock(Player.class));
        when(gameMock.getPlayer2()).thenReturn(mock(Player.class));

        when(gameMock.getPlayer1().getScore()).thenReturn(0);
        when(gameMock.getPlayer2().getScore()).thenReturn(0);

        SUT.printStatistics(gameMock);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("After this round we have below scores:\n");
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 1", 0) );
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 2", 0) );
        stringBuilder.append("\n");

        String expected = stringBuilder.toString();

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void statisticsAfretOneRoundWonByPlayer1Test() {
        outContent.reset();
        Game gameMock = mock(Game.class);
        when(gameMock.getPlayer1()).thenReturn( mock(Player.class));
        when(gameMock.getPlayer2()).thenReturn(mock(Player.class));

        when(gameMock.getPlayer1().getScore()).thenReturn(1);
        when(gameMock.getPlayer2().getScore()).thenReturn(0);

        SUT.printStatistics(gameMock);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("After this round we have below scores:\n");
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 1", 1) );
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 2", 0) );
        stringBuilder.append("\n");

        String expected = stringBuilder.toString();

        assertEquals( expected, outContent.toString() );
    }

}