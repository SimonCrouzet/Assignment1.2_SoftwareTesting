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

    public boolean start() {
        console.welcomeMessage();

        console.newRoundMessage(1);
        Player roundWinner = game.round();
        console.printTheFightWinner(roundWinner);
        console.printStatistics(game);

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
