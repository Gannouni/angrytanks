package be.kdg.angrytanks.view.gui.layout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 26/02/14
 */

/*
    Een InfoModule is een Module die een boodschap weergeeft, en eventueel links een ImageIcon.
 */

public class InfoModule extends Module{

    PixelPlainLabel tekstBoven;
    PixelPlainLabel tekstOnder;
    JLabel picto;
    JPanel horizontaalPaneel;
    JPanel verticaalPaneel;

    public InfoModule(){
        initComponenten();
        layoutComponenten();
        addComponenten();
    }

    private void initComponenten(){
        tekstBoven = new PixelPlainLabel();
        tekstOnder = new PixelPlainLabel();
        picto = new JLabel();
        horizontaalPaneel = new JPanel();
        verticaalPaneel = new JPanel();
    }

    private void layoutComponenten(){
        horizontaalPaneel.setLayout(new BoxLayout(horizontaalPaneel, BoxLayout.LINE_AXIS));
        horizontaalPaneel.setOpaque(false);
        verticaalPaneel.setLayout(new BoxLayout(verticaalPaneel, BoxLayout.PAGE_AXIS));
        verticaalPaneel.setOpaque(false);

        tekstBoven.setBorder(new EmptyBorder(0, 10, -3, 10));
        tekstOnder.setBorder(new EmptyBorder(-3, 10, 0, 10));
        picto.setOpaque(false);
        picto.setBorder(new EmptyBorder(0, 4, 0, 0));
    }
    private void addComponenten(){
        horizontaalPaneel.add(picto);
        horizontaalPaneel.add(Box.createRigidArea(new Dimension(10,1)));
        horizontaalPaneel.add(verticaalPaneel);

        verticaalPaneel.add(Box.createVerticalGlue());
        verticaalPaneel.add(tekstBoven);
        verticaalPaneel.add(tekstOnder);
        verticaalPaneel.add(Box.createVerticalGlue());

        this.add(Box.createVerticalGlue());
        this.add(horizontaalPaneel);
        this.add(Box.createVerticalGlue());
    }

    public void setTekst(String tekst){
        //html-trucje om newlines te gebruiken in een JLabel

        int splitIndex = tekst.length();
        ArrayList<Integer> spaties = new ArrayList<Integer>();
        for(int i = tekst.indexOf(' '); i >= 0; i = tekst.indexOf(' ', i + 1)) {
            spaties.add(i);
        }
        int besteAfstand = tekst.length();
        for(Integer i : spaties){
            int afstand = Math.abs((int) Math.round((double) tekst.length() / 2) - i);
            if(afstand <= besteAfstand){
                besteAfstand = afstand;
                splitIndex = i;
            }
        }

        if(splitIndex != tekst.length() - 1){
            verticaalPaneel.add(tekstOnder);
            tekstBoven.setText(tekst.substring(0, splitIndex + 1));
            tekstOnder.setText(tekst.substring(splitIndex + 1, tekst.length()));
        } else{
            verticaalPaneel.remove(tekstOnder);
            tekstBoven.setText(tekst);
        }
    }

    public void setPicto(ImageIcon picto){
        this.picto.setIcon(picto);
    }
}
