package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 20/02/14
 */

/*
    Een PixelPlainComboBox is een JComboBox die er een beetje anders uitziet.
 */
public class PixelPlainComboBox<E> extends JComboBox {

    private boolean ignoreRepaint;

    public PixelPlainComboBox() {
        super();

        this.putClientProperty("Nimbus.Overrides", new CustomUIDefaults());
        this.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
        this.setUI(new PixelComboBoxArrow(this));

        this.setOpaque(true);
        this.setForeground(Constanten.BALK_TEKST_KLEUR);
        this.setBorder(new EmptyBorder(0,0,0,0));

    }

    @Override
    public void paintComponent(Graphics g) { //nasty hack!
        ignoreRepaint = true;
        try {
            java.awt.Rectangle b = getComponent(0).getBounds();
            g.setClip(3, 3, getWidth() - b.width - 6, getHeight() - 6);
            setBackground(Constanten.BALK_BG_KLEUR);
            super.paintComponent(g);
            g.setClip(b.x, b.y, b.width, b.height);
            setBackground(Constanten.BALK_BG_KLEUR);
            super.paintComponent(g);

        } finally {
            ignoreRepaint = false;
        }
    }

    private class CustomUIDefaults extends UIDefaults {
        public CustomUIDefaults(){
            this.put("ComboBox.font", Constanten.PIXEL_FONT_GROOT.getFont());
            this.put("ComboBox.background", Constanten.BALK_BG_KLEUR);
            this.put("ComboBox.textForeground", Constanten.BALK_TEKST_KLEUR);
            this.put("ComboBox.contentMargins", new Insets(3,3,3,3));
            this.put("ComboBox.padding", new Insets(3,3,3,3));
            this.put("ComboBox.popupInsets", new Insets(3,3,3,3));
            this.put("ComboBox.listRenderer.contentMargins", new Insets(3,3,3,3));
            this.put("ComboBox.renderer.contentMargins", new Insets(3,3,3,3));
            this.put("ComboBox.textField.contentMargins", new Insets(3,3,3,3));

        }
    }

    private class PixelComboBoxArrow extends BasicComboBoxUI{
            public PixelComboBoxArrow(JComponent c){

            }

            @Override
            protected JButton createArrowButton(){
                JButton pijltje = new JButton(new ImageIcon(Constanten.COMBOBOX_PIJL.getImage()));
                pijltje.setContentAreaFilled(false);
                Dimension grootte = new Dimension(30,20);
                pijltje.setBorder(new EmptyBorder(0, 0, 0, 0));
                pijltje.setMaximumSize(grootte);
                pijltje.setMinimumSize(grootte);
                pijltje.setPreferredSize(grootte);

                return pijltje;

            }

    }


}
