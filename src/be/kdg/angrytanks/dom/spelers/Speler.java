package be.kdg.angrytanks.dom.spelers;

import be.kdg.angrytanks.dom.veld.Schutter;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */
public abstract class Speler {
    private Schutter schutter;
    private String naam;


    public Speler(String naam, Schutter schutter){
        this.naam = naam;
        this.schutter = schutter;
    }

    public String getNaam(){
        return naam;
    }

    public Schutter getSchutter(){
        return schutter;
    }

    public void resetSpeler(){
        schutter.resetHP();
    }



}
