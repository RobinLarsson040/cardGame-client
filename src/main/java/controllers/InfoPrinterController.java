package controllers;

import client.ClientGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class InfoPrinterController {

    @FXML
    private Label INFO_TEXT_INPUT;

    @FXML
    private Label INFO_TEXT_INPUT2;

    @FXML
    public void setINFO_TEXT_INPUT(String INFO_TEXT_INPUT) {
        this.INFO_TEXT_INPUT.setText(INFO_TEXT_INPUT);
    }

    @FXML
    public void printInfo(String text) {
        INFO_TEXT_INPUT2.setTextFill(text.startsWith("ERROR") ? Color.RED : Color.WHITE);

        String[] msgArray = text.split(":");
        String msg = msgArray[1];
        System.out.println("MESSAGE TO PRINT INFO " + text);
        INFO_TEXT_INPUT2.setText(msg);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(5000),
                ae -> INFO_TEXT_INPUT2.setText("")));
        timeline.play();
    }

    public void nextPlayerTurn(boolean playerOneTurn) {
        INFO_TEXT_INPUT.setText(playerOneTurn ? "Player 1:s turn" : "Player 2:s turn");
    }
}
