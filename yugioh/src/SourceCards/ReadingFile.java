package SourceCards;


import Cards.Cards;
import Cards.CardsTypes.MagicCards;
import Cards.CardsTypes.MonsterCards;
import Cards.CardsTypes.SecretPower.AttackUP;
import Cards.CardsTypes.SecretPower.DeffendUP;
import Cards.CardsTypes.SecretPower.DestroyCard;
import Cards.CardsTypes.SecretPower.StoleCard;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ricsko on 2017. 04. 12..
 */
public class ReadingFile{
    private static ArrayList names = new ArrayList<>();  //beolvasott fájlok tárolása
   /**
    * két forrás ahonan érkezik az input:
    * ./src/SourceCards/MonsterText.txt
    * ./src/SourceCards/MagicText.txt
    */

   /*
    megkapja a forrás fájlt és a sor számot (a Deck osztálytól)
    a megfelelő sort kiválasztja (1)
    tab mentén eltöri azokat (2) és tömblistában csomagoljuk, a könyebb használhatóság miatt
    */
    public static void ReadingFile1(File file, int x) throws IOException {
        String line = Files.readAllLines(Paths.get(String.valueOf(file))).get(x);  //1
        names = new ArrayList<>(Arrays.asList(line.split("\t")));  //2
    }

    /*
      kártya osztályokat adunk vissza
      parsolni kell, mert String az tömblista
     */

    public static Cards getMonster(){
        return new MonsterCards((String) names.get(0),Integer.parseInt((String) names.get(1)),Integer.parseInt((String) names.get(2)),Integer.parseInt((String) names.get(3)));
    }

    public static Cards getAttackUP(){
        return new AttackUP((String) names.get(0),Integer.parseInt((String) names.get(1)),Integer.parseInt((String) names.get(2)),Integer.parseInt((String) names.get(3)));
    }

    public static Cards getDeffendUP(){
        return new DeffendUP((String) names.get(0),Integer.parseInt((String) names.get(1)),Integer.parseInt((String) names.get(2)),Integer.parseInt((String) names.get(3)));
    }

    public static Cards getDestroy(){
        return new DestroyCard((String) names.get(0),Integer.parseInt((String) names.get(1)),Integer.parseInt((String) names.get(2)),Integer.parseInt((String) names.get(3)));
    }

    public static Cards getStole(){
        return new StoleCard((String) names.get(0),Integer.parseInt((String) names.get(1)),Integer.parseInt((String) names.get(2)),Integer.parseInt((String) names.get(3)));
    }

    public static Cards getMagic(){
        return new MagicCards((String)names.get(0));
    }


}
