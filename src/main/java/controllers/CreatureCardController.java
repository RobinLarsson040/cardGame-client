package controllers;


import client.ActionClass;
import client.ClientGame;
import entities.CreatureCard;
import entities.GameCard;
import entities.Magic;
import entities.MagicCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class CreatureCardController {



    private ActionClass action = ActionClass.getInstance();

    @FXML
    private ImageView IMG_URL;
    @FXML
    private AnchorPane CREATURE_CARD;
    @FXML
    private Label CARD_NAME, CARD_HP, CARD_CD, CARD_DP, CARD_AP, CARD_AT;

    private int index;
    private String table;
    private boolean isUsed;
    private String currentPlayer;
    private boolean playerOneTurn;
    private boolean magicCardOnHand;
    Boolean isClicked = false;
    DropShadow borderGlow;

    public void setValues(CreatureCard card, int index, String value) {
        System.out.println("/img/"+ card.getImageUrl());
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("/img" + card.getImageUrl() ).toExternalForm());
        checkIfHandContainsMagicCards();
        this.table = value;
        this.index = index;
        this.isUsed = card.getIsUsed();
        IMG_URL.setImage(image);
        CARD_NAME.setText(card.getName());
        CARD_HP.setText(Integer.toString(card.getHp()));
        CARD_AP.setText(Integer.toString(card.getAttackPoints()));
        CARD_CD.setText(Integer.toString(card.getCoolDown()));
        CARD_DP.setText(Integer.toString(card.getDefencePoint()));
        CARD_AT.setText(card.getAttackType());
        checkIfUsedAndDisable();
        getPlayerAndPlayerTurn();
    }

    private void checkIfUsedAndDisable() {
        if (isUsed && table.equals("playerTable") && !magicCardOnHand) {
            CREATURE_CARD.setDisable(true);
        }
    }

    public void onClick() throws IOException {
        isClicked = !isClicked;
        System.out.println(isClicked);
        switch (table) {
            case "hand":
                SoundPlayer.getInstance().creatureCardClicked();
                playCardOnTable();
                break;
            case "playerTable":
                SoundPlayer.getInstance().creatureCardClicked();
                handlePlayerCardsOnTable();
                break;
            case "enemyTable":
                SoundPlayer.getInstance().tablePick();
                handleEnemyTable();
        }
    }

    private void handleEnemyTable() throws IOException {
        if (isClicked) {
            action.setCard2(index);
            setBorderColor();
        } else {
            resetBorder();
            action.setCard2(-1);
        }
    }

    private void handlePlayerCardsOnTable() throws IOException {
        if (isClicked) {
            action.setCard1(index);
            setBorderColor();
        } else {
            resetBorder();
            action.setCard1(-1);
        }
    }

    private void playCardOnTable() throws IOException {
        ClientGame.getClientNetwork().sendMessage("PLAY_CARD:" + (index + 1));
    }

    private void setBorderColor() {
        int depth = 70;
        borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.RED);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);
        CREATURE_CARD.setEffect(borderGlow);
    }

    private void resetBorder() {
        int depth = 70;
        borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.rgb(0,0,0,0));
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

    private void checkIfHandContainsMagicCards() {
        List<GameCard> cardsOnHand = ClientGame.getDto().getCardsOnHand();
        for (GameCard card : cardsOnHand) {
            if (card instanceof MagicCard) {
                magicCardOnHand = true;
            }
        }
    }

}
