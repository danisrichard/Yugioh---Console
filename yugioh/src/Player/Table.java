package Player;

import Cards.Cards;
import Cards.CardsTypes.MagicCards;
import Cards.CardsTypes.MonsterCards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricsko on 2017. 04. 10..
 */
public class Table {
    private List<Cards> monsterCards = new ArrayList<>();  //szörnytipusú lista
    private List<Cards> magicCards = new ArrayList<>();    //mágikustipus kártya

    /*
    listsize = kártya lista maximális mérete
    5 = max ennyi kártyát lehet rakni, nem lehet többet
     */
    private final static int listSize = 5;

    Table() {
    }

    public void putCardtoTableMonster(Cards monsterObject) {
        if (monsterCards.size() < listSize && !(monsterObject instanceof MagicCards)) {
            monsterCards.add(monsterObject);
        } else {
            System.out.println("Your deck is full, wtf want u?");
        }
    }

    public void putCardToTableMagic(Cards magicObjects) {
        if (magicCards.size() < listSize && magicObjects instanceof MagicCards) {
            magicCards.add(magicObjects);
        } else {
            putCardtoTableMonster(magicObjects);
        }
    }

    public List<Cards> getMonsterCards() {
        return monsterCards;
    }

    public void setMonsterCards(MonsterCards monsterCard) {
        monsterCards.add(monsterCard);
    }

    public List<Cards> getMagicCards() {
        return magicCards;
    }

    public static int getListSize() {
        return listSize;
    }

    public void setMagicCards(Cards magicCards) {
        this.magicCards.add(magicCards);
    }

    @Override
    public String toString() {
        return
                "Szörnykártyták: " + monsterCards +
                        " Mágikus kártyák: "  + magicCards;
    }


}