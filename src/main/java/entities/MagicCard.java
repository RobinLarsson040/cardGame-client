package entities;


import client.MagicMethod;

public class MagicCard implements GameCard {

    private String name;
    Player player;
    private MagicMethod magicMethod;
    private int energyCost;
    private int attackPoints;
    private String imageUrl;
    private String magicType;

    public MagicCard(String name, int energyCost, int attackPoints, String imageUrl, String magicType) {
        this.name = name;
        this.energyCost = energyCost;
        this.attackPoints = attackPoints;
        this.imageUrl = imageUrl;
        this.magicType = magicType;
    }

    public MagicCard() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMagicType() {
        return magicType;
    }

    public void setMagicType(String magicType) {
        this.magicType = magicType;
    }
}
