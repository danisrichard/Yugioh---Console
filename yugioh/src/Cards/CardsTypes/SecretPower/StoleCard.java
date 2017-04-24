package Cards.CardsTypes.SecretPower;

import Cards.Cards;
import Player.Player;
import Cards.DoSkill;

/**
 * Created by Domician on 2017. 04. 11..
 */

/**
 * Elvesz 1 kártyát az ellenfél kezéből (ha van benne) és odaadja a neked (ez onnantól a te kártyád)
 */
public class StoleCard extends SecretCards implements DoSkill {

    private boolean uniqueUse = false;
    private boolean modeChange = false;  //nem támad

    public StoleCard(String cardName, int level, int attack, int deffend) {
        super(cardName, level, attack, deffend);
    }

    /**
     * @param obj nincs tesztelve -> PLAYER 2 paraméter kell
     */
    @Override
    public void doUnique(Player obj) {
        // ennek itt nincs értelme
    }

    public Cards doUni(Player obj) {
        int random = (int) (Math.random() * obj.getInHandCards().size());
        Cards cardObject = null;
        if (obj.getInHandCards().size() > 0) {
            cardObject = obj.getInHandCards().get(random);
        } else {
            System.out.println(" Nincs kártya a kezében :( ");
        }
        return cardObject;
    }

    public boolean isModeChange() {
        return modeChange;
    }

    public void setModeChange() {
        this.modeChange = !modeChange;
    }

    public void setUniqueUse() {
        this.uniqueUse = true;
    }

    public boolean isUniqueUse() {
        return uniqueUse;
    }

    @Override
    public String toString() {
        return "Kártyalopó lap: " +
                "Kártya neve: " + cardName +
                "Erő" + getAttackNumb() + "Védekezőerő " + getDeffendNumb() +
                " szuperképesség használható: " + ((!(uniqueUse)) ? " Igen " : " Nem ") +
                " Támad: " + ((!modeChange) ? " Védekezik " : " Támad " + '}');
    }
}
