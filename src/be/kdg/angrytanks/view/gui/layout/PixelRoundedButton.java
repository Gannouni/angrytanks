package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 19/02/14
 */

/*
    Een PixelRoundedButton is een JButton die er een beetje anders uitziet.
    De PixelRoundedButton heeft een gepixeld, afgerond randje.
 */
public class PixelRoundedButton extends JButton{


    public PixelRoundedButton(String naam){
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
            this.put("Button.contentMargins", new Insets(6,12,6,12));

            class ButtonEdgePainter implements javax.swing.Painter<JComponent>{
                private Color randKleur;

                public ButtonEdgePainter(Color randKleur){ super(); this.randKleur = randKleur; }

                public ButtonEdgePainter(){ this(Constanten.BALK_STREEP_KLEUR); }

                public void paint(Graphics2D g, JComponent c, int w, int h) {
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    g.setColor(this.randKleur);
                    //hoekje linksboven
                    g.fillRect(4, 4, 2, 2); g.fillRect(6, 2, 4, 2); g.fillRect(2, 6, 2, 4);
                    //hoekje rechtsboven
                    g.fillRect(w - 6, 4, 2, 2); g.fillRect(w - 10, 2, 4, 2); g.fillRect(w - 4, 6, 2, 4);
                    //hoekje rechtsonder
                    g.fillRect(w-6, h-6, 2, 2); g.fillRect(w-10, h-4, 4, 2); g.fillRect(w-4, h-10, 2, 4);
                    //hoekje linksonder
                    g.fillRect(4, h-6, 2, 2); g.fillRect(6, h-4, 4, 2); g.fillRect(2, h-10, 2, 4);
                    //zijranden
                    g.fillRect(10, 0, w-20, 2); g.fillRect(10, h-2, w-20, 2); g.fillRect(0, 10, 2, h-20); g.fillRect(w - 2, 10, 2, h-20);
                }

            }
            Painter buttonPainterLichter = new ButtonEdgePainter(Constanten.BALK_STREEP_LICHTER_KLEUR);
            Painter buttonPainterDonker = new ButtonEdgePainter(Constanten.BALK_STREEP_KLEUR);
            this.put("Button[Default].backgroundPainter", buttonPainterDonker);
            this.put("Button[Default+Focused+MouseOver].backgroundPainter", buttonPainterLichter);
            this.put("Button[Default+Focused+Pressed].backgroundPainter", buttonPainterLichter);
            this.put("Button[Default+Focused].backgroundPainter", buttonPainterDonker);
            this.put("Button[Default+MouseOver].backgroundPainter", buttonPainterLichter);
            this.put("Button[Default+Pressed].backgroundPainter", buttonPainterLichter);
            this.put("Button[Disabled].backgroundPainter", buttonPainterDonker);
            this.put("Button[Enabled].backgroundPainter", buttonPainterDonker);
            this.put("Button[Focused+MouseOver].backgroundPainter", buttonPainterLichter);
            this.put("Button[Focused+Pressed].backgroundPainter", buttonPainterLichter);
            this.put("Button[Focused].backgroundPainter", buttonPainterDonker);
            this.put("Button[MouseOver].backgroundPainter", buttonPainterLichter);
            this.put("Button[Pressed].backgroundPainter", buttonPainterLichter);
        }
    }
}
