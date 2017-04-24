package Player;

import Cards.Cards;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricsko on 2017. 04. 11..
 */

public class Player {
    private String name;   //játékos név
    private List<Cards> inHandCards = new ArrayList<>();   //kezében levő kártyák tárolása
    private Deck deckObject;    //a játékos SAJÁT deckje (ahonnan majd felszedi a kártyát)
    private int health = 4000;      //játékos élet
    private Table table = new Table();   // a játékos SAJÁT asztalja (ahova majd lerakja a kártyát)

    private boolean passTactic = false;
    private boolean passAttack = false;


    public Player(String name, int numberCards) throws IOException {  //mainbe ezeket kérjük be)
        this.name = name;
        this.deckObject = new Deck(numberCards);   //dekladárás után egyből készítünk egy paklit
    }

    public void createDeck() throws IOException {
        deckObject.createDeck();
    }
    /*
        hozzáad egy kártya típusú objektumot az asztalhoz
     */
    public void addCardtoTable(Cards valami) {
        table.putCardToTableMagic(valami);
    }

    /*
        legfelső lapot kihúzza a kártya listából
        ha elfogy a kártya meghivja a lostplayerHP-t
     */

    public void getCardFromDeck() {
        try {
            inHandCards.add(deckObject.getDeck().get(0));
            deckObject.getDeck().remove(0);
        } catch (Exception e) {
            System.out.println(" A kártya kifogyott a pakliból, siess, mert veszítesz 500 hp-t körönként ");
            lostPlayerHP();
        }
    }

    /*
    elkezd fogyni a játékos élete
    ha elfogyott akkor meghivja a gameend és gameresult-ot
     */

    private void lostPlayerHP() {
        if (health - 500 >= 0) {
            health -= 500;
        }else{
            health -=health;
        }
    }

    public void lostPlayerHPByMonster(int value){
        health = health-value;
    }

    /*
     meg lehet nézni hogy áll a játékos élete
     */
    public void checkPlayerHP() {
        if (health <= 0) {
            System.out.println(" Meghalt");
        }
    }

    public boolean isPassedTactic() {
        return passTactic;
    }

    public void doPassTactic() {
        passTactic = true;
    }

    public void setPassAttack(boolean param){
        passAttack = param;
    }

    public boolean isPassAttack(){
        return passAttack;
    }

    public void setPassTactic(boolean param){
        passTactic = param;
    }

    public void doPassAttack(){
        passAttack = true;
    }

    public String getName() {
        return name;
    }

    public List<Cards> getInHandCards() {
        return inHandCards;
    }

    public Deck getDeckObject() {
        return deckObject;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int value) {
        this.health += value;
    }

    @Override
    public String toString() {
        return "Játékos neve: " + name + " , a kártyák a kezedben: " + "%n" + inHandCards +
                " és a kártyáid az asztalon: " + table.toString();
    }

    public Table getTable() {
        return table;
    }
}
