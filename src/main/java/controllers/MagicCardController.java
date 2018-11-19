package controllers;


import entities.MagicCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class MagicCardController {
    private int index;
    @FXML
    private Image IMG_URL;
    @FXML
    private  AnchorPane MAGIC_CARD;
    @FXML
    private Label CARD_NAME, ENERGY_COST, ATTACK_POINTS, MAGIC_METHOD;


    public void setValues(MagicCard card, int index, String value) {
        this.type = value;
        this.index = index;
        this.CARD_NAME.setText(card.getName());
        this.ATTACK_POINTS.setText(Integer.toString(card.getAttackPoints()));
    }
}
