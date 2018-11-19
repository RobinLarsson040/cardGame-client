package client;

import entities.MagicCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionClass implements Initializable {

    private static ActionClass instance;

    private ActionClass() {
    }

    public static ActionClass getInstance() {
        if (instance == null) {
            instance = new ActionClass();
        }
        return instance;
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    private String currentPlayer;
    private boolean playerOneTurn;
    private int card1 = -1;
    private int card2 = -1;
    private int magicCard = -1;

    public void setMagicCard(int magicCard, String attackType) throws IOException {
        this.magicCard = magicCard;

        if (attackType.equals("instant")) {
            castMagicInstant();
            resetCards();
        }

    }

    public void setCard1(int card1) throws IOException {
        this.card1 = card1 + 1;
        attackPLayer();

        if (magicCard != -1 && card1 != -1) {
            castMagicTargetHeal();
            resetCards();
        }
    }

    public void setCard2(int card2) throws IOException {
        this.card2 = card2 + 1;
        if (card1 != -1 && card2 != -1) {
            attack();
            resetCards();
        } else if (card2 != -1 && magicCard != -1) {
            castMagicTargetDamage();
            resetCards();
        }

    }

    private void attack() throws IOException {
        if (card1 >= 0 && card2 >= 0) {
            System.out.println("ATTACK_CARD:" + card1 + ":" + card2);
            ClientGame.getClientNetwork().sendMessage("ATTACK_CARD:" + card1 + ":" + card2);
        }
    }

    private void castMagicInstant() throws IOException {
        if (card1 >= 0) {
            System.out.println("CAST_MAGIC_INSTANT:" + card1);
            ClientGame.getClientNetwork().sendMessage("CAST_MAGIC_INSTANT:" + card1);
        }
    }

    private void castMagicTargetDamage() throws IOException {
        if (card1 >= 0 && card2 >= 0) {
            System.out.println("CAST_MAGIC_TARGET_DAMAGE:" + magicCard + ":" + card2);
            ClientGame.getClientNetwork().sendMessage("CAST_MAGIC_TARGET_DAMAGE:" + magicCard + ":" + card2);
        }
    }

    private void castMagicTargetHeal() throws IOException {
        if (magicCard >= 0 && card1 >= 0) {
            System.out.println("CAST_MAGIC_TARGET_HEAL:" + magicCard + ":" + card1);
            ClientGame.getClientNetwork().sendMessage("CAST_MAGIC_TARGET_HEAL:" + magicCard + ":" + card1);
        }
    }

    private void resetCards() {
        card1 = -1;
        card2 = -1;
        magicCard = -1;
    }

    @FXML
    public void endTurn() throws IOException {
        ClientGame.getClientNetwork().sendMessage("END_TURN");
    }

    private void attackPLayer() throws IOException {
        currentPlayer = ClientGame.getPlayer();
        playerOneTurn = ClientGame.getDto().getPlayerOneTurn();

        switch (currentPlayer) {
            case " player1":
                if (playerOneTurn) {
                    if (ClientGame.getDto().getPlayer2CardsOnTable().size() == 0 && ClientGame.getDto().getTurn() > 1) {
                        ClientGame.getClientNetwork().sendMessage("ATTACK_PLAYER:" + card1);
                    }
                }
                break;
            case " player2":
                if (!playerOneTurn) {
                    if (ClientGame.getDto().getPlayer1CardsOnTable().size() == 0 && ClientGame.getDto().getTurn() > 1) {
                        ClientGame.getClientNetwork().sendMessage("ATTACK_PLAYER:" + card1);
                    }
                }
                break;
        }
    }

}
