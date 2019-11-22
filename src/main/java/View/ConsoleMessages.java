package View;

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
        System.out.println("GOODBYE!");
    }

    public void newRoundMessage( int roundNumber ) {
        System.out.println("Time for ROUND number "+ 4 +"!");
    }

}
