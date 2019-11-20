package Model;

public class Player {
    private int score;
    private Fighter currentFighter;

    public Player(){
        score = 0;
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
