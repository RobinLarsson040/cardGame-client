package client;


public enum MagicMethod {
    HEALPLAYER(1),
    HEALCARD(2),
    HEALALLCARDS(3),
    DAMAGEPLAYER(4),
    DAMAGECARD(5),
    DAMAGEALLCARDS(6);

    private int methodNumber;

    private MagicMethod(int methodNumber) {
        this.methodNumber = methodNumber;
    }

    private int getMethodNumber() {
        return methodNumber;
    }

}

