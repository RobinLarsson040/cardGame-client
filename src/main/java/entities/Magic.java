package entities;

import entities.CreatureCard;
import entities.Player;

import java.util.List;
import java.util.Random;

public class Magic {

    Player player;
    CreatureCard creatureCard;

    //omedelbar effekt - öka Player1/2 HP med 2
    public void selfHealPlayer( Player player, int healPoints ) {
        int heal = getRandomPoints( healPoints );
        if (player.getHp()+heal >= player.getMaxHp()){
            player.setHp(20);
        }
        else{
        player.setHp( player.getHp() + heal );

        }
    }

    //omedelbar effekt - ge skada till Player1/2 HP med 2
    public void damageEnemyPlayer( Player player, int attackPoints ) {

        int damage = getRandomPoints( attackPoints );
        if (damage >= player.getHp()){
            player.setHp(0);
        }
        else{
            player.setHp( player.getHp() - damage );
        }
    }

    // omedelbar effek - ge direkt skada till motståndarens kort med 2
    public void damageEnemyCards( List<CreatureCard> cardsOnTable, int attackPoints ) {

        int damage = getRandomPoints( attackPoints );

        cardsOnTable.stream()
            .forEach( card -> {
                int cardHp = card.getHp();
                card.setHp( damage > cardHp ? 0 : cardHp - damage );
            });
    }

    // omedelbar effek - öka sina egna korts HP med 2
    public void healFriendlyCards( List<CreatureCard> cardsOnTable, int healPoints ) {

        int heal = getRandomPoints( healPoints );

        cardsOnTable.stream()
                .forEach( card -> {
                    int maxHp = card.gethpMax();
                    int damagedCreatureCard =  maxHp - card.getHp();
                    card.setHp( heal >= damagedCreatureCard ? maxHp : card.getHp() + heal  );
                });

    }

    // riktad effekt - öka ett korts HP med 2
    public void healOneCard( CreatureCard creatureCard, int healPoints ) {
        int maxHp = creatureCard.gethpMax();

        int heal = getRandomPoints( healPoints );
        int damagedCreatureCard = maxHp - creatureCard.getHp();

        creatureCard.setHp( heal >= damagedCreatureCard ? maxHp : creatureCard.getHp() + heal );
    }

    // riktad effekt - minska ett korts HP med 2
    public void damageOneCard( CreatureCard creatureCard, int attackPoints ) {

        int damage = getRandomPoints( attackPoints );

        if(damage > creatureCard.getHp()){
            creatureCard.setHp(0);

        }
        else {
            creatureCard.setHp(creatureCard.getHp() - damage);

        }
    }

    public int getRandomPoints( int number ) {
        Random r = new Random();
        return r.nextInt( number ) + 1;
    }
}
