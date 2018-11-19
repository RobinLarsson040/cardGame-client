package controllers;

import client.ClientGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class InfoPrinterController {

    @FXML
    private Label INFO_TEXT_INPUT;

    @FXML
    public void setINFO_TEXT_INPUT(String INFO_TEXT_INPUT) {
        this.INFO_TEXT_INPUT.setText(INFO_TEXT_INPUT);
    }

    @FXML
    public void printInfo(String text) {
        System.out.println("MESSAGE TO PRINT INFO " + text);
        INFO_TEXT_INPUT.setText(text);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> nextPlayerTurn(ClientGame.getDto().getPlayerOneTurn())));
        timeline.play();
    }

    public void nextPlayerTurn(boolean playerOneTurn) {
        INFO_TEXT_INPUT.setText(playerOneTurn ? "Player 1:s turn" : "Player 2:s turn");
    }
}
