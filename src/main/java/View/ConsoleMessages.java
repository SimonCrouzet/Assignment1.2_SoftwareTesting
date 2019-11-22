package View;

import Model.Game;
import Model.Player;

public class ConsoleMessages {      // class with messages that we would print to the console

    public void welcomeMessage() {
        System.out.print("Welcome to the BIG FIGHT game!\n");
    }

    public void printTheFightWinner (Player p) {
        System.out.print("In this round the winner was player number " + p.getPlayerNumber() + "!\n");
    }

    public void goodbyeMessage() {
        System.out.println("What a game it was!");
        System.out.println("\tStay STRONG fighters!");
        System.out.print("GOODBYE!");
    }

    public void newRoundMessage( int roundNumber ) {
        System.out.println("Time for ROUND number "+ roundNumber +"!");             // before each round this message is printed
    }

    public void printStatistics(Game game) {                                        // in Game class object we store score info
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("After this round we have below scores:\n");
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 1", game.getPlayer1().getScore()) );
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 2", 0) );
        stringBuilder.append("\n");

        String output = stringBuilder.toString();

        System.out.print(output);
    }

}
