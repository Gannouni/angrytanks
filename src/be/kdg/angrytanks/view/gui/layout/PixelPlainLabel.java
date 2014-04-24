package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 21/02/14
 */

/*
    Een PixelPlainLabel is een JLabel die er een beetje anders uitziet.
 */
public class PixelPlainLabel extends JLabel {
    public PixelPlainLabel(String text){
        super(text);

        this.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        this.setForeground(Constanten.BALK_TEKST_KLEUR);
        this.setBackground(Constanten.BALK_BG_KLEUR);
        this.setBorder(new EmptyBorder(5,5,5,5));
    }

    public PixelPlainLabel(){
        this("");
    }

    @Override
    public void setBorder(Border border) {
        super.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5,5,5,5)));
    }
}
