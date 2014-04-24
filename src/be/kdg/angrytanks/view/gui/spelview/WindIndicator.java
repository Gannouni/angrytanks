package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.view.gui.layout.Indicator;
import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 17/02/14
 */

/**
    Een WindIndicator is een Indicator met
        * de titel WIND
        * een JLabel met een ImageIcon van een pijl naar links, die wordt getoond als de waarde negatief is
        * een JLabel met een ImageIcon van een stippellijn, die wordt getoond als de waarde 0 is
        * een JLabel met een ImageIcon van een pijl naar rechts, die wordt getoond als de waarde positief is
        * een numerieke waarde
 */
public class WindIndicator extends Indicator {
    private static final String TITEL = "WIND";
    private static final JLabel PIJL_NEUTRAAL_LABEL = new JLabel(new ImageIcon(Constanten.WIND_NEUTRAAL.getImage()));
    private static final JLabel PIJL_LINKS_LABEL = new JLabel(new ImageIcon(Constanten.WIND_LINKS.getImage()));
    private static final JLabel PIJL_RECHTS_LABEL = new JLabel(new ImageIcon(Constanten.WIND_RECHTS.getImage()));

    public WindIndicator(int waarde){
        super(TITEL, String.valueOf(waarde), PIJL_NEUTRAAL_LABEL);

        this.setWaarde(waarde);
    }

    public WindIndicator(){
        this(0);
    }

    public void setWaarde(int waarde){
        super.setWaarde(String.valueOf(waarde));

        //stel juiste icon in
        if(waarde < 0) setVisual(PIJL_LINKS_LABEL);
        else if(waarde > 0) setVisual(PIJL_RECHTS_LABEL);
        else setVisual(PIJL_NEUTRAAL_LABEL);
    }

    @Override
    public void setWaarde(String waarde){
        this.setWaarde(Integer.parseInt(waarde));
    }

}
