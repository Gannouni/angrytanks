package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.veld.Positie;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
    Rotsen is een GeranddeBlokGridPanel dat aan de hand van een HashSet met Posities zichzelf vult met RotsBlokken
 */
public class Rotsen extends GeranddeBlokGridPanel {

    public Rotsen(HashSet<Positie> posities){
        super(new HashMap<Positie, Blok>(), true);
        setRotsen(posities);
        this.setOpaque(false);
    }

    public Rotsen(){
        this(new HashSet<Positie>());
    }

    public void setRotsen(HashSet<Positie> posities){
        HashMap<Positie, Blok> blokken = new HashMap<Positie, Blok>();
        for(Positie positie : posities){
            blokken.put(positie, new RotsBlok());
        }
        super.setBlokken(blokken);
        super.resetRanden();
    }
}
