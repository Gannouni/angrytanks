package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 19/02/14
 */
/*
    Een PixelPlainButton is een JButton die er een beetje anders uitziet.
 */
public class PixelPlainButton extends JButton{

    public PixelPlainButton(String naam){
        super(naam);
        this.putClientProperty("Nimbus.Overrides", new CustomUIDefaults());
        this.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
    }

    @Override
    public void setEnabled(boolean enabled){ //hack omdat normale UIDefaults van Nimbus voor enabled/disabled button tekstkleur niet lijken te werken
        super.setEnabled(enabled);

        if(enabled)
            this.setForeground(((UIDefaults) this.getClientProperty("Nimbus.Overrides")).getColor("Button.textForeground"));
        else
            this.setForeground(((UIDefaults) this.getClientProperty("Nimbus.Overrides")).getColor("Button[Disabled].textForeground"));
    }

    private class CustomUIDefaults extends UIDefaults {
        public CustomUIDefaults(){
            this.put("Button.font", Constanten.PIXEL_FONT_GROOT.getFont());
            this.put("Button.textForeground", Constanten.BALK_TEKST_KLEUR);
            this.put("Button[Disabled].textForeground", Constanten.BALK_STREEP_LICHTER_KLEUR);
            this.put("Button.contentMargins", new Insets(3,3,3,3));
        }
    }
}
