package Controller;

import Model.Game;

public class PlayGame {
    private Game game;

    public boolean start() {
        game = new Game();
        return true;
    }
}
