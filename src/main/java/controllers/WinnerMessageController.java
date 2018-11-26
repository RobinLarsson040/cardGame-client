package controllers;

import client.ClientGame;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;

public class WinnerMessageController {

    @FXML
    private GridPane HIGH_SCORE_PANE;
    @FXML
    private Text PLAYER_NAME;
    @FXML
    private List highScore;


    public void setParams(String name) {
        highScore = ClientGame.getHighScore();
        setGrid();
    }

    public void setGrid() {
        for (int i = 0; i < highScore.size() ; i++) {
            Text text = new Text();
            text.setText(highScore.get(i).toString());
            HIGH_SCORE_PANE.add(text,0,i);
        }
    }
}
