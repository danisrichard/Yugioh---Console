package Cards.CardsTypes.SecretPower;

import Cards.Cards;
import Cards.CardsTypes.MonsterCards;
import Cards.*;
import Player.Player;

/**
 * Created by Domician on 2017. 04. 20..
 */
public abstract class SecretCards extends MonsterCards implements DoSkill{

    private boolean uniqueUse = false;

    public SecretCards(String cardName, int level, int attack, int deffend) {
        super(cardName, level, attack, deffend);
    }

    @Override
    public void doUnique(Player obj){
    }

    public Cards doUni(Player obj){
        return null;
    }

    public void setUniqueUse() {
        this.uniqueUse = true;
    }

    public boolean isUniqueUse() {
        return uniqueUse;
    }

    @Override
    public String toString() {
        return "Plusz képességű szörnyek " + uniqueUse;
    }
}
