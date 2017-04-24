package Cards.CardsTypes.SecretPower;

import Cards.CardsTypes.MonsterCards;
import Player.Player;
import Cards.DoSkill;

/**
 * Created by Domician on 2017. 04. 11..
 */

/**
 * 500-al megnöveli a támadóerőt, minden táblán levő kártyának
 */
public class AttackUP extends SecretCards implements DoSkill {

    private boolean uniqueUse = false;
    private boolean modeChange = false;  //nem támad

    public AttackUP(String cardName, int level, int attack, int deffend) {
        super(cardName, level, attack, deffend);
    }

    /**
     * @param obj nincs tesztelve -> PLAYER 1 PARAMÉTER!
     */
    @Override
    public void doUnique(Player obj) {

    }

    public MonsterCards doUni(MonsterCards obj){
        obj.setAttackNumb(500);
        return obj;
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
        return "Támadóerő növelő kártya: " +
                "kártya neve: " + cardName  +
                " szuperképesség használható: " + ((!(uniqueUse))? " Igen " : " Nem ") +
                " Támad: "+ ((!modeChange) ? " Védekezik " : " Támad ") +
                '}';
    }
}
