package dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.CreatureCard;
import entities.GameCard;

import java.io.Serializable;
import java.util.List;

public class GameDto implements Serializable {
    int turn;
    int round;
    Boolean playerOneTurn;
    int player1Hp;
    int player2Hp;
    List<CreatureCard> player1CardsOnTable;
    List<CreatureCard> player2CardsOnTable;
    List<GameCard> cardsOnHand;

    @JsonCreator
    public GameDto(@JsonProperty("turn") int turn,
                   @JsonProperty("round") int round,
                   @JsonProperty("playerOneTurn") Boolean playerOneTurn,
                   @JsonProperty("player1Hp") int player1Hp,
                   @JsonProperty("player2Hp") int player2Hp,
                   @JsonProperty("player1CardsOnTable") List<CreatureCard> player1CardsOnTable,
                   @JsonProperty("player2CardsOnTable") List<CreatureCard> player2CardsOnTable,
                   @JsonProperty("cardsOnHand") List<GameCard> cardsOnHand) {
        this.turn = turn;
        this.round = round;
        this.playerOneTurn = playerOneTurn;
        this.player1Hp = player1Hp;
        this.player2Hp = player2Hp;
        this.player1CardsOnTable = player1CardsOnTable;
        this.player2CardsOnTable = player2CardsOnTable;
        this.cardsOnHand = cardsOnHand;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Boolean getPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneTurn(Boolean playerOneTurn) {
        this.playerOneTurn = playerOneTurn;
    }

    public int getPlayer1Hp() {
        return player1Hp;
    }

    public void setPlayer1Hp(int player1Hp) {
        this.player1Hp = player1Hp;
    }

    public int getPlayer2Hp() {
        return player2Hp;
    }

    public void setPlayer2Hp(int player2Hp) {
        this.player2Hp = player2Hp;
    }

    public List<CreatureCard> getPlayer1CardsOnTable() {
        return player1CardsOnTable;
    }

    public void setPlayer1CardsOnTable(List<CreatureCard> player1CardsOnTable) {
        this.player1CardsOnTable = player1CardsOnTable;
    }

    public List<CreatureCard> getPlayer2CardsOnTable() {
        return player2CardsOnTable;
    }

    public void setPlayer2CardsOnTable(List<CreatureCard> player2CardsOnTable) {
        this.player2CardsOnTable = player2CardsOnTable;
    }

    public List<GameCard> getCardsOnHand() {
        return cardsOnHand;
    }

    public void setCardsOnHand(List<GameCard> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }
}
