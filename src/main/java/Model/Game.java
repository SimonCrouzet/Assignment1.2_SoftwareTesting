package Model;

import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;
    private int winningScoreLimit = 3;

    public Game() {
        player1 = new Player();
        player2 = new Player();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getWinningScoreLimit() {
        return winningScoreLimit;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        if (player1.getScore() >= winningScoreLimit ) {
            return player1;
        }

        if (player2.getScore() >= winningScoreLimit ) {
            return player2;
        }

        return null;
    }

    public Player fight() {
        if (player2.getCurrentFighter()==null || player2.getCurrentFighter().getOriginalHealth()<=0)
            return player1;
        else if (player1.getCurrentFighter()==null || player1.getCurrentFighter().getOriginalHealth()<=0)
            return player2;

        Random random = new Random();

        int currentHealth_player1 = player1.getCurrentFighter().getOriginalHealth();
        int currentHealth_player2 = player2.getCurrentFighter().getOriginalHealth();

        while (currentHealth_player1 > 0 && currentHealth_player2 > 0 ) {
            boolean turn = random.nextBoolean();

            if (turn) { // Fighter1 attack first
                currentHealth_player2 -= player1.getCurrentFighter().getAttack();

                if (currentHealth_player2 > 0)
                    currentHealth_player1 -= player2.getCurrentFighter().getAttack();
            }
            else { // Fighter2 attack first
                currentHealth_player1 -= player2.getCurrentFighter().getAttack();

                if (currentHealth_player1 > 0)
                    currentHealth_player2 -= player1.getCurrentFighter().getAttack();
            }
        }

        if ( currentHealth_player1 <= 0 )
            return player2;
        else
            return player1;

    }

}
