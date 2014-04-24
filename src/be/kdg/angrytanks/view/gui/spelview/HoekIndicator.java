package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.layout.Indicator;

import javax.swing.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 17/02/14
 */

/**
    Een HoekIndicator is een Indicator met
        * de titel HOEK
        * een bijbehorend JLabel met ImageIcon
        * een numerieke waarde met een ° als achtervoegsel
 */
public class HoekIndicator extends Indicator {
    private static final String TITEL = "HOEK";
    private static final JLabel HOEK_LABEL = new JLabel(new ImageIcon(Constanten.HOEK.getImage()));

    public HoekIndicator(double waarde){
        super(TITEL, String.valueOf(waarde), HOEK_LABEL);

        this.setWaarde(waarde);
    }

    public HoekIndicator(){
        this(0);
    }

    public void setWaarde(double waarde){
        super.setWaarde(String.valueOf(waarde) + "°");
    }

    @Override
    public void setWaarde(String waarde){
        this.setWaarde(Integer.parseInt(waarde));
    }

}