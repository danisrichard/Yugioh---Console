package Player;

import Cards.Cards;
import Cards.CardsTypes.MagicCards;
import Cards.CardsTypes.MonsterCards;
import Cards.CardsTypes.SecretPower.AttackUP;
import Cards.CardsTypes.SecretPower.DeffendUP;
import Cards.CardsTypes.SecretPower.DestroyCard;
import Cards.CardsTypes.SecretPower.StoleCard;
import SourceCards.ReadingFile;
import com.sun.org.apache.regexp.internal.RE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ricsko on 2017. 04. 11..
 */
public class Deck {
    private int cardsNumber;
    private List<Cards> deck = new ArrayList<Cards>();

    /**
     * itt adod meg hány darabos legyen a pakli 20 - 40 számérték lehet
     * @param 20-40 között lehet, ha nagyobb akkor 30-ra állitja
     */

    Deck(int cardNumber) throws IOException {
        if (cardNumber > 40 || cardNumber < 20) {
            this.cardsNumber = 30;
        } else {
            this.cardsNumber = cardNumber;
        }
    }

    /**
     * pakli létrehozás
     */

    public void createDeck() throws IOException {
        for (int i = 0; i < cardsNumber; i++) {     //cardsNumber
            int random = (int) (Math.random() * 6 + 1);
            int randomLine = (int) (Math.random() * 15 + 1);
            switch (random) {
                case 1:
                    deck.add(createMonster(randomLine));
                    break;
                case 2:
                    deck.add(createMagic(randomLine));
                    break;
                case 3:
                    deck.add(attackCard(randomLine));
                    break;
                case 4:
                    deck.add(deffendCard(randomLine));
                    break;
                case 5:
                    deck.add(destroyCard(randomLine));
                    break;
                case 6:
                    deck.add(stoleCard(randomLine));
                    break;
                default:
                    System.out.println("valami rossz :)");
            }
        }
        Collections.shuffle(deck);
    }

    @Override
    public String toString() {
        return deck.toString();
    }

    /**
     * és itt jönnének létre a kártyák
     */

    private final File monsterData = new File("./src/SourceCards/MonsterText.txt");
    private final File magicData = new File("./src/SourceCards/MagicText.txt");


    private Cards createMonster(int x) throws IOException {
        ReadingFile.ReadingFile1(monsterData, x);

        return ReadingFile.getMonster();
    }

    private Cards createMagic(int x) throws IOException {
        ReadingFile.ReadingFile1(magicData, x);

        return ReadingFile.getMagic();
    }

    private Cards attackCard(int x) throws IOException {
        ReadingFile.ReadingFile1(monsterData, x);

        return ReadingFile.getAttackUP();
    }

    private Cards deffendCard(int x) throws IOException {
        ReadingFile.ReadingFile1(monsterData, x);

        return ReadingFile.getDeffendUP();
    }

    private Cards destroyCard(int x) throws IOException {
        ReadingFile.ReadingFile1(monsterData, x);

        return ReadingFile.getDestroy();
    }

    private Cards stoleCard(int x) throws IOException {
        ReadingFile.ReadingFile1(monsterData, x);

        return ReadingFile.getStole();

    }

    List<Cards> getDeck() {
        return deck;
    }



}
