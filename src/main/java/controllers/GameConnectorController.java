package controllers;

import client.ClientGame;
import entities.MagicCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameConnectorController {
    public TextField PLAYER_NAME_INPUT;
    public TextField IP_INPUT;
    public Button CONNECT_TO_GAME_BTN;
    public AnchorPane thPane;
    GameBoardController controller;
    private String ipAddress;
    private String playerName;

    ClientGame clientGame;
    public void startNewGame(ActionEvent event) throws IOException {
        if (IP_INPUT.getText().length() < 6) {
            ipAddress = "localhost";
        }else {
            ipAddress = IP_INPUT.getText();
        }
        playerName = PLAYER_NAME_INPUT.getText();
        SoundPlayer.getInstance().buttonClicked();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        BorderPane pane = loader.load(getClass().getResource("/view/gameboard.fxml").openStream());
        controller = loader.getController();
        openConnectsion();
        controller.setConnectionParam(playerName, ipAddress);
        stage.setScene(new Scene(pane, 1200, 700 ));
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private void openConnectsion() {
        Thread thread = new Thread(() -> {
            try {
                ClientGame clientGame = new ClientGame("Robin","localhost", 6666, controller);
//                ClientGame clientGame = new ClientGame("10.155.90.109", 6666, controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
