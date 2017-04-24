package Cards.CardsTypes;

import Cards.Cards;

/**
 * Created by Ricsko on 2017. 04. 10..
 */

/**
 * Szörnykártya (alap)
 */

public class MonsterCards extends Cards {
    private int level; //1-11 értéke lehet
    private boolean modeChange = false;  //nem támad
    private int attack;
    private int deffend;
    private boolean cardUse = false;

    public MonsterCards(String cardName, int level, int attack, int deffend) {
        super(cardName);
        this.level = level;
        this.attack = attack;
        this.deffend = deffend;
    }

    public void setAttackNumb(int attack) {
        this.attack += attack;
    }

    public int getDeffendNumb() {
        return deffend;
    }

    public void setDeffendNumb(int deffend) {
        this.deffend += deffend;
    }

    public int getAttackNumb(){
        return attack;
    }

    public boolean isModeChange() {
        return modeChange;
    }

    public void setModeChange() {
       this.modeChange = !modeChange;
    }

    public boolean isCardUse() {
        return cardUse;
    }

    public void setCardUse() {
        cardUse = !cardUse;
    }

    @Override
    public String toString() {
        return "Szörnykártya: " + cardName + " Támadoerő: " + attack +
                " Védekezőerő: " + deffend + " Támad: " + ((!modeChange) ? " Védekezik " : " Támad ");
    }
}
