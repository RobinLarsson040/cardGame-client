package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private AnchorPane SIDE_PANE;
    @FXML
    private AnchorPane MENU_BTN_ANCHOR_PANE;
    @FXML
    private Button NEW_GAME_BTN;
    @FXML
    private Button HIGH_SCORE_BTN;


    public void openHighScore() throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/highScore.fxml"));
//        MENU_BTN_ANCHOR_PANE.getChildren().setAll(pane);
    }

    public void startNewGame() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/gameConnector.fxml"));
        SIDE_PANE.getChildren().setAll(pane);
    }

}
