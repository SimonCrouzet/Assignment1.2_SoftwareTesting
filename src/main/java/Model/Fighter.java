package Model;

public class Fighter {
    private int attack;
    private int currentHealth;
    private int originalHealth;
    private boolean isAlive;

    public Fighter(int attack, int health) {
        this.attack = attack;
        this.originalHealth = health;
        this.currentHealth = health;
        this.isAlive = true;
    }

    public int getAttack() {
        return attack;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void takeDamage( int damage ) {
        this.currentHealth -= damage;

        if (currentHealth<=0)
            isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
