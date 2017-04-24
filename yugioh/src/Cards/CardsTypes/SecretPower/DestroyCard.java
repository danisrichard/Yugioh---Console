package Cards.CardsTypes.SecretPower;

import Player.Player;

/**
 * Created by Ricsko on 2017. 04. 11..
 */

/*
 * Elpusztít egy véletlenszerű kártyát az ellenfél térfeléről (ha van)
 */

public class DestroyCard extends SecretCards {

    private boolean uniqueUse = false;
    private boolean modeChange = false;  //nem támad


    public DestroyCard(String cardName, int level, int attack, int deffend) {
        super(cardName, level, attack, deffend);
    }



    /**
     * @param obj nincs tesztelve -> PLAYER 2 PARAMÉTER
     */

    @Override
    public void doUnique(Player obj) {
        int randomNumb = (int) (Math.random() * obj.getInHandCards().size());
        if (obj.getTable().getMonsterCards().size() > 0 || obj.getTable().getMagicCards().size() > 0) {
            boolean random = Math.random() > 0.5;
            if (random) {
                obj.getTable().getMagicCards().remove(randomNumb);
            } else {
                obj.getTable().getMonsterCards().remove(randomNumb);
            }
        } else {
            System.out.println(" nincs kártya az ellenfél oldalán, megérte? nem hiszem :/ ");
        }
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
        return "Kártyapusztító lap: "+
                "Kártya neve: " + cardName +
                "Erő" + getAttackNumb() + "Védekezőerő " + getDeffendNumb() +
                " szuperképesség használható: " + ((!(uniqueUse))? " Igen " : " Nem ") +
                " Támad: " + ((!modeChange) ? " Védekezik " : " Támad " + '}');
    }
}
