package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 10/02/14
 */

/*
    Een GeranddeBlok is een Blok die op basis van 16 Afbeeldingen zijn eigen Afbeelding kan instellen op basis van de aangegeven randen.
 */

public abstract class GeranddeBlok extends MultiStateBlok{
    private boolean top = false, right = false, bottom = false, left = false;

    public GeranddeBlok(Afbeelding[] afbeeldingen){
        super(new Afbeelding[16]);
        setAfbeeldingen(afbeeldingen);
    }

    public GeranddeBlok(){
        this(new Afbeelding[16]);
    }

    @Override
    public void setAfbeeldingen(Afbeelding[] afbeeldingen){
        if(afbeeldingen.length != 16) throw new IllegalArgumentException("Geeft een array van grootte 16");
        super.setAfbeeldingen(afbeeldingen);
    }

    public void setAfbeelding(Afbeelding afbeelding, boolean top, boolean right, boolean bottom, boolean left){
        super.setAfbeelding(afbeelding, berekenIndex(top, right, bottom, left));
    }

    public void stelRandIn(boolean top, boolean right, boolean bottom, boolean left){
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    @Override
    public Afbeelding getAfbeelding(){
        return super.getAfbeelding(berekenIndex(this.top, this.right, this.bottom, this.left));
    }

    public Afbeelding getAfbeelding(boolean top, boolean right, boolean bottom, boolean left){
        return super.getAfbeelding(berekenIndex(top, right, bottom, left));
    }

    private int berekenIndex(boolean top, boolean right, boolean bottom, boolean left){
        int index = 0;

        if(top) index += 1;
        if(right) index += 2;
        if(bottom) index += 4;
        if(left) index += 8;

        return index;
    }
}
