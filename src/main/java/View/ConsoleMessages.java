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

    public void askForFightersNumbers() {
        System.out.println("\nPLAYERS! It is time to choose your fighters!" +
                "\nChoose wisely a number between 1 and 3:");

    }

    public void printGameWinner(Player player) {
        System.out.print("AND THE WINNER IS Player number " + player.getPlayerNumber() + "!\n");            // depending on the score limit, player who gets this score first wins
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
        stringBuilder.append( String.format("%-20s%-15d","Player 1", game.getPlayer1().getScore()) );       // getting first player's score
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-20s%-15d","Player 2", game.getPlayer2().getScore()) );       // getting second player's score
        stringBuilder.append("\n");

        String output = stringBuilder.toString();

        System.out.print(output);
    }

    /*
    * We print attack and health value for each fighter
    * We use nice looking layout
    * */
    public void printFightersStatistics(Game game) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("In this round fighters are:\n");
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-15s%-10s%-5d%-10s%-5d","1st fighter", "ATTACK:", game.getPlayer1().getCurrentFighter().getAttack(), "HEALTH:", game.getPlayer1().getCurrentFighter().getOriginalHealth()) );
        stringBuilder.append("\n");
        stringBuilder.append( String.format("%-15s%-10s%-5d%-10s%-5d","2nd fighter", "ATTACK:", game.getPlayer2().getCurrentFighter().getAttack(), "HEALTH:", game.getPlayer2().getCurrentFighter().getOriginalHealth()) );
        stringBuilder.append("\n");

        String output = stringBuilder.toString();

        System.out.print(output);
    }

}
