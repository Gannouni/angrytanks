package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.layout.ImagePanel;
import be.kdg.angrytanks.view.gui.layout.LayeredPanel;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.concurrent.Callable;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
    Een Landschap is een LayeredPanel dat bestaat uit verschillende lagen.
    * Een ImagePanel lucht bevat een afbeelding van de blauwe lucht met een gradient.
    * Een Baan toont de baan op basis van het schot.
    * Rotsen toont de rotsen op basis van de obstructie.
    * Wolken genereert de wolken en beweegt ze op basis van de wind.
    * Tanks beheert de twee Tanks
 */
public class Landschap extends LayeredPanel {
    Color achtergrondKleur = new Color(216, 232, 239);

    Spel spel;
    ImagePanel lucht;
    Baan baan;
    Rotsen rotsen;
    Wolken wolken;
    Tanks tanks;

    public Landschap(Spel spel){
        this.spel = spel;
        this.fixGrootte();

        initComponenten();
        layoutComponenten();
        addComponenten();
        addListeners();
        wolken.startWaaien();
    }

    public void initComponenten(){
        lucht = new ImagePanel(Constanten.LUCHT.getImage());
        wolken = new Wolken();
        rotsen = new Rotsen();
        tanks = new Tanks();
        baan = new Baan();
    }

    public void layoutComponenten(){
        this.setBackground(achtergrondKleur);
        this.setOpaque(true);
    }

    public void addComponenten(){
        this.voegLaagToe(lucht);
        this.voegLaagToe(wolken);
        this.voegLaagToe(rotsen);
        this.voegLaagToe(tanks);
        this.voegLaagToe(baan);
        this.bouwLagen();
    }

    public void addListeners(){
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                maakExtensies();
                updateAlles();
                wolken.genereerWolken();
            }

            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }

    public void resetLandschap(){
        tanks.resetTanks();
        maakExtensies();
        updateAlles();
    }

    public void updateAlles(){ //alles updaten in één keer!
        updateBaan();
        updateRotsen();
        updateTanks();
    }

    public void updateTanks(){ //update de tanks en repaint ze
        if(spel.isSpelStartKlaar()){
            tanks.setTanks(spel.getSchutterAPositie(), spel.getSchutterBPositie());
            tanks.setOffsetX((this.getWidth() - spel.getLevelBreedte() * Constanten.BLOKGROOTTE)/2);
            tanks.repaint();
        }
    }

    public void updateBaan(){ //update de baan en toon ze volledig
        if(spel.isSpelStartKlaar()){
            baan.setBaan(spel.getBaan());
            baan.toonAfgelegdeBaanVolledig();
            baan.setOffsetX((this.getWidth() - spel.getLevelBreedte() * Constanten.BLOKGROOTTE)/2);
            baan.repaint();
        }
    }

    public void animeerBaan(Callable onCompleteFunc){ //update de baan en animeer de baan
        if(spel.isSpelStartKlaar()){
            baan.setBaan(spel.getBaan());
            baan.setSnelheid((int)Math.round(spel.getKracht()));
            baan.animeer(onCompleteFunc);
        }
    }

    public void updateRotsen(){ //update de rotsen met de obstructie en repaint ze
        if(spel.isSpelStartKlaar()){
            rotsen.setTopLimit(spel.getLevelHoogte() + spel.getExtensieTop(), true);
            rotsen.setRightLimit(spel.getLevelBreedte() + spel.getExtensieRight(), true);
            rotsen.setBottomLimit(0 - spel.getExtensieBottom(), true);
            rotsen.setLeftLimit(0 - spel.getExtensieLeft(), true);
            rotsen.setRotsen(spel.getObstructie());
            rotsen.setOffsetX((this.getWidth() - spel.getLevelBreedte() * Constanten.BLOKGROOTTE) / 2);
            rotsen.repaint();
            maakExtensies();
            fixGrootte();
        }
    }

    public void maakExtensies(){ //update de extensies van het speelveld
        if(this.isShowing()){
            int extensieTop = (int)Math.ceil((double)this.getHeight() / Constanten.BLOKGROOTTE - spel.getLevelHoogte());
            spel.setExtensieTop(extensieTop);
            int extensieLeft = (int)Math.ceil(((double) this.getWidth() / Constanten.BLOKGROOTTE - spel.getLevelBreedte()) / 2);
            spel.setExtensieLeft(extensieLeft);
            int extensieRight = (int)Math.ceil(((double) this.getWidth() / Constanten.BLOKGROOTTE - spel.getLevelBreedte()) / 2);
            spel.setExtensieRight(extensieRight);
        }
    }

    public void fixGrootte(){ //maak het landschap minimum zo groot als de standaard Level-hoogte en -breedte
        if(spel.isSpelBezig()){
            Dimension grootte = new Dimension(spel.getLevelBreedte() * Constanten.BLOKGROOTTE + Constanten.BLOKGROOTTE, spel.getLevelHoogte() * Constanten.BLOKGROOTTE + Constanten.BLOKGROOTTE);
            this.setMinimumSize(grootte);
            this.setPreferredSize(grootte);
        }
    }

    public void setTankALoopGraden(int graden){
        tanks.setTankALoopGraden(graden);
    }

    public void setTankBLoopGraden(int graden){
        tanks.setTankBLoopGraden(graden);
    }

    public void setTankLoopGraden(int graden){
        if(spel.getSpelerAanDeBeurt() == spel.getSpelerA()){
            setTankALoopGraden(graden);
        } else{
            setTankBLoopGraden(graden);
        }
    }

    public void feedbackModus() throws AngryTanksException{
        updateBaan();
        updateRotsen();
        baan.setVisible(true);
        if(!spel.isSpelBezig()){
            if(spel.getAndereSpeler(spel.getWinnaar())==spel.getSpelerA()){
                tanks.setTankASchroot(true);
            }
            else{
                tanks.setTankBSchroot(true);
            }
        }
    }

    public void speelModus(){
        baan.setVisible(false);
        wolken.setWind(spel.getWind());
    }

    public void animatieModus(Callable onCompleteFunc){
        baan.setVisible(true);
        animeerBaan(onCompleteFunc);
    }
}
