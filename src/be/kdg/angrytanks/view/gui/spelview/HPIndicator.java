package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.view.gui.layout.Indicator;
import be.kdg.angrytanks.view.gui.layout.PixelProgressBar;

import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 17/02/14
 */

/**
    Een HPIndicator is een Indicator met
        * de titel HP
        * een PixelProgressBar die de waarde visualiseert
        * een numerieke waarde
 */
public class HPIndicator extends Indicator {
    private static final String TITEL = "HP";


    private int maxWaarde = 0;
    private int waarde = 0;
    private PixelProgressBar hpBar;

    public HPIndicator(int maxWaarde, int waarde){
        super(TITEL, String.valueOf(waarde));

        //hpbar maken en toevoegen als visual
        hpBar = new PixelProgressBar(0, 0);
        hpBar.setPreferredSize(new Dimension(60, 8));
        hpBar.setMaximumSize(new Dimension(60, 8));
        hpBar.setMinimumSize(new Dimension(60,8));
        setVisual(hpBar);

        //waarden instellen
        this.setMaxWaarde(maxWaarde);
        this.setWaarde(waarde);
    }

    public HPIndicator(){
        this(0, 0);
    }

    public void setWaarde(int waarde){
        //controle
        if(waarde < 0) waarde = 0;
        if(waarde > maxWaarde) waarde = maxWaarde;

        this.waarde = waarde;

        //stel label in
        setLabelWaarde();

        //stel juiste waarde in op hpbar
        hpBar.setValue(this.waarde);
    }

    @Override
    public void setWaarde(String waarde){
        this.setWaarde(Integer.parseInt(waarde));

    }

    public void setMaxWaarde(int maxWaarde){
        //controle
        if(maxWaarde < 0) maxWaarde = 0;
        if(waarde > maxWaarde) waarde = maxWaarde;

        this.maxWaarde = maxWaarde;

        //stel label in
        setLabelWaarde();

        //stel juiste waarde in op hpbar
        hpBar.setMaximum(this.maxWaarde);
    }

    public void setLabelWaarde(){
        super.setWaarde(String.valueOf(this.waarde) + "/" + String.valueOf(this.maxWaarde));
    }

}

