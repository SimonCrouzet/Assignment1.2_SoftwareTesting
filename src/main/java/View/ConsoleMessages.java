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
        System.out.print("What a game it was!\n" +
                "\tStay STRONG fighters!" +
                "\n GOODBYE!");
    }

}
