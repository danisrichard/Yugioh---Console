import Cards.CardsTypes.MagicCards;
import Cards.CardsTypes.MonsterCards;
import Cards.CardsTypes.SecretPower.*;
import Cards.Enumclasses.MagicPower;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ricsko on 2017. 04. 10..
 * YUGIOH game
 */
public class Main {
    public static void main(String args[]) throws IOException {

        Scanner scan = new Scanner(System.in);

        String input = "";
        char c;
        int numb = 0;

        try {
            System.out.println(" Üdv a Yugioh játékban, a játék kezdete elött adj meg pár adatot");
            System.out.println("A játékosod neve:");
            input = scan.next();
            System.out.println("A kártyák száma amennyit szeretnél húzni:");
            numb = scan.nextInt();
            System.out.println("Kezdődjön is a játék !");
        } catch (Exception e) {
            System.out.println("basszus nem kértem pedig sokat, csak egy nevet meg kártyaszámot, de kezdődjék a játék alapértékekkel");
        }

        Player player1 = new Player(input, numb);
        Player player2 = new Player("SanyiFőGonosz", numb);
        player1.createDeck();
        player2.createDeck();

        /*
         * Játékosok létrehozása , a kártyaszám egyezik mindkettő hogy fair legyen
         */


        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            player1.getCardFromDeck();
            player2.getCardFromDeck();

            System.out.println(" Következő kör ");

            /*
             * TAKTIKAI RÉSZ!!!
             */
            /*
             * -> ha átállítod nem fut le a while ciklus a feltételek miatt :)
             */

            player1.setPassTactic(false);
            player1.setPassAttack(false);
            player2.setPassAttack(false);
            player2.setPassTactic(false);

            while (!player1.isPassedTactic() && !player2.isPassedTactic()) {

                System.out.println("///////////////////");
                System.out.println("Kártyták (T)aktika fázisnak állítás vagy (P)asszolás vagy (A)llás ellenörzése");
                input = scan.next();
                c = Character.toLowerCase(input.charAt(0));

                if (!player1.isPassedTactic() && c == 't') {
                    System.out.println("Kezednben lévő kártyák: " + player1.getInHandCards().toString());
                    System.out.println("Asztalon lévő kártyák: " + player1.getTable().toString());
                    System.out.println("Ellenfél oldalon lévő kártyák: " + player2.getTable().toString());
                    System.out.println(" Mit szerentnél? Mód (v)áltás (támad vagy védekezik) vagy (l)erakni kártyát? ");

                    System.out.println("///////////////////");

                    input = scan.next();
                    c = Character.toLowerCase(input.charAt(0));

                    if (c == 'l') {
                        System.out.println("Melyik kártyád szeretnéd lerakni az asztalra? - válassz:");
                        System.out.println(player1.getInHandCards().toString());
                        System.out.println("Válassz 1-től " + player1.getInHandCards().size() + "-ig");

                        try {
                            numb = scan.nextInt() - 1;

                            player1.addCardtoTable(player1.getInHandCards().get(numb));
                            player1.getInHandCards().remove(numb);
                            System.out.println("Pályán lévő kártyák mostantól: " + player1.getTable().toString());
                        } catch (Exception e) {
                            System.out.println("Hiba - Nincs ilyen érték a kezedben");
                        }

                    } else if (c == 'v') {   // ha a bement 'v' karakter akkor ugrik be ebben a ciklusba
                        System.out.println("Melyik kártyátd állását szeretnéd váltani  - válassz:");
                        System.out.println(player1.getTable().getMonsterCards().toString());
                        System.out.println("Válaasz kártyát 1-től " + player1.getTable().getMonsterCards().size() + " -ig");

                        numb = scan.nextInt() - 1;  //beolvas

                        try {
                            MonsterCards changeMonster = (MonsterCards) player1.getTable().getMonsterCards().get(numb);
                            player1.getTable().getMonsterCards().remove(numb);
                            changeMonster.setModeChange();
                            System.out.println(changeMonster.toString());
                            player1.getTable().putCardtoTableMonster(changeMonster);
                        } catch (Exception e) {
                            System.out.println("Hiba - nincs ilyen érték az asztalon");
                        }
                    }

                } else if (!player1.isPassedTactic() && c == 'p') {
                    player1.doPassTactic();

                } else if (!player1.isPassedTactic() && c == 'a') {
                    System.out.println("Kezednben lévő kártyák: " + player1.getInHandCards().toString());
                    System.out.println("Asztalon lévő kártyák: " + player1.getTable().toString());
                    System.out.println("Ellenfél oldalon lévő kártyák: " + player2.getTable().toString());
                    System.out.println("A te életerőd: " + player1.getHealth() + " Az ellenfél életereje: " + player2.getHealth());
                }

                /*
                 *
                 * Az ellenfelet szabályzó kódrész (kezdetleges)
                 *
                 */

                if (!player2.isPassedTactic() && player1.isPassedTactic()) {

                /*
                    Kiválaszt egy random kártyát a kezésből és lerakja a Table-re MINDKETTŐ TIPUS
                 */

                    if (player2.getInHandCards().size() > 0) {
                        int random = (int) (Math.random() * player2.getInHandCards().size());
                        if (player2.getInHandCards().get(random) instanceof MonsterCards) {
                            player2.getTable().setMonsterCards((MonsterCards) player2.getInHandCards().get(random));
                            player2.getInHandCards().remove(random);
                        } else {
                            player2.getTable().setMagicCards(player2.getInHandCards().get(random));
                            player2.getInHandCards().remove(random);
                        }
                    }

                /*
                    Taktikaki fázis állás vizsgális és állítja ha if-ben benne vna MONSTERCARD

                 */

                    for (int i = 0; i < player2.getTable().getMonsterCards().size(); i++) {
                        MonsterCards monsteCardChecker = (MonsterCards) player2.getTable().getMonsterCards().get(i);
                        player2.getTable().getMonsterCards().remove(i);
                        if (monsteCardChecker.getDeffendNumb() > monsteCardChecker.getAttackNumb() && player1.getTable().getMonsterCards().size() != 0) {
                            monsteCardChecker.setModeChange();
                        }
                        player2.getTable().getMonsterCards().add(monsteCardChecker);
                    }
                    System.out.println("Player 2 állása a pályán jelenleg: " + player2.getTable().toString() + " és a kezében lévő kártyák száma: " + player2.getInHandCards().size());
                    player2.doPassTactic();
                }
            }

            if (player2.isPassedTactic() && player1.isPassedTactic()) {
                player1.setPassAttack(false);
                player1.setPassAttack(false);
            }

            /*
             * TÁMADÓ FÁZIS
             */

            while (!player1.isPassAttack() && !player2.isPassAttack()) {

                int numb1;  //player1 ellen választott szám
                int numb2; //player2 ellen választott szám

                System.out.println("(T)ámadod az ellenfelet vagy (B)ekapcsolod a szörnykártya képességét vagy (M)ágikus kártya bekapcs vagy (P)asszolsz, jelenleg (H)elyzet lekérdezése?");
                input = scan.next();
                c = Character.toLowerCase(input.charAt(0));

                /*
                 * ha támadsz akkor ez fut le
                 */

                ArrayList<MonsterCards> monsterChecker = new ArrayList<>();  //le kell menteni hogy melyik kártyával játszottál mér, h ne lehessen újra meg újra meghívni

                if (!player1.isPassAttack() && (c == 't') && (player2.getTable().getMonsterCards().size() != 0)) {
                    System.out.println("Válaszd ki a lapot az ellenfél oldaláról akit megszeretnél támadni: ");
                    System.out.println("1-től " + player2.getTable().getMonsterCards().size() + " -ig");

                    numb2 = scan.nextInt();
                    numb2 = numb2 - 1;

                    if (numb2 < player2.getTable().getMonsterCards().size() && numb2 >= 0) {
                        System.out.println("Válaszd ki a kártyáid közül melyikkel szeretnél támadni");
                        System.out.println("1től " + player1.getTable().getMonsterCards().size() + " -ig");

                        /*
                         * vizsgáljuk hogy szörnykártya
                         */

                        numb1 = scan.nextInt() - 1;  // indexelés miatt -1
                        try {
                            if (numb1 < player1.getTable().getMonsterCards().size() && numb1 >= 0) { //&& player1.getTable().getMonsterCards().get(numb1) instanceof MonsterCards) {
                                MonsterCards player1Card = (MonsterCards) player1.getTable().getMonsterCards().get(numb1);
                                MonsterCards player2Card = (MonsterCards) player2.getTable().getMonsterCards().get(numb2);

                                for (int i = 0; i < monsterChecker.size(); i++) {
                                    if (monsterChecker.get(i).equals(player1Card)) {
                                        System.out.println("Használd már ezt a kártyát");
                                        break;
                                    } else {
                                        monsterChecker.add(player1Card);
                                    }
                                }

                                /*
                                 * vizsgáljuk hogy a mi kártyánk támadó módban van-e
                                 */

                                if (player1Card.isModeChange()) {
                            /*
                                ha az övé támadó módban van akkor a támadó erőket nézzük össze  (kisebb veszít)
                             */

                                        if (player1Card.getAttackNumb() > player2Card.getAttackNumb() && !player2Card.isModeChange()) {
                                            player2.getTable().getMonsterCards().remove(numb2);
                                            player2Card = null;
                                            player1Card = null;
                                            System.out.println(" Player 1 kártya győzött! 1 ");
                                        } else if (player1Card.getAttackNumb() < player2Card.getAttackNumb() && !player2Card.isModeChange()) {
                                            player1.getTable().getMonsterCards().remove(numb1);
                                            player1Card = null;
                                            player2Card = null;
                                            System.out.println(" Player 2 kártya győzőtt! 1 ");
                                        }

                            /*
                                ha az övé védekező módban akkor a mi támadó az ő védekező erejét nézzük össze (a kisebb veszít)
                             */
                                        if (!(player2Card == null)) {
                                            if (player1Card.getAttackNumb() > player2Card.getDeffendNumb() && player2Card.isModeChange()) {
                                                player2.getTable().getMonsterCards().remove(numb2);
                                                System.out.println(" Player 1 kártya győzőtt! ");
                                            } else {
                                                player1.getTable().getMonsterCards().remove(numb1);
                                                System.out.println(" Player 2 kártya győzőtt! ");
                                            }
                                        }

                                } else {
                                    System.out.println(" ez a kártya védekezik, nem támadhatsz, állitsd át támadóban a taktikai részben");
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(" nincs ilyen sorszámú kártya az asztalon lent ");
                        } catch (Exception e) {
                            System.out.println(" Valami nagyobb galiba történt, próbáld meg újra");
                        }
                    }

                } else if (!player1.isPassAttack() && (c == 't') && (player2.getTable().getMonsterCards().size() == 0)) {
                    System.out.println("Player 2 élete : " + player2.getHealth());
                    System.out.println(" ellenfélnél nincsen kártya, válassz egyet amivel a ellenfelet megtámadod: ");
                    System.out.println(" Ezek kártyák közül választhatsz " + player1.getTable().getMonsterCards().size());
                    numb1 = scan.nextInt() - 1;

                    if (numb1 < player1.getTable().getMonsterCards().size() && numb1 >= 0 && player1.getTable().getMonsterCards().get(numb1) instanceof MonsterCards) {
                        MonsterCards player1Card = (MonsterCards) player1.getTable().getMonsterCards().get(numb1);

                        System.out.println(" Az ellenfél életereje mostantól: " + (player2.getHealth() - player1Card.getAttackNumb()) + "-ra/re csökkent");
                        player2.lostPlayerHPByMonster(player1Card.getAttackNumb());
                    }
                } else if (!player1.isPassAttack() && c == 'm') {
                    System.out.println(" Válaszd ki melyik mágikus kártyát akarod használni: ");
                    System.out.println("1-től " + player1.getTable().getMagicCards().size() + " -ig");

                    numb1 = scan.nextInt();

                    try {
                        MagicCards magichelp = (MagicCards) player1.getTable().getMagicCards().get(numb1);
                        if (magichelp.getPower() == MagicPower.ATTACKUP) {
                            int random = (int) (Math.random() * player1.getTable().getMonsterCards().size());
                            MonsterCards monsterHelper = (MonsterCards) player1.getTable().getMonsterCards().get(random);
                            player1.getTable().getMonsterCards().remove(random);
                            monsterHelper.setAttackNumb(magichelp.getChange());
                            player1.getTable().getMonsterCards().add(monsterHelper);
                        }

                        if (magichelp.getPower() == MagicPower.DEFFENDUP) {
                            int random = (int) (Math.random() * player1.getTable().getMonsterCards().size());
                            MonsterCards monsterHelper = (MonsterCards) player1.getTable().getMonsterCards().get(random);
                            player1.getTable().getMonsterCards().remove(random);
                            monsterHelper.setDeffendNumb(magichelp.getChange());
                            player1.getTable().getMonsterCards().add(monsterHelper);
                        }

                        if (magichelp.getPower() == MagicPower.HEALTHUP) {
                            player1.setHealth(magichelp.getChange());
                            System.out.println(" Megnövelte a hősöd életét 500 hp-val");
                        }

                        player1.getTable().getMagicCards().remove(numb1);
                    } catch (Exception e) {
                        System.out.println(" Nincs ilyen kártya az asztalon, próbáld újra");
                    }
                }

                /*
                 A szörny kártyták speciális képességét aktiváló rész
                 */

                else if (!player1.isPassAttack() && c == 'b') {
                    System.out.println(" Válaszd ki hogy a szörny kártyáid közül melyiket szeretnéd használni:");
                    System.out.println("1-től " + player1.getTable().getMonsterCards().size() + " -ig választhatsz");

                    numb = scan.nextInt() - 1;

                    if (numb < player1.getTable().getMonsterCards().size() && numb >= 0 && player1.getTable().getMonsterCards().get(numb) instanceof SecretCards) {
                        SecretCards secretHelper = (SecretCards) player1.getTable().getMonsterCards().get(numb);
                        if (!secretHelper.isUniqueUse()) {   //megvizsgálja, hogy elhasználta-e már a képességét, ha nem,belép a ciklusba
                            if (player1.getTable().getMonsterCards().get(numb) instanceof AttackUP) {
                                for (int i = 0; i < player1.getTable().getMonsterCards().size(); i++) {
                                    MonsterCards helpMonster = (MonsterCards) player1.getTable().getMonsterCards().get(i);
                                    player1.getTable().getMonsterCards().remove(i);
                                    helpMonster.setAttackNumb(500);
                                    System.out.println(" A kártya erőket megnöveltük 500-al :) ");
                                    player1.getTable().getMonsterCards().add(helpMonster);
                                }
                            }

                            if (player1.getTable().getMonsterCards().get(numb) instanceof DestroyCard) {
                                int random = (int) (Math.random() * player2.getInHandCards().size());
                                player2.getTable().getMonsterCards().remove(random);
                                System.out.println(" Elpusztított egy véletlen kártyát az ellenfél kezéből");
                            }

                            if (player1.getTable().getMonsterCards().get(numb) instanceof StoleCard) {
                                System.out.println(" Elvettél egy random kártyát az ellenfél kezéből: ");
                                int random = (int) (Math.random() * player2.getInHandCards().size());
                                try {
                                    player1.getInHandCards().add(player2.getInHandCards().get(random));
                                    System.out.println("Ezt kaptad meg: " + player1.getInHandCards().get(random).toString());
                                } catch (Exception e) {
                                    System.out.println(" Nincsen a kezében kártya, elhasználtad attól függetlenül");
                                }
                            }

                            if (player1.getTable().getMonsterCards().get(numb) instanceof DeffendUP) {
                                for (int i = 0; i < player1.getTable().getMonsterCards().size(); i++) {
                                    MonsterCards helpMonster = (MonsterCards) player1.getTable().getMonsterCards().get(i);
                                    player1.getTable().getMonsterCards().remove(i);
                                    helpMonster.setDeffendNumb(500);
                                    System.out.println(" A kártya erőket megnöveltük 500-al :) ");
                                    player1.getTable().getMonsterCards().add(helpMonster);
                                }
                            }
                            player1.getTable().getMonsterCards().remove(numb);
                            secretHelper.setUniqueUse();  //átállít a TRUE-ra, az értéket, ezáltal elhasználta a képességét, többször nem hazsnálható
                            player1.getTable().getMonsterCards().add(secretHelper);
                        } else {
                            System.out.println("Ennek a kártyának már nins szuperereje");
                        }
                    }

                } else if (!player1.isPassAttack() && c == 'p') {
                    player1.doPassAttack();
                    if (monsterChecker.isEmpty()) {
                        for (int i = 0; i < monsterChecker.size(); i++) {
                            monsterChecker.remove(i);
                        }
                    }
                } else if (!player1.isPassAttack() && c == 'h') {
                    System.out.println("Kezednben lévő kártyák: " + player1.getInHandCards().toString());
                    System.out.println("Asztalon lévő kártyák: " + player1.getTable().toString());
                    System.out.println("Ellenfél oldalon lévő kártyák: " + player2.getTable().toString());
                    System.out.println("A te életerőd: " + player1.getHealth() + " Az ellenfél életereje: " + player2.getHealth());
                }

            /*
               player 2 SecretCard képességek kijátszása
            */

                if (!player2.isPassAttack() && player1.isPassAttack()) {
                    for (int i = 0; i < player2.getTable().getMonsterCards().size(); i++) {
                        if (player2.getTable().getMonsterCards().get(i) instanceof SecretCards) {

                            boolean randomForSkill = Math.random() > 0.5;
                            if (randomForSkill) {
                            SecretCards player2MonsterSecret = (SecretCards) player2.getTable().getMonsterCards().get(i);
                            player2.getTable().getMonsterCards().remove(i);

                            if (player2.getTable().getMonsterCards().get(numb) instanceof AttackUP) {
                                for (int x = 0; x < player1.getTable().getMonsterCards().size(); x++) {
                                    MonsterCards helpMonster = (MonsterCards) player2.getTable().getMonsterCards().get(x);
                                    player2.getTable().getMonsterCards().remove(x);
                                    helpMonster.setAttackNumb(500);
                                    System.out.println(" A kártya erőket megnöveltük 500-al , az ellenfél oldalán ");
                                    player2.getTable().getMonsterCards().add(helpMonster);
                                }
                            }

                            if (player2.getTable().getMonsterCards().get(numb) instanceof DestroyCard) {
                                int random = (int) (Math.random() * player1.getInHandCards().size()-1);
                                player1.getTable().getMonsterCards().remove(random);
                                System.out.println(" Elpusztított egy véletlen kártyát a player1 kezéből");
                            }

                            if (player2.getTable().getMonsterCards().get(numb) instanceof StoleCard) {
                                System.out.println(" Elvettél egy random kártyát az ellenfél kezéből: ");
                                int random = (int) (Math.random() * player1.getInHandCards().size());
                                try {
                                    player2.getInHandCards().add(player2.getInHandCards().get(random));
                                    System.out.println("Ezt kaptad meg: " + player2.getInHandCards().get(random).toString());
                                } catch (Exception e) {
                                    System.out.println(" Nincsen a kezében kártya, elhasználta attól függetlenül az ellenfél");
                                }
                            }

                            if (player2.getTable().getMonsterCards().get(numb) instanceof DeffendUP) {
                                for (int x = 0; x < player2.getTable().getMonsterCards().size(); x++) {
                                    MonsterCards helpMonster = (MonsterCards) player2.getTable().getMonsterCards().get(x);
                                    player2.getTable().getMonsterCards().remove(x);
                                    helpMonster.setDeffendNumb(500);
                                    System.out.println(" A kártya erőket megnöveltük 500-al az ellenfélnél ");
                                    player2.getTable().getMonsterCards().add(helpMonster);
                                }
                            }
                            player2MonsterSecret.setUniqueUse();  //átállít a TRUE-ra, az értéket, ezáltal elhasználta a képességét, többször nem hazsnálható
                            player2.getTable().getMonsterCards().add(player2MonsterSecret);

                            } else {
                                System.out.println("Ennek a kártyának már nins szuperereje");
                            }
                        }

                    }

                /*
                    player 2 támadás képességek kijátszása
                 */

                    for (int i = 0; i < player2.getTable().getMonsterCards().size(); i++) {
                        MonsterCards monsterAttack = (MonsterCards) player2.getTable().getMonsterCards().get(i);
                        if (monsterAttack.isModeChange() && player1.getTable().getMonsterCards().size() > 0) {
                            int random = (int) (Math.random() * player1.getTable().getMonsterCards().size());
                            MonsterCards monsterPlayer = (MonsterCards) player1.getTable().getMonsterCards().get(random);

                            if (monsterAttack.isModeChange() && !monsterAttack.isModeChange()) {
                            /*
                                ha az övé támadó módban van akkor a támadó erőket nézzük össze  (kisebb veszít)
                             */

                                if (monsterAttack.getAttackNumb() > monsterPlayer.getAttackNumb() && !monsterPlayer.isModeChange()) {
                                    player2.getTable().getMonsterCards().remove(i);
                                    monsterAttack = null;
                                    monsterPlayer= null;
                                    System.out.println(" Player 1 kártya győzött! 1 ");
                                } else if (monsterAttack.getAttackNumb() < monsterPlayer.getAttackNumb() && !monsterPlayer.isModeChange()) {
                                    player1.getTable().getMonsterCards().remove(random);
                                    monsterAttack = null;
                                    monsterPlayer= null;
                                    System.out.println(" Player 2 kártya győzőtt! 1 ");
                                }

                            /*
                                ha az övé védekező módban akkor a mi támadó az ő védekező erejét nézzük össze (a kisebb veszít)
                             */
                                if (!(monsterPlayer == null)) {
                                    if (monsterAttack.getAttackNumb() > monsterPlayer.getDeffendNumb() && monsterPlayer.isModeChange()) {
                                        player1.getTable().getMonsterCards().remove(random);
                                        System.out.println(" Player 1 kártya győzőtt! ");
                                    } else {
                                        player2.getTable().getMonsterCards().remove(i);
                                        System.out.println(" Player 2 kártya győzőtt! ");
                                    }
                                }

                            }
                        }

                        if (!monsterAttack.isModeChange() && player1.getTable().getMonsterCards().size() == 0) {
                            player1.lostPlayerHPByMonster(monsterAttack.getAttackNumb());
                            System.out.println(" Megtámadott téged");
                        }
                    }
                    player2.doPassAttack();
                }
            }

            if (player2.isPassAttack() && player1.isPassAttack()) {
                player1.checkPlayerHP();
                player2.checkPlayerHP();
            }
        }

        /*
            Eredmény kiiratása.
         */

        System.out.println(" Játéknak vége van: ");
        if (player1.getHealth() <= 0 && player2.getHealth() > 0)

        {
            System.out.println(player2.getName() + " nyerte a játékot");
        } else

        {
            System.out.println(player1.getName() + " nyerte a játékot");
        }
    }
}
