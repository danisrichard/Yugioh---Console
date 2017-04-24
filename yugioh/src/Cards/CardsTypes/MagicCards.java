package Cards.CardsTypes;

import Cards.Cards;
import Cards.Enumclasses.MagicPower;

/**
 * Created by Ricsko on 2017. 04. 10..
 */

/**
 * mágikus kárta létrehozás
 */

public class MagicCards extends Cards {
    private MagicPower power = null;
    private final int change = 500;

    public MagicCards(String name) {
        super(name);

        int powerNumb = (int) (Math.random()*3+1);

        switch (powerNumb){
            case 1: power = MagicPower.ATTACKUP;
                    break;
            case 2 : power = MagicPower.DEFFENDUP;
                    break;
            case 3: power = MagicPower.HEALTHUP;
        }
    }

    public int getChange() {
        return change;
    }

    public MagicPower getPower() {
        return power;
    }

    public void setPower(MagicPower power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Magiccard" + power + " és erre tudja felhasználni: " + getPower();
    }
}
