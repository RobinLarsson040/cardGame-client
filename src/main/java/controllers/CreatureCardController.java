package controllers;


import client.ActionClass;
import client.ClientGame;
import entities.CreatureCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class CreatureCardController {

    private ActionClass action = ActionClass.getInstance();

    @FXML
    private Image IMG_URL;
    @FXML
    private AnchorPane CREATURE_CARD;
    @FXML
    private Label CARD_NAME, CARD_HP;

    private int index;
    private String table;
    private boolean isUsed;
    private String currentPlayer;
    private boolean playerOneTurn;

    public void setValues(CreatureCard card, int index, String value) {
        this.table = value;
        this.index = index;
        this.isUsed = card.getIsUsed();
        CARD_NAME.setText(card.getName());
        CARD_HP.setText(Integer.toString(card.getHp()));
        checkIfUsedAndDisable();
        getPlayerAndPlayerTurn();
    }

    private void checkIfUsedAndDisable() {
        if (isUsed && table.equals("playerTable")) {
            CREATURE_CARD.setDisable(true);
        }
    }

    public void onClick() throws IOException {
        switch (table) {
            case "hand":
                playCardOnTable();
                break;
            case "playerTable":
                handlePlayerCardsOnTable();
                break;
            case "enemyTable":
                handleEnemyTable();
        }
    }

    private void handleEnemyTable() throws IOException {
        setBorderColor();
        action.setCard2(index);
    }

    private void handlePlayerCardsOnTable() throws IOException {
        setBorderColor();
        action.setCard1(index);
    }

    private void playCardOnTable() throws IOException {
        ClientGame.getClientNetwork().sendMessage("PLAY_CARD:" + (index + 1));
    }

    private void setBorderColor() {
        int depth = 70;
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.RED);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);
        CREATURE_CARD.setEffect(borderGlow);
    }

    private void getPlayerAndPlayerTurn() {
        currentPlayer = ClientGame.getPlayer();
        playerOneTurn = ClientGame.getDto().getPlayerOneTurn();

        switch (currentPlayer) {
            case "player1":
                if (!playerOneTurn) {
                    this.CREATURE_CARD.setDisable(true);
                }
                break;
            case "player2":
                if (playerOneTurn) {
                    this.CREATURE_CARD.setDisable(true);
                }
                break;
        }
    }

}
