package Model;

public class Player {
    private int playerNumber;
    private int score;
    private Fighter currentFighter;

    public Player () {
        score = 0;
    }

    public Player( int number ){
        playerNumber = number;
        score = 0;
    }

    public int getPlayerNumber () {
        return playerNumber;
    }

    public void setPlayerNumber( int number ) {
        this.playerNumber = number;
    }

    public int getScore() {
        return score;
    }

    public Fighter getCurrentFighter() {
        return currentFighter;
    }

    public void setCurrentFighter(Fighter currentFighter) {
        this.currentFighter = currentFighter;
    }
}
