package controllers;


import client.ActionClass;
import client.ClientGame;
import entities.MagicCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MagicCardController {


    private ActionClass action = ActionClass.getInstance();
    private String currentPlayer;
    private boolean playerOneTurn;
    private int index;
    @FXML
    private Image IMG_URL;
    @FXML
    private AnchorPane MAGIC_CARD;
    @FXML
    private Label CARD_NAME, MAGIC_CARD_TYPE, MAGIC_CARD_EC, MAGIC_CARD_AP;
    private String cardType;
    private Tooltip tooltip = new Tooltip();


    public void setValues(MagicCard card, int index, String value) {
        this.MAGIC_CARD_TYPE.setText(value);
        this.index = index;
        this.CARD_NAME.setText(card.getName());
        this.MAGIC_CARD_AP.setText(Integer.toString(card.getAttackPoints()));
//        this.MAGIC_CARD_EC.setText(Integer.toString(card.));
        if (value.contains("ONE")) {
            this.MAGIC_CARD_TYPE.setText("TARGET");
        } else {
            this.MAGIC_CARD_TYPE.setText("INSTANT");
        }
        this.cardType = value;
        tooltip.setText(setToolTipText());
        Tooltip.install(MAGIC_CARD,tooltip);

        getPlayerAndPlayerTurn();
    }

    public void onClick() throws IOException {
        setBorderColor();
        if (cardType.equals("DAMAGEALLCARDS")
                || cardType.equals("HEALALLCARDS")
                || cardType.equals("HEALPLAYER")
                || cardType.equals("DAMAGEPLAYER")
                || cardType.equals("HEALWHOLETABLE")) {
            System.out.println(index);
            action.setMagicCard(index, "instant");
        } else if (cardType.equals("HEALONECARD")) {
            action.setMagicCard(index, "heal");
        } else if (cardType.equals("DAMAGEONECARD")) {
            action.setMagicCard(index, "damage");
        }
    }

    private void setBorderColor() {
        int depth = 70;
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.BLUEVIOLET);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);
        MAGIC_CARD.setEffect(borderGlow);
    }

    private String setToolTipText() {
        String value = "";
        switch (cardType) {
            case "DAMAGEALLCARDS":
                value = "Damage all enemy cards";
                break;
            case "HEALPLAYER":
                value = "Heal player";
                break;
            case "DAMAGEPLAYER":
                value = "Attack Enemy";
                break;
            case "HEALALLCARDS":
                value = " Heal all your Cards On Table";
                break;
            case "HEALONECARD":
                value = "Pick one card to Heal";
                break;
            case "DAMAGEONECARD":
                return "Pick a Enemy card to Attack";
        }
        return value;
    }

    private void getPlayerAndPlayerTurn() {
        currentPlayer = ClientGame.getPlayer();
        playerOneTurn = ClientGame.getDto().getPlayerOneTurn();

        switch (currentPlayer) {
            case "player1":
                if (!playerOneTurn) {
                    this.MAGIC_CARD.setDisable(true);
                }
                break;
            case "player2":
                if (playerOneTurn) {
                    this.MAGIC_CARD.setDisable(true);
                }
                break;
        }
    }


}
