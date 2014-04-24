package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.layout.Indicator;

import javax.swing.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 17/02/14
 */

/**
    Een KrachtIndicator is een Indicator met
        * de titel KRACHT
        * een bijbehorend JLabel met ImageIcon
        * een numerieke waarde
 */
public class KrachtIndicator extends Indicator {
    private static final String TITEL = "KRACHT";
    private static final JLabel KRACHT_LABEL = new JLabel(new ImageIcon(Constanten.KRACHT.getImage()));

    public KrachtIndicator(double waarde){
        super(TITEL, String.valueOf(waarde), KRACHT_LABEL);

        this.setWaarde(waarde);
    }

    public KrachtIndicator(){
        this(0);
    }

    public void setWaarde(double waarde){
        if(waarde < 0) waarde = 0;
        super.setWaarde(String.valueOf(waarde));
    }

    @Override
    public void setWaarde(String waarde){
        this.setWaarde(Integer.parseInt(waarde));
    }

}
