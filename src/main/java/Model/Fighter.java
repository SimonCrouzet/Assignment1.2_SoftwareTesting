package Model;

public class Fighter {
    private int attack;
    private int currentHealth; // Health during the fight
    private int originalHealth;
    private boolean isAlive; // Verify if the fighter is alive

    public Fighter(int attack, int health) {
        if ( health <= 0 ) {                // we cannot create an already dead fighter
            throw new IllegalArgumentException();
        }

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
            isAlive = false; // Update the boolean isAlive
    }

    public boolean isAlive() {
        return isAlive;
    }
}
