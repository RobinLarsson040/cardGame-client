package controllers;


import client.ActionClass;
import entities.MagicCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MagicCardController {

    private ActionClass action = ActionClass.getInstance();

    private int index;
    @FXML
    private Image IMG_URL;
    @FXML
    private AnchorPane MAGIC_CARD;
    @FXML
    private Label CARD_NAME, ENERGY_COST, ATTACK_POINTS, MAGIC_METHOD, MAGIC_CARD_TYPE;
    private String cardType;


    public void setValues(MagicCard card, int index, String value) {
        this.MAGIC_CARD_TYPE.setText(value);
        this.index = index;
        this.cardType = value;
        this.CARD_NAME.setText(card.getName());
        this.ATTACK_POINTS.setText(Integer.toString(card.getAttackPoints()));
    }

    public void onClick() throws IOException {
        if (cardType.equals("DAMAGEALLCARDS")
                || cardType.equals("HEALALLCARDS")
                || cardType.equals("HEALPLAYER")
                || cardType.equals("DAMAGEPLAYER")
                || cardType.equals("HEALWHOLETABLE")) {

            action.

        }
    }


}
