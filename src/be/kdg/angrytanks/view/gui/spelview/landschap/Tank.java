package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Afbeelding;
import be.kdg.angrytanks.view.gui.constanten.Constanten;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */
/*
    Een Tank is een MultiStateBlok met plaats voor 11 Afbeeldingen;
    * voor elk tiental graden tussen 0 en 90 één Afbeelding met een verschillende loop (indexen 0-9)
    * plus één met een Afbeelding van een tank in schroot (index 10)
    Een Tank kan op basis van een ingesteld aantal graden (tussen 0 en 90) de juiste Afbeelding instellen.
 */
public abstract class Tank extends MultiStateBlok{
    private int loopGraden;
    private boolean schroot = false;

    public Tank(Afbeelding[] afbeeldingen){
        super(new Afbeelding[11]);
        setAfbeeldingen(afbeeldingen);
    }

    public Tank(){
        this(new Afbeelding[11]);
    }

    @Override
    public void setAfbeeldingen(Afbeelding[] afbeeldingen){
        if(afbeeldingen.length != 11) throw new IllegalArgumentException("Geeft een array van grootte 11");
        super.setAfbeeldingen(afbeeldingen);
    }

    public void setAfbeelding(Afbeelding afbeelding, int loopGraden){
        super.setAfbeelding(afbeelding, berekenIndex(loopGraden));
    }

    public void stelLoopGradenIn(int loopGraden){
        this.loopGraden = loopGraden;
    }

    @Override
    public Afbeelding getAfbeelding(){
        if(schroot){
            return super.getAfbeelding(10);
        }
        else{
            return super.getAfbeelding(berekenIndex(loopGraden));
        }
    }

    public Afbeelding getAfbeelding(int loopGraden){
        return super.getAfbeelding(berekenIndex(loopGraden));
    }

    private int berekenIndex(int loopGraden){
        if(loopGraden < 0) loopGraden = 0;
        if(loopGraden > 90) loopGraden = 90;

        return (int)Math.round((double)(loopGraden - 1)/10);
    }

    public void setSchroot(boolean schroot){
        this.schroot = schroot;
    }



}
