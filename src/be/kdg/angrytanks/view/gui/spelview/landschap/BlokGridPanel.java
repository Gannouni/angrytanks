package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.dom.veld.Positie;
import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/02/14
 */
/*
    Een BlokGridPanel is een JPanel.
    Het kan een HashMap met Blokken toegewezen aan Posities inladen, en aan de hand daarvan zichtzelf painten.
 */
public class BlokGridPanel extends JPanel {

    protected HashMap<Positie, Blok> blokken;

    private boolean forceerBlokGrootte;
    private boolean herhaalX = false;
    private boolean herhaalY = false;
    private int offsetX = 0;
    private int offsetY = 0;

    public BlokGridPanel(HashMap<Positie, Blok> blokken, boolean forceerBlokGrootte){
        this.setLayout(null);
        this.blokken = new HashMap<Positie, Blok>(blokken);
        this.forceerBlokGrootte = forceerBlokGrootte;
        this.setOpaque(false);
    }

    public BlokGridPanel(){
        this(new HashMap<Positie, Blok>(), false);
    }

    //setters

    public void setForceerBlokGrootte(boolean forceerBlokGrootte){
        this.forceerBlokGrootte = forceerBlokGrootte;
    }

    public void setOffsetX(int offsetX){
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY){
        this.offsetY = offsetY;
    }

    public void setHerhaalX(boolean herhaalX){
        this.herhaalX = herhaalX;
    }

    public void setHerhaalY(boolean herhaalY){
        this.herhaalY = herhaalY;
    }

    public void setBlokken(HashMap<Positie,Blok> blokken){
        this.blokken = new HashMap<Positie, Blok>(blokken);
    }


    //getters

    public int getOffsetX(){
        return offsetX;
    }

    public int getOffsetY(){
        return offsetX;
    }


    //andere methods

    public void putBlok(Positie positie, Blok blok){
        blokken.put(positie, blok);
    }

    public void clearBlokken(){
        blokken.clear();
    }

    public void update(){
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){ //de paintComponent die alle Blokken in de blokken-HashMap op de juiste plaats zal painten
        super.paintComponent(g);
        int breedte = Constanten.BLOKGROOTTE;
        int hoogte = Constanten.BLOKGROOTTE;
        for(Positie pos : blokken.keySet()){
            Image img = blokken.get(pos).getAfbeelding().getImage();
            if(!forceerBlokGrootte){
                breedte = img.getWidth(this);
                hoogte = img.getHeight(this);
            }
            int x = pos.getX() * Constanten.BLOKGROOTTE + offsetX;
            int y = (this.getHeight() - (pos.getY() * Constanten.BLOKGROOTTE + offsetY)) - Constanten.BLOKGROOTTE;
            if(herhaalX) x = x % (this.getWidth() + Constanten.BLOKGROOTTE) + (x<0 ? this.getWidth()  + Constanten.BLOKGROOTTE : 0) - Constanten.BLOKGROOTTE;
            if(herhaalY) y = y % (this.getHeight() + Constanten.BLOKGROOTTE) + (y<0 ? this.getHeight()  + Constanten.BLOKGROOTTE : 0) - Constanten.BLOKGROOTTE;
            g.drawImage(img, x, y, breedte, hoogte, this);

        }
    }
}
