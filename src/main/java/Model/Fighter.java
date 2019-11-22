package Model;

import java.util.Objects;

public class Fighter {
    private int attack;
    private int currentHealth; // Health during the fight
    private int originalHealth;
    private boolean isAlive; // Verify if the fighter is alive

    public Fighter(int attack, int health) {
        if ( health <= 0 ) {                // we cannot create an already dead fighter
            throw new IllegalArgumentException("Health value cannot be <= 0.");
        }

        if ( attack <= 0 ) {
            throw new IllegalArgumentException("Attack value cannot be <= 0.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fighter fighter = (Fighter) o;
        return attack == fighter.attack &&
                currentHealth == fighter.currentHealth &&
                originalHealth == fighter.originalHealth &&
                isAlive == fighter.isAlive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attack, currentHealth, originalHealth, isAlive);
    }
}
