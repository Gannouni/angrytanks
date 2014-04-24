package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */
/*
    Een Blok beheert een afbeelding, en kan gebruikt worden in een BlokGridPanel.
 */
public abstract class Blok{
    private Afbeelding afbeelding;

    public Blok(Afbeelding afbeelding){
        this.afbeelding = afbeelding;
    }

    public Afbeelding getAfbeelding(){
        return afbeelding;
    }
}
