package Cards.CardsTypes.SecretPower;

import Cards.CardsTypes.MonsterCards;
import Player.Player;
import Cards.DoSkill;

/**
 * Created by Domician on 2017. 04. 11..
 */

/*
 * 500-al megnöveli a védekezőerőt, minden táblán levő kártyának, és amit késöbb rakok le annak is
 */

public class DeffendUP extends SecretCards implements DoSkill {

    private boolean uniqueUse = false;
    private boolean modeChange = false;  //nem támad

    public DeffendUP(String cardName, int level, int attack, int deffend) {
        super(cardName, level, attack, deffend);
    }

    /**
     * @param obj nincs tesztelve -> PLAYER 1 PARAMÉTER!
     */
    @Override
    public void doUnique(Player obj) {
        for (int i = 0; i < obj.getTable().getMonsterCards().size(); i++) {
            MonsterCards helper = (MonsterCards) obj.getTable().getMonsterCards().get(i);
            obj.getTable().getMonsterCards().remove(i);
            helper.setDeffendNumb(500);
            obj.getTable().getMonsterCards().add(helper);
        }
    }

    public boolean isModeChange() {
        return modeChange;
    }

    public void setModeChange() {
        modeChange = true;
    }

    public void setUniqueUse() {
        this.uniqueUse = !modeChange;
    }

    public boolean isUniqueUse() {
        return uniqueUse;
    }

    @Override
    public String toString() {
        return "Védekezést növelő kártya {" +
                "Kártya neve: " + cardName +
                "Erő" + getAttackNumb() + "Védekezőerő " + getDeffendNumb() +
                " szuperképesség használható: " + ((!(uniqueUse))? " Igen " : " Nem ") +
                " Támad: " + ((!modeChange) ? " Védekezik " : " Támad " + '}');
    }
}