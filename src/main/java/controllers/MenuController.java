package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private ImageView SOUND_TOGGLE;
    @FXML
    private AnchorPane SIDE_PANE;
    @FXML
    private AnchorPane MENU_BTN_ANCHOR_PANE;
    @FXML
    private Button NEW_GAME_BTN;
    @FXML
    private Button HIGH_SCORE_BTN;

    javafx.scene.image.Image soundOn = new javafx.scene.image.Image(getClass().getResource("/img/soundOn.png").toExternalForm());
    javafx.scene.image.Image soundOff = new javafx.scene.image.Image(getClass().getResource("/img/soundOff.png").toExternalForm());


    public void openHighScore() throws IOException {
        buttonClicked();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/highscore.fxml"));
        SIDE_PANE.getChildren().setAll(pane);
    }

    public void startNewGame() throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/gameConnector.fxml"));
        SIDE_PANE.getChildren().setAll(pane);
    }

    public void buttonClicked() {
        SoundPlayer.getInstance().buttonClicked();
    }

    public void soundToggle() {
        String value = SoundPlayer.getInstance().themeSongToggle();
        if (value.equals("soundOn")) {
            SOUND_TOGGLE.setImage(soundOn);
        } else {
            SOUND_TOGGLE.setImage(soundOff);
        }
    }

}
