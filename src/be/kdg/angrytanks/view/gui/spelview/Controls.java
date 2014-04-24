package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.view.gui.GUI;
import be.kdg.angrytanks.view.gui.constanten.*;
import be.kdg.angrytanks.view.gui.layout.*;
import be.kdg.angrytanks.view.gui.spelview.landschap.Landschap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/**
    Controls is een JPanel dat wordt gebruikt om een schot te lanceren en feedback te ontvangen
    In de Controls bevinden zich:
        * een InfoModule om te tonen welke speler aan de beurt is
        * een SliderModule voor de kracht
        * een SliderModule voor de hoek
        * een JButton lanceerKnop om het schot te lanceren

        * een InfoModule om feedback te tonen
        * een JButton okKnop om de feedback te bevestigen en een nieuwe beurt te starten
        * een JButton hoeraKnop die verschijnt als het spel gedaan is, om terug te keren naar het hoofdmenu

    Bovenstaande JComponents worden gelayout met behulp van een ModuleBalk en enkele omkaderende Modules.
    Ze worden uitgewisseld wanneer gekozen wordt voor speelModus() of feedbackModus().
 */
public class Controls extends JPanel {
    Spel spel;
    GUI gui;
    SpelPanel spelPanel;

    private JButton stopKnop;
    private JButton helpKnop;
    private InfoModule beurtModule;
    private JButton lanceerKnop;
    private SliderModule hoekControl;
    private SliderModule krachtControl;
    private JButton okKnop;
    private JButton hoeraKnop;
    private InfoModule feedbackModule;

    private CakeModule menu;
    private Module lanceerKnopModule;
    private Module okKnopModule;
    private Module hoeraKnopModule;
    private ModuleBalk controleBalk;


    public Controls(Spel spel){
        this.spel = spel;

        initComponenten();
        layoutComponenten();
        addComponenten();
        addListeners();
    }

    private void initComponenten(){
        //essentials
        stopKnop = new PixelPlainButton("STOPPEN");
        helpKnop = new PixelPlainButton("HELP");
        beurtModule = new InfoModule();
        hoekControl = new SliderModule("HOEK", 0, 0, 0, "°", new Callable() {
            @Override
            public Object call() throws Exception {
                if(hoekControl != null) spelPanel.setTankLoopGraden(hoekControl.getWaarde());
                return null;
            }
        });
        krachtControl = new SliderModule("KRACHT", 0, 0, 0, "");
        feedbackModule = new InfoModule();
        lanceerKnop = new PixelRoundedButton("LANCEER");
        okKnop = new PixelRoundedButton("OK");
        hoeraKnop = new PixelRoundedButton("HOERA!");

        //layoutelementen
        controleBalk = new ModuleBalk();
        lanceerKnopModule = new Module();
        okKnopModule = new Module();
        hoeraKnopModule = new Module();
        menu = new CakeModule();
    }

    private void layoutComponenten(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //menu
        menu.setMaximumSize(new Dimension(200, 100));
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.setBorder(new EmptyBorder(0, 0, 0, 0));

        helpKnop.setAlignmentX(Box.CENTER_ALIGNMENT);
        helpKnop.setBorder(new EmptyBorder(8,15,7,15));
        stopKnop.setAlignmentX(Box.CENTER_ALIGNMENT);
        stopKnop.setBorder(new EmptyBorder(7,15,8,15));

        //controls
        lanceerKnopModule.setLayout(new BoxLayout(lanceerKnopModule, BoxLayout.PAGE_AXIS));
        lanceerKnopModule.setBorder(new EmptyBorder(20, 25, 20, 25));
        okKnopModule.setLayout(new BoxLayout(okKnopModule, BoxLayout.PAGE_AXIS));
        okKnopModule.setBorder(new EmptyBorder(20,25,20,25));
        hoeraKnopModule.setLayout(new BoxLayout(hoeraKnopModule, BoxLayout.PAGE_AXIS));
        hoeraKnopModule.setBorder(new EmptyBorder(20, 25, 20, 25));


    }

    private void addComponenten(){
        this.add(controleBalk);

        controleBalk.add(menu, ModuleBalk.Kant.LINKS);
        controleBalk.add(lanceerKnopModule, ModuleBalk.Kant.RECHTS);
        controleBalk.add(hoekControl, ModuleBalk.Kant.RECHTS);
        controleBalk.add(krachtControl, ModuleBalk.Kant.RECHTS);

        menu.setTopComponent(stopKnop);
        menu.setBottomComponent(helpKnop);

        lanceerKnopModule.add(Box.createVerticalGlue());
        lanceerKnopModule.add(lanceerKnop);
        lanceerKnopModule.add(Box.createVerticalGlue());
        okKnopModule.add(Box.createVerticalGlue());
        okKnopModule.add(okKnop);
        okKnopModule.add(Box.createVerticalGlue());
        hoeraKnopModule.add(Box.createVerticalGlue());
        hoeraKnopModule.add(hoeraKnop);
        hoeraKnopModule.add(Box.createVerticalGlue());

    }

    private void addListeners(){
        stopKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.stopSpel();

            }
        });

        helpKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.openHelpDialog();
            }
        });

        //spelcontrols
        lanceerKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    spel.speel(krachtControl.getWaarde(), hoekControl.getWaarde());
                    lanceerKnop.setEnabled(false);
                    spelPanel.animatieModus();

                } catch (AngryTanksException ex) {
                    gui.toonError(ex);
                }
                Constanten.GELUID_VUUR.speelAf();


            }
        });

        okKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    spel.nieuweBeurt();
                    spelPanel.speelModus();
                } catch (AngryTanksException ex) {
                    gui.toonError(ex);
                }
            }
        });

        hoeraKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.openHoofdMenuPanel();

            }
        });
    }

    public void readyControls(){
        this.spelPanel = (SpelPanel) SwingUtilities.getAncestorOfClass(SpelPanel.class, this);
        this.gui = (GUI) SwingUtilities.getAncestorOfClass(GUI.class, this.spelPanel);
    }

    public void resetControls(){
        krachtControl.setMinWaarde(Spel.MIN_KRACHT);
        krachtControl.setMaxWaarde(Spel.MAX_KRACHT);
        krachtControl.setWaarde((int)Math.round(((double) Spel.MAX_KRACHT - Spel.MIN_KRACHT) / 2));
        hoekControl.setMinWaarde(Spel.MIN_HOEK);
        hoekControl.setMaxWaarde(Spel.MAX_HOEK);
        hoekControl.setWaarde((int)Math.round(((double)Spel.MAX_HOEK - Spel.MIN_HOEK) / 2));
    }

    public void feedbackModus(){
        updateFeedback();
        clearModus();

        //elementen toevoegen
        if(!spel.isSpelBezig()){
            hoeraKnop.setEnabled(true);
            controleBalk.add(hoeraKnopModule, ModuleBalk.Kant.RECHTS);
        } else {
            okKnop.setEnabled(true);
            controleBalk.add(okKnopModule, ModuleBalk.Kant.RECHTS);
        }
        controleBalk.add(feedbackModule, ModuleBalk.Kant.RECHTS);
    }

    public void speelModus(){
        updateBeurtInfo();
        clearModus();
        spelPanel.setTankLoopGraden(hoekControl.getWaarde());

        //elementen toevoegen
        lanceerKnop.setEnabled(true);
        controleBalk.add(lanceerKnopModule, ModuleBalk.Kant.RECHTS);
        controleBalk.add(hoekControl, ModuleBalk.Kant.RECHTS);
        controleBalk.add(krachtControl, ModuleBalk.Kant.RECHTS);
        controleBalk.add(beurtModule, ModuleBalk.Kant.RECHTS);
    }

    public void clearModus(){
        //elementen weghalen
        lanceerKnop.setEnabled(false);
        hoeraKnop.setEnabled(false);
        okKnop.setEnabled(false);
        controleBalk.remove(beurtModule);
        controleBalk.remove(lanceerKnopModule);
        controleBalk.remove(hoekControl);
        controleBalk.remove(krachtControl);
        controleBalk.remove(okKnopModule);
        controleBalk.remove(hoeraKnopModule);
        controleBalk.remove(feedbackModule);
    }

    public void updateBeurtInfo(){
        String naamSpeler;
        String tekst;

        naamSpeler = spel.getSpelerAanDeBeurt().getNaam();

        tekst = naamSpeler + " is aan de beurt.";
        beurtModule.setTekst(tekst);
    }

    public void updateFeedback(){
        ImageIcon icon;
        String tekst;

        //tekst en icon bepalen
        if((spel.isVijandGeraakt() || spel.isZelfGeraakt()) && !spel.isSpelBezig()){
            icon = new ImageIcon(Constanten.ICOON_SKULL.getImage());
            String naamWinnaar = "";
            try{
                naamWinnaar = spel.getWinnaar().getNaam();
            } catch(AngryTanksException e){
                gui.toonError(e);
            }
            tekst = naamWinnaar + " heeft gewonnen!";
            Constanten.GELUID_RAAK.speelAf();
            Constanten.GELUID_FINALE.speelAf();
        } else{

            String naamSpeler;
            String naamVijand;


                naamSpeler = spel.getSpelerAanDeBeurt().getNaam();
                naamVijand = spel.getAndereSpeler(spel.getSpelerAanDeBeurt()).getNaam();

            if(spel.isVijandGeraakt()){
                icon = new ImageIcon(Constanten.ICOON_TARGET.getImage());
                tekst = naamSpeler + " heeft " + naamVijand + " geraakt!";
                Constanten.GELUID_RAAK.speelAf();
            } else if(spel.isZelfGeraakt()){
                icon = new ImageIcon(Constanten.ICOON_SAD.getImage());
                tekst = naamSpeler + " heeft zichzelf geraakt...";
                Constanten.GELUID_RAAK.speelAf();
            } else if(spel.isObstructieGeraakt()){
                icon = new ImageIcon(Constanten.ICOON_BOM.getImage());
                tekst = naamSpeler + " heeft een rots geraakt.";
                Constanten.GELUID_LAND.speelAf();
            } else{
                icon = new ImageIcon(Constanten.ICOON_WOLK.getImage());
                tekst = naamSpeler + " heeft niets geraakt.";
            }
        }

        feedbackModule.setTekst(tekst);
        feedbackModule.setPicto(icon);
    }
}
