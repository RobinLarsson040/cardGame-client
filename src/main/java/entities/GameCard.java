package entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = CreatureCard.class, name = "creaturecard"),
        @JsonSubTypes.Type(value = MagicCard.class, name = "magiccard")}
)
public interface GameCard {

    public String getName();

    public int getAttackPoints();
}
