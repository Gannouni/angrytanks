package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 19/02/14
 */

/*
    Een PixelProgressBar is een JProgressBar die er een beetje anders uitziet.
 */
public class PixelProgressBar extends JProgressBar {
    public PixelProgressBar(int min, int max, int value){
        super(min, max, value);
        this.putClientProperty("Nimbus.Overrides", new CustomUIDefaults());
        this.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
    }

    public PixelProgressBar(int min, int max){
        this(min, max, min);
    }

    public PixelProgressBar(){
        this(0, 0);
    }

    private class CustomUIDefaults extends UIDefaults {
        public CustomUIDefaults(){
            this.put("ProgressBar[Enabled].backgroundPainter", new javax.swing.Painter<JComponent>() {
                public void paint(Graphics2D g, JComponent c, int w, int h) {
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Constanten.BALK_TEKST_KLEUR);
                    g.drawRect(1, 1, w - 2, h - 2);
                }
            });

            this.put("ProgressBar[Enabled].foregroundPainter", new javax.swing.Painter<JComponent>() {
                public void paint(Graphics2D g, JComponent c, int w, int h) {
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Constanten.BALK_ACCENT_KLEUR);
                    g.fillRect(2, 2, w - 4, h - 4);
                }
            });

            this.put("ProgressBar.horizontalSize", new Dimension(60,8));
        }
    }
}
