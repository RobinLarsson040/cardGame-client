package app;

import app.entities.GameCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.controllers.Game;

import java.util.ArrayList;
import java.util.List;

public class Main{

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }



    public static void main(String[] args) {
//        launch(args);

            List<GameCard> deck = new ArrayList();
            for(int i = 0; i < 20; i++) {
                GameCard card = new GameCard("kort" + (i+1));
                deck.add(card);
            }
        Game game = new Game(deck);
    }
}
