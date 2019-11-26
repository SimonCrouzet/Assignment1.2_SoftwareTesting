package Controller;

import Model.Game;
import Model.Player;
import View.ConsoleMessages;

public class PlayGame {
    private Game game;
    private ConsoleMessages console;

    public PlayGame(Game game, ConsoleMessages console) {
        this.game = game;
        this.console = console;
    }

    public PlayGame() {
        this(new Game(), new ConsoleMessages());
    }

    /**
     * Method used to play to the game. Do the correct calls about the view and the models
     * @return true if we don't have any technical issues during the game
     */
    public boolean start() {
        console.welcomeMessage();

        int roundCount=0;
        while (!game.isGameOver()) {
            roundCount++;
            console.newRoundMessage(roundCount);

            Player roundWinner = game.round();

            console.printTheFightWinner(roundWinner);
            console.printStatistics(game);
        }

        Player winner = game.getWinner();
        console.printGameWinner(winner);

        console.goodbyeMessage();
        return true;
    }

    protected void setConsole(ConsoleMessages console) {
        this.console = console;
    }

    protected void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public ConsoleMessages getConsole() {
        return console;
    }
}
