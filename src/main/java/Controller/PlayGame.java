package Controller;

import Model.Game;
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
        console.goodbyeMessage();

        return true;
    }

    protected void setConsole(ConsoleMessages console) {
        this.console = console;
    }
}
