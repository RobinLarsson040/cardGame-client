package controllers;

import client.ActionClass;
import client.ClientGame;

import dto.GameDto;
import entities.CreatureCard;
import entities.GameCard;
import entities.MagicCard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    public GridPane ENEMY_CARDS_ON_TABLE, PLAYER_CARDS_ON_TABLE, CARD_GRIDPANE;
    @FXML
    public ProgressBar PLAYER_HP_PROGRESSBAR, ENEMY_HP_PROGRESSBAR;
    @FXML
    public Label PLAYER_NAME;
    @FXML
    private BorderPane GAME_BOARD;
    @FXML
    private AnchorPane infoPan, CARDS_ON_TABLE;
    @FXML
    private Label GAME_ROUND;
    @FXML
    public Button ATTACK_ENEMY_BTN;
    @FXML
    public Button END_TURN_BTN;
    @FXML
    private Label CARDS_IN_DECK;

    private String playerName;
    private String ip_Adress;

    private ActionClass action;
    GameDto gameDto;
    List<CreatureCard> playerCards, enemyCards;
    double playerHp, enemyHp;
    private String currentPlayer;
    private boolean playerOneTurn;
    private InfoPrinterController infoPrinterController;


    public void setConnectionParam(String playerName, String ip_Adress) {
        this.playerName = playerName;
        this.ip_Adress = ip_Adress;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        action = ActionClass.getInstance();
        try {
            loadPrintComponent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ATTACK_ENEMY_BTN.setOnAction((event -> {
            try {
                action.attackPlayer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        END_TURN_BTN.setOnAction((event -> {
            try {
                action.endTurn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }

    public void update() {
        try {
            gameDto = ClientGame.getDto();
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        getPlayerAndPlayerTurn();
                        infoPrinterController.nextPlayerTurn(playerOneTurn);
                        assignCards();
                        printCardsOnHand();
                        printCardsOnBoard(playerCards, enemyCards);
                        printPlayerInfo();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        } catch (Exception e) {
        }
    }

    public void updateMessage() {
        try {
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        infoPrinterController.printInfo(ClientGame.getMessageToScreen());
                    } catch (Exception e) {
                        System.out.println("could not print to screen");
                    }
                });
            }).start();
        } catch (Exception e) {
        }
    }


    //    Måste ändra namn till något bra!
    private void assignCards() {

        switch (ClientGame.getPlayer()) {
            case "player1":
                playerHp = ClientGame.getDto().getPlayer1Hp();
                enemyHp = ClientGame.getDto().getPlayer2Hp();
                playerCards = ClientGame.getDto().getPlayer1CardsOnTable();
                enemyCards = ClientGame.getDto().getPlayer2CardsOnTable();
                break;
            case "player2":
                playerHp = ClientGame.getDto().getPlayer2Hp();
                enemyHp = ClientGame.getDto().getPlayer1Hp();
                playerCards = ClientGame.getDto().getPlayer2CardsOnTable();
                enemyCards = ClientGame.getDto().getPlayer1CardsOnTable();
                break;
            default:
                System.out.println("NO PLAYER");
                break;
        }
    }

    private void printCardsOnHand() throws IOException {
        CARD_GRIDPANE.getChildren().clear();
        CARD_GRIDPANE.addRow(0);

        List<GameCard> cardList = ClientGame.getDto().getCardsOnHand();

        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i) instanceof CreatureCard) {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(getClass().getResource("/view/creatureCard.fxml").openStream());
                CreatureCardController controller = loader.getController();
                controller.setValues((CreatureCard) cardList.get(i), i, "hand");
                CARD_GRIDPANE.addColumn(i, pane);

            } else if (cardList.get(i) instanceof MagicCard) {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(getClass().getResource("/view/magicCard.fxml").openStream());
                MagicCardController controller = loader.getController();
                controller.setValues((MagicCard) cardList.get(i), i, ((MagicCard) cardList.get(i)).getMagicType());
                CARD_GRIDPANE.addColumn(i, pane);
            }
        }
    }

    private void printCardsOnBoard(List<CreatureCard> playerCards, List<CreatureCard> enemyCards) throws IOException {
        printCardsOnHand();
        printCardsOnTable(PLAYER_CARDS_ON_TABLE, playerCards, "playerTable");
        printCardsOnTable(ENEMY_CARDS_ON_TABLE, enemyCards, "enemyTable");
    }

    private void printCardsOnTable(GridPane thePane, List<CreatureCard> cards, String table) throws IOException {
        thePane.getChildren().clear();
        thePane.addRow(0);
        if (cards != null) {
            for (int i = 0; i < cards.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(getClass().getResource("/view/creatureCard.fxml").openStream());
                CreatureCardController controller = loader.getController();
                controller.setValues(cards.get(i), i, table);
                thePane.addColumn(i, pane);
            }
        }
    }

    private void printPlayerInfo() {
        PLAYER_HP_PROGRESSBAR.setProgress((playerHp * 0.05));
        PLAYER_NAME.setText(ClientGame.getPlayer());
        ENEMY_HP_PROGRESSBAR.setProgress((enemyHp * 0.05));
        GAME_ROUND.setText(Integer.toString(ClientGame.getDto().getRound()));
        CARDS_IN_DECK.setTextFill(Color.PURPLE);
        CARDS_IN_DECK.setText(Integer.toString(ClientGame.getDto().getCardsLeftInDeck()));
    }

    private void getPlayerAndPlayerTurn() {
        currentPlayer = ClientGame.getPlayer();
        playerOneTurn = ClientGame.getDto().getPlayerOneTurn();

        switch (currentPlayer) {
            case "player1":
                if (!playerOneTurn) {
                    END_TURN_BTN.setDisable(true);
                } else {
                    END_TURN_BTN.setDisable(false);
                }
                break;
            case "player2":
                if (playerOneTurn) {
                    END_TURN_BTN.setDisable(true);
                } else {
                    END_TURN_BTN.setDisable(false);
                }
                break;
        }
    }

    private void loadPrintComponent() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        infoPan = loader.load(getClass().getResource("/view/infoPrinter.fxml").openStream());
        infoPrinterController = loader.getController();
        CARDS_ON_TABLE.getChildren().addAll(infoPan);
    }


}
