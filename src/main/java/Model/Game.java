package Model;

import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;
    private int winningScoreLimit = 3;
    private boolean gameOver;

    public Game() {
        player1 = new Player();
        player2 = new Player();
        gameOver = false;
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
            gameOver = true;
            return player1;
        }

        if (player2.getScore() >= winningScoreLimit ) {
            gameOver = true;
            return player2;
        }

        return null;
    }

    /*
    fight() method represents ONE ROUND between one fighter from each player
    1. We check if each player has a fighter and if they are alive
    2. we initialize currentHealt variables
    3. while both of the fighters are alive:
        3.a. We randomly choose who attacks first
        3.b. In each turn both of them attack
        3.c. We always check if during the first hit the fighter who took damage is still alive
    4. We return the player whose fighter stayed alive
    * */

    public Player fight() {
        if (player1.getCurrentFighter()==null && player2.getCurrentFighter()==null)
            throw new IllegalArgumentException("Both of the players have no fighters !");

        if (player2.getCurrentFighter()==null || player2.getCurrentFighter().getOriginalHealth()<=0)
            return player1;
        else if (player1.getCurrentFighter()==null || player1.getCurrentFighter().getOriginalHealth()<=0)
            return player2;

        Random random = new Random();

        while (player1.getCurrentFighter().isAlive() && player2.getCurrentFighter().isAlive()) {
            boolean turn = random.nextBoolean();

            if (turn) { // Fighter1 attack first
                player2.getCurrentFighter().takeDamage(player1.getCurrentFighter().getAttack());

                if (player2.getCurrentFighter().isAlive())
                    player1.getCurrentFighter().takeDamage(player2.getCurrentFighter().getAttack());
            }
            else { // Fighter2 attack first
                player1.getCurrentFighter().takeDamage(player2.getCurrentFighter().getAttack());

                if (player1.getCurrentFighter().isAlive())
                    player2.getCurrentFighter().takeDamage(player1.getCurrentFighter().getAttack());
            }
        }

        if (!player1.getCurrentFighter().isAlive())
            return player2;
        else
            return player1;

    }

    /**
     * Initialize a round, then score the point
     * @return the round winner
     */
    public Player round() {
        player1.setCurrentFighter(new Fighter(10,20));
        player2.setCurrentFighter(new Fighter(10,20));

        Player winner = fight();

        gameOver = winner.scoreAPoint(winningScoreLimit);

        return winner;
    }

    public Player play() {
        while (!gameOver) {
            round();
        }
        return getWinner();
    }

}
