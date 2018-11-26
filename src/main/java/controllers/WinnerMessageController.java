package controllers;

import client.ClientGame;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.util.List;

public class WinnerMessageController {

    @FXML
    private GridPane HIGH_SCORE_PANE;
    @FXML
    private Label PLAYER_NAME;
    @FXML
    private List highScore;


    public void setParams(String name) {
        SoundPlayer.getInstance().playTada();
        highScore = ClientGame.getHighScore();
        System.out.println(highScore.size());
        setGrid();
        PLAYER_NAME.setText(name);
    }

    public void setGrid() {
        for (int i = 0; i < highScore.size() ; i++) {
            Label text = new Label();
            text.setText(highScore.get(i).toString());
            HIGH_SCORE_PANE.addRow(i,text);

        }
    }
}
