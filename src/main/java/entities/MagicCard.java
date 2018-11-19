package entities;


import client.MagicMethod;

public class MagicCard implements GameCard {

    private String name;
    Player player;
    private MagicMethod magicMethod;
    private int energyCost;
    private int attackPoints;

    public MagicCard() {
    }

    public MagicCard(String name, int energyCost, int attackPoints) {
        this.name = name;
        this.energyCost = energyCost;
        this.attackPoints = attackPoints;
    }

    public MagicMethod getMagicMethod() {
        return magicMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoints(){
        return attackPoints;
    }
}
