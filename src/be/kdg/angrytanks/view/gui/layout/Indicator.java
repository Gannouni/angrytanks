package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 17/02/14
 */

/*  Een Indicator is een Module die bestaat uit een JLabel met een titel bovenaan,
    een JLabel met een waarde onderaan en een vrij te kiezen JComponent in het midden.
 */

public class Indicator extends Module {
    private static final JComponent DUMMY_COMPONENT = new JPanel();

    private PixelPlainLabel titel;
    private PixelPlainLabel waarde;
    private JComponent visual;

    public Indicator(String titel, String waarde, JComponent visual){
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(2,15,2,15));

        this.titel = new PixelPlainLabel(titel);
        this.waarde = new PixelPlainLabel(waarde);
        this.visual = visual;

        this.titel.setBorder(new EmptyBorder(0,0,0,0));
        this.waarde.setBorder(new EmptyBorder(0,0,0,0));

        bouwModule();
    }

    public Indicator(String titel, String waarde){
        this(titel, waarde, DUMMY_COMPONENT);
    }

    public Indicator(String titel){
        this(titel, "");
    }

    public Indicator(){
        this("");
    }

    public void setTitel(String titel){
        this.titel.setText(titel);
        bouwModule();
    }

    public void setVisual(JComponent visual){
        this.visual = visual;
        bouwModule();
    }

    public void setWaarde(String waarde){
        this.waarde.setText(waarde);
        bouwModule();
    }

    private void bouwModule(){
        //leegmaken
        this.removeAll();

        //toevoegen
        this.add(titel);
        this.add(Box.createVerticalGlue());
        this.add(visual);
        this.add(Box.createVerticalGlue());
        this.add(waarde);

        //centreren
        titel.setAlignmentX(Component.CENTER_ALIGNMENT);
        visual.setAlignmentX(Component.CENTER_ALIGNMENT);
        waarde.setAlignmentX(Component.CENTER_ALIGNMENT);

        //markup
        this.titel.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        this.titel.setForeground(Constanten.BALK_TEKST_KLEUR);
        this.waarde.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        this.waarde.setForeground(Constanten.BALK_TEKST_KLEUR);
    }
}
