package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.view.gui.GUI;
import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.layout.Module;
import be.kdg.angrytanks.view.gui.layout.ModuleBalk;
import be.kdg.angrytanks.view.gui.layout.PixelPlainLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/**
    Het Dashboard is een JPanel dat wordt gebruikt om informatie te tonen
    In het Dashboard bevinden zich:
        * een Indicator om de HP van spelerA te tonen
        * een Indicator om de HP van spelerB te tonen
        * een Indicator om de windsnelheid en -richting te tonen
        * een Indicator om de hoek van het schot te tonen
        * een Indicator om de kracht van het schot te tonen

        * een JLabel om de naam van spelerA te tonen
        * een JLabel om de naam van spelerB te tonen

    Bovenstaande JComponents worden gelayout met behulp van een ModuleBalk voor de Indicators en een JPanel voor de JLabels met namen.
    Aan de linkerkant wordt de naam van spelerA getoond, aan de rechterkant de naam van spelerB.
    De Indicators worden uitgelijnd aan de kant van de speler op wie ze van toepassing zijn.
    De wind-, hoek- en kracht- Indicators worden uitgelijnd aan de kant van de speler die aan de beurt is.
    De wind-, hoek- en kracht- Indicators worden zichtbaar wanneer feedbackModus() wordt aangeroepen, en worden onzichtbaar wanneer speelModus() wordt aangeroepen.
 */
public class Dashboard extends JPanel{
    private Spel spel;
    private ModuleBalk bovenBalk;
    private JPanel onderBalk;

    private Module tankAImage;
    private Module tankBImage;
    private HPIndicator tankAHP;
    private HPIndicator tankBHP;
    private WindIndicator windIndicator;
    private KrachtIndicator krachtIndicator;
    private HoekIndicator hoekIndicator;

    private PixelPlainLabel spelerANaam;
    private PixelPlainLabel vs;
    private PixelPlainLabel spelerBNaam;
    private JLabel slashesLinks;
    private JLabel slashesRechts;


    public Dashboard(Spel spel){
        this.spel = spel;

        initComponenten();
        layoutComponenten();
        addComponenten();
    }

    private void initComponenten(){
        //bovenbalk
        bovenBalk = new ModuleBalk();
        windIndicator = new WindIndicator();
        krachtIndicator = new KrachtIndicator();
        hoekIndicator = new HoekIndicator();
        tankAImage = new Module();
            tankAImage.setLayout(new BoxLayout(tankAImage,BoxLayout.PAGE_AXIS));
            tankAImage.add(Box.createVerticalGlue());
            tankAImage.add(new JLabel(new ImageIcon(Constanten.TANK_LINKS.getImage())));
            tankAImage.add(Box.createVerticalGlue());
            tankAImage.setBorder(new EmptyBorder(15,0,15,15));
        tankBImage = new Module();
            tankBImage.setLayout(new BoxLayout(tankBImage,BoxLayout.PAGE_AXIS));
            tankBImage.add(Box.createVerticalGlue());
            tankBImage.add(new JLabel(new ImageIcon(Constanten.TANK_RECHTS.getImage())));
            tankBImage.add(Box.createVerticalGlue());
            tankBImage.setBorder(new EmptyBorder(15,15,15,0));
        tankAHP = new HPIndicator();
        tankBHP = new HPIndicator();

        //onderbalk
        onderBalk = new JPanel();
        spelerANaam = new PixelPlainLabel("");
        vs = new PixelPlainLabel("vs.");
        spelerBNaam = new PixelPlainLabel("");
        slashesLinks = new JLabel(new ImageIcon(Constanten.SLASHES_LINKS.getImage()));
        slashesRechts = new JLabel(new ImageIcon(Constanten.SLASHES_RECHTS.getImage()));
    }

    private void layoutComponenten(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //onderbalk
        onderBalk.setLayout(new BoxLayout(onderBalk, BoxLayout.LINE_AXIS));
        onderBalk.setBackground(Constanten.BALK_BG_KLEUR);
        onderBalk.setBorder( BorderFactory.createMatteBorder(Constanten.BASIS_DIKTE, 0, 0, 0, Constanten.BALK_STREEP_KLEUR));

        spelerANaam.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        vs.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        spelerBNaam.setFont(Constanten.PIXEL_FONT_GROOT.getFont());

        spelerANaam.setForeground(Constanten.BALK_TEKST_KLEUR);
        vs.setForeground(Constanten.BALK_TEKST_KLEUR);
        spelerBNaam.setForeground(Constanten.BALK_TEKST_KLEUR);

        spelerANaam.setAlignmentY(Component.CENTER_ALIGNMENT);
        vs.setAlignmentY(Component.CENTER_ALIGNMENT);
        spelerBNaam.setAlignmentY(Component.CENTER_ALIGNMENT);

        spelerANaam.setBorder(new EmptyBorder(0, 5, 0, 5));
        vs.setBorder(new EmptyBorder(0, 5, 0, 5));
        spelerBNaam.setBorder(new EmptyBorder(0, 5, 0, 5));
    }

    private void addComponenten(){
        this.add(bovenBalk);
        this.add(onderBalk);

        //bovenbalk
        bovenBalk.add(tankAImage, ModuleBalk.Kant.LINKS);
        bovenBalk.add(tankAHP, ModuleBalk.Kant.LINKS);
        bovenBalk.add(tankBImage, ModuleBalk.Kant.RECHTS);
        bovenBalk.add(tankBHP, ModuleBalk.Kant.RECHTS);

        //onderbalk
        onderBalk.add(spelerANaam);
        onderBalk.add(Box.createHorizontalGlue());
        onderBalk.add(slashesLinks);
        onderBalk.add(Box.createHorizontalGlue());
        onderBalk.add(vs);
        onderBalk.add(Box.createHorizontalGlue());
        onderBalk.add(slashesRechts);
        onderBalk.add(Box.createHorizontalGlue());
        onderBalk.add(spelerBNaam);
    }

    public void updateInfo(){
        spelerANaam.setText(spel.getSpelerA().getNaam());
        spelerBNaam.setText(spel.getSpelerB().getNaam());
        windIndicator.setWaarde(spel.getWind());
        hoekIndicator.setWaarde(spel.getHoek());
        krachtIndicator.setWaarde(spel.getKracht());
        tankAHP.setMaxWaarde(spel.getSpelerA().getSchutter().getMaxHp());
        tankBHP.setMaxWaarde(spel.getSpelerB().getSchutter().getMaxHp());
        tankAHP.setWaarde(spel.getSpelerA().getSchutter().getHp());
        tankBHP.setWaarde(spel.getSpelerB().getSchutter().getHp());
    }

    public void speelModus(){
        updateInfo();
        maakLeeg();
        bovenBalk.add(windIndicator, spelerKant());
    }

    public void feedbackModus(){
        updateInfo();
        maakLeeg();
        bovenBalk.add(windIndicator, spelerKant());
        bovenBalk.add(hoekIndicator, spelerKant());
        bovenBalk.add(krachtIndicator, spelerKant());
    }

    private void maakLeeg(){
        bovenBalk.remove(windIndicator);
        bovenBalk.remove(hoekIndicator);
        bovenBalk.remove(krachtIndicator);
    }

    private ModuleBalk.Kant spelerKant(){
        ModuleBalk.Kant kant = ModuleBalk.Kant.LINKS;
        if(spel.getSpelerAanDeBeurt() == spel.getSpelerB()){
            kant = ModuleBalk.Kant.RECHTS;
        }

        return kant;
    }
}
