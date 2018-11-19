package client;

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
    private  boolean playerOneTurn;
    private int playerCard = -1;
    private int enemyCard = -1;

    public void setPlayerCard(int playerCard) throws IOException {
        this.playerCard = playerCard + 1;
        attackPLayer();
    }

    public void setEnemyCard(int enemyCard) throws IOException {
        this.enemyCard = enemyCard + 1;
        attack();
    }

    private void attack() throws IOException {
        if (playerCard >= 0) {
            System.out.println("ATTACK_CARD:" + playerCard + ":" + enemyCard);
            ClientGame.getClientNetwork().sendMessage("ATTACK_CARD:" + playerCard + ":" + enemyCard);
        }
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
                        ClientGame.getClientNetwork().sendMessage("ATTACK_PLAYER:"+ playerCard);
                    }
                }
                break;
            case " player2":
                if (!playerOneTurn) {
                    if (ClientGame.getDto().getPlayer1CardsOnTable().size() == 0 && ClientGame.getDto().getTurn() > 1) {
                        ClientGame.getClientNetwork().sendMessage("ATTACK_PLAYER:"+ playerCard);
                    }
                }
                break;
        }
    }

}
