package Model;

public class Fighter {
    private int attack;
    private int health;

    public Fighter(int attack, int health) {
        this.attack = attack;
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
