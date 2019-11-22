package View;

import Model.Fighter;
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
    void statisticsAfterOneRoundWonByPlayer1Test() {
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

    @Test
    void statisticsAfterTwoRoundsWonByPlayer2Test() {
        outContent.reset();
        Game gameMock = mock(Game.class);
        when(gameMock.getPlayer1()).thenReturn( mock(Player.class));
        when(gameMock.getPlayer2()).thenReturn(mock(Player.class));

        when(gameMock.getPlayer1().getScore()).thenReturn(0);
        when(gameMock.getPlayer2().getScore()).thenReturn(2);

        SUT.printStatistics(gameMock);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("After this round we have below scores:\n");
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 1", 0) );
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 2", 2) );
        stringBuilder.append("\n");

        String expected = stringBuilder.toString();

        assertEquals( expected, outContent.toString() );
    }

    @Test
    void printFightersSmallValuesTest() {
        outContent.reset();
        Game gameMock = mock(Game.class);
        when(gameMock.getPlayer1()).thenReturn(mock(Player.class));
        when(gameMock.getPlayer2()).thenReturn(mock(Player.class));

        Fighter fighter1 = mock(Fighter.class);
        Fighter fighter2 = mock(Fighter.class);

        when(gameMock.getPlayer1().getCurrentFighter()).thenReturn(fighter1);
        when(gameMock.getPlayer1().getCurrentFighter()).thenReturn(fighter2);

        when(fighter1.getAttack()).thenReturn(10);
        when(fighter1.getOriginalHealth()).thenReturn(20);
        when(fighter1.getCurrentHealth()).thenReturn(20);

        when(fighter2.getAttack()).thenReturn(5);
        when(fighter2.getOriginalHealth()).thenReturn(15);
        when(fighter2.getCurrentHealth()).thenReturn(15);

        SUT.printFightersStatistics(gameMock);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("In this round fighters are:\n");
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-15s%-10s%-5d%-10s%-5d","1st fighter", "ATTACK:", 10, "HEALTH:", 20) );
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-15s%-10s%-5d%-10s%-5d","2nd fighter", "ATTACK:", 5, "HEALTH:", 15) );
        stringBuilder.append("\n");

        String expected = stringBuilder.toString();

        assertEquals( expected, outContent.toString() );
    }
}