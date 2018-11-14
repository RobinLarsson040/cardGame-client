package client;

import client.component.gameBoard.GameBoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain extends Application {

    static GameBoardController controller = new GameBoardController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader;
        Parent parent;
        loader = new FXMLLoader(getClass().getResource("resources/gameboard.fxml"));
        loader.setController(controller);
        parent = loader.load();
        primaryStage.setTitle("CARD GAME");
        primaryStage.setScene(new Scene(parent, 1200, 700));
        primaryStage.show();

    }


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                ClientGame clientGame = new ClientGame("localhost", 6666, controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        launch(args);
    }
    }

