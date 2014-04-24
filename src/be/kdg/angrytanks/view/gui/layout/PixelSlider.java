package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 19/02/14
 */
/*
    Een PixelSlider is een JSlider die er een beetje anders uitziet.
 */
public class PixelSlider extends JSlider{
    public PixelSlider(int min, int max, int value){
        super(HORIZONTAL, min, max, value);
        this.putClientProperty("Nimbus.Overrides", new CustomUIDefaults());
        this.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
    }

    public PixelSlider(int min, int max){
        this(min, max, (min + max) / 2);
    }

    public PixelSlider(){
        this(0, 0);
    }

    private class CustomUIDefaults extends UIDefaults {
        public CustomUIDefaults(){
            this.put("Slider.", 15);
            this.put("Slider.thumbWidth", 20);
            this.put("Slider.thumbHeight", 20);
            this.put("Slider:SliderThumb.backgroundPainter", new javax.swing.Painter<JComponent>() {
                public void paint(Graphics2D g, JComponent c, int w, int h) {
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    g.setStroke(new BasicStroke(2));
                    //g.setColor(Color.RED);
                    //g.fillRect(1, 1, w-3, h-3);
                    g.setColor(Constanten.BALK_TEKST_KLEUR);
                    g.fillRect(1, 1, 14, 2);
                    g.fillRect(3, 3, 2, 2);
                    g.fillRect(11, 3, 2, 2);
                    g.fillRect(5, 5, 2, 2);
                    g.fillRect(9, 5, 2, 2);
                    g.fillRect(7, 7, 2, 10);
                }
            });
            this.put("Slider:SliderTrack.backgroundPainter", new javax.swing.Painter<JComponent>() {
                public void paint(Graphics2D g, JComponent c, int w, int h) {
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    g.setStroke(new BasicStroke(2));
                    //g.setColor(Color.GRAY);
                    //g.fillRoundRect(0, 6, w-1, 8, 8, 8);
                    g.setColor(Constanten.BALK_TEKST_KLEUR);
                    g.drawRect(1, 10, w - 2, 4);
                }
            });
        }
    }
}
