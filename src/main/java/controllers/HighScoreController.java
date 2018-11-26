package controllers;

import entities.HighScore;
import entities.Player;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HighScoreController extends Application implements Initializable {
    @FXML
    private TableView highScoreTable;
    @FXML
    private TableColumn<HighScore, String> highScoreName;
    @FXML
    private TableColumn<HighScore, String> highScorePoints;
    @FXML
    private TableColumn<HighScore, String> highScorePosition;

    public void convertHighScoreList(){

    }

//    public static void main(String[] args) {
//        HighScoreController h = new HighScoreController();
//        h.printHighScore(h.getHighscoreList());
//    }

    public List<HighScore> getHighScoreList(){
        List<HighScore> list = new ArrayList<>();

        List<Pair> highScore = new ArrayList<>();
        Pair<String, Integer> player;

        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";

            String myUrl = "jdbc:mysql://sql7.freemysqlhosting.net/sql7265239";

            Class.forName(myDriver);

            Connection conn = DriverManager.getConnection(myUrl, "sql7265239", "cmhKZhQUGY");

            String query = "select * FROM highScore order BY  Points desc LIMIT 10";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String name = rs.getString("Name");
                int points = rs.getInt("Points");
                player = new Pair<>(name, points);
                highScore.add(player);
            }

            st.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        System.out.println("Plc: Namn:   Poäng:");
        for (int i = 0; i <highScore.size() ; i++) {
            list.add(new HighScore(Integer.toString(i+1), highScore.get(i).getKey().toString(), highScore.get(i).getValue().toString()));
            System.out.println((i+1) + "     "+highScore.get(i).getKey() + "   " + highScore.get(i).getValue());
        }
        return list;
    }

    public void printHighScore(List<HighScore> highScoreList) {

        List<HighScore> highScoreList1 = highScoreList;
        highScoreName = new TableColumn("Name: ");
        highScorePosition = new TableColumn("");
        highScorePoints = new TableColumn("Points: ");
        highScoreTable.getColumns().addAll(highScorePosition, highScoreName, highScorePoints);
        //ObservableList<List<StringProperty>> data = FXCollections.observableArrayList();
        final ObservableList<HighScore>data2 = FXCollections.observableArrayList();
        for (int i = 0; i < highScoreList1.size() ; i++) {
//            List<StringProperty> newRow = new ArrayList<>();
//            newRow.add(0, new SimpleStringProperty(highScoreList1.get(i).getPosition()));
//            newRow.add(1, new SimpleStringProperty(highScoreList1.get(i).getName()));
//            newRow.add(2, new SimpleStringProperty(highScoreList1.get(i).getPoints()));
//            System.out.println(newRow);
            HighScore h = new HighScore(highScoreList1.get(i).getPosition(),highScoreList1.get(i).getName(), highScoreList1.get(i).getPoints());
            data2.add(h);
            //data.add(newRow);
        }


        highScorePoints.setCellValueFactory(new PropertyValueFactory<HighScore, String>("points"));
        highScorePosition.setCellValueFactory(new PropertyValueFactory<HighScore, String>("position"));
        highScoreName.setCellValueFactory(new PropertyValueFactory<HighScore, String>("name"));
        highScoreTable.setItems(data2);
        //highScoreTable.getItems().addAll(data);



        //highScorePosition


//        for (int i = 0; i < highScoreList1.size(); i++) {
//            HighScore h = new HighScore(highScoreList1.get(i).getPosition(),highScoreList1.get(i).getName(), highScoreList1.get(i).getPoints());
//            highScoreTable.getItems().add(h);
//        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        printHighScore(getHighScoreList());
    }
}
