package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 10/02/14
 */

/*
    Een MultiStateBlok is een Blok die tussen Afbeeldingen uit een array kan switchen
 */

public abstract class MultiStateBlok extends Blok{
    private Afbeelding[] afbeeldingen;

    public MultiStateBlok(Afbeelding[] afbeeldingen){
        super(afbeeldingen[0]);
        setAfbeeldingen(afbeeldingen);
    }

    public void setAfbeeldingen(Afbeelding[] afbeeldingen){
        this.afbeeldingen = afbeeldingen;
    }

    public void setAfbeelding(Afbeelding afbeelding, int index){
        if(index >= afbeeldingen.length || index < 0) throw new IndexOutOfBoundsException();
        afbeeldingen[index] = afbeelding;
    }

    public Afbeelding getAfbeelding(int index){
        if(index >= afbeeldingen.length || index < 0) throw new IndexOutOfBoundsException();
        return afbeeldingen[index];
    }
}

