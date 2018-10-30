package test;

import app.entities.GameCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

   List <GameCard> deck;
   Player player;

    @Mock
    GameCard card;

    @BeforeEach
    void setUp() {
        deck = getDeck(20);
        player = new Player(deck);
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            deck.add(card);
        }
        return deck;
    }

    @Test
    void reduceHp() {
        int hpBefore = player.getHp();
        final int DAMAGE = 5;

        player.reduceHp(DAMAGE);
        int hpAfter = player.getHp();

        assertEquals(hpAfter, hpBefore-DAMAGE);
    }

    @Test
    void reduceHpWithMoreThanPlayerHave() {
        int hpBefore = player.getHp();
        final int OVERKILL = 20;

        player.reduceHp(OVERKILL);
        int hpAfter = player.getHp();

        assertEquals(hpAfter, hpBefore-OVERKILL);
    }

    @Test
    void increaseHp() {
        int hpBefore = player.getHp();
        final int HEAL = 10;

        player.increaseHp(HEAL);
        int hpAfter = player.getHp();

        assertEquals(hpAfter, hpBefore + hpAfter);
    }

    @Test
    void drawCard() {
        deck = getDeck(10);
        Player player = new Player(deck);
        player.drawCard();
        assertEquals(1, player.getCardsOnHand().size());
        assertEquals(9, player.getCardsInDeck().size());

    }


}