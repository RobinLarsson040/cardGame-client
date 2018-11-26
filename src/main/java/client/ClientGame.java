package client;


import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.GameBoardController;
import controllers.InfoPrinterController;
import dto.GameDto;
import entities.CreatureCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientGame extends Thread {

    public static ClientNetwork clientNetwork;

    public static ClientNetwork getClientNetwork() {
        return clientNetwork;
    }

    static String player;
    int turn;
    int round;
    String playerTurn;
    static int player1Hp;
    static int player2Hp;
    List<CreatureCard> player1CardsOnTable;
    List<CreatureCard> player2CardsOnTable;
    List<CreatureCard> cardsOnHand;
    ObjectMapper objectMapper;
    Scanner scanner;
    static GameDto gameData;
    GameBoardController controller;
    static String messageToScreen;
    static List highScore;
    String name;


    public ClientGame(String name,String address, int port, GameBoardController controller) throws IOException {
        this.clientNetwork = new ClientNetwork();
        System.out.println(address);
        clientNetwork.startConnection(address, port);
        this.controller = controller;
        objectMapper = new ObjectMapper();
        scanner = new Scanner(System.in);
        highScore = new ArrayList();
        clientNetwork.sendMessage(name);
        System.out.println("Name: "+ name);
        receiveMsg.start();
        this.name = name;
    }

    private Thread receiveMsg = new Thread() {
        public void run() {
            while (true) {
                String msgFromServer = null;
                try {
                    msgFromServer = clientNetwork.getIn().readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (msgFromServer.startsWith("PLAYER")) {
                    String[] playerString = msgFromServer.split(":");
                    player = playerString[1];
                    System.out.println("YOU ARE PLAYER: " + player);
                } else if (msgFromServer.startsWith("HIGHSCORE")) {
                    deserializeHighScoreFromServer(msgFromServer);

                } else if (msgFromServer.startsWith("MESSAGE")) {
                    messageToScreen = msgFromServer;
                    controller.updateMessage();
                } else if (msgFromServer.startsWith("ERROR")) {
                    messageToScreen = msgFromServer;
                    controller.updateMessage();
                } else if (msgFromServer.startsWith("GUI:")) {
                    try {
                        deserializeMsgFromServer(msgFromServer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public void sendMsgLoopTemp() {
        while (true) {
            String msgToServer = scanner.nextLine();
            clientNetwork.getOut().println(msgToServer);
        }
    }

    public void deserializeMsgFromServer(String msg) throws IOException {
        String parsedString = msg.replace("GUI:", "");
        GameDto gameDto = objectMapper.readValue(parsedString, GameDto.class);
        gameData = gameDto;
        player2Hp = gameDto.getPlayer2Hp();
        player1Hp = gameDto.getPlayer1Hp();

        controller.update();

    }

    private void deserializeHighScoreFromServer(String highScoreString) {
        highScoreString = highScoreString.replace("HIGHSCORE:", "");
        highScoreString = highScoreString.replace("[", "");
        highScoreString = highScoreString.replace("]", "");
        highScoreString = highScoreString.replace("=", "  ");
        System.out.println(highScoreString);
       /* for (String placement : highScoreString.split(",")) {
            highScore.add(placement);
        }
        System.out.println("HIGH-SCORE CLIENT " + highScore.toString());
        ////ladda highscore som är sparat i "highScore" listan*/
    }

    public static GameDto getDto() {
        return gameData;
    }

    public static String getPlayer() {
        return player;
    }

    public static String getMessageToScreen() {
        return messageToScreen;
    }

    public  static List getHighScore() {return highScore; }


}
