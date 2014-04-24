package be.kdg.angrytanks.view.gui;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.dom.veld.Level;
import be.kdg.angrytanks.view.gui.constanten.*;
import be.kdg.angrytanks.view.gui.layout.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 13/02/14
 */

/**
    Het StartOptiesPanel is een JPanel met
        * een JComboBox levelCB om een level te selecteren
        * een JTextField maxWindInput, om de max. wind in te voeren
        * een JTextField startHPInput, om de start-HP in te voeren
        * een JTextField naamSpelerA, om de naam van speler A in te voeren
        * een JTextField naamSpelerB, om de naam van speler B in te voeren
        * een JButton startButton,
               om de opties door te geven aan de controller
               en om de GUI het spel te laten starten
        * een JButton terugButton, om de GUI terug te laten keren naar het hoofdmenu
 */

public class StartOptiesPanel extends JPanel {

    GUI gui;

    private Spel spel;

    //labels en input
    private JButton terugButton;
    private JButton startButton;
    private JTextField naamSpelerA;
    private JTextField naamSpelerB;
    private JLabel levelLabel;
    private JLabel maxWindLabel;
    private JTextField maxWindInput;
    private PixelPlainLabel startHPLabel;
    private JTextField startHPInput;
    private JComboBox levelCB;

    //layout
    private ModuleBalk bovenBalk;
    private JPanel middenBalk;
    private JPanel naamBalk;
    private JPanel onderBalk;
    private ModuleBalk menuBalk;
    private Module naamSpelerAModule;
    private Module naamSpelerBModule;
    private CakeModule levelModule;
    private CakeModule startHPModule;
    private CakeModule maxWindModule;
    private ImagePanel lefty;
    private ImagePanel righty;
    private Module terugKnopModule;

    public StartOptiesPanel(Spel spel){
        this.spel = spel;

        initComponenten();
        layoutComponenten();
        addComponenten();
        addListeners();

        laadLevels();
    }

    public void readyStartOptiesPanel(){
        this.gui = (GUI) SwingUtilities.getAncestorOfClass(GUI.class, this);
    }

    private void initComponenten(){
        terugButton = new PixelPlainButton("TERUG");
        startButton = new PixelRoundedButton("START");
        naamSpelerA = new JTextField();
        naamSpelerB = new JTextField();
        levelLabel = new PixelPlainLabel("LEVEL");
        maxWindLabel = new PixelPlainLabel("MAX. WIND");
        maxWindInput = new JTextField(String.valueOf(Spel.REG_MAXWIND));
        startHPLabel = new PixelPlainLabel("START HP");
        startHPInput = new JTextField(String.valueOf(Spel.REG_STARTHP));
        levelCB = new PixelPlainComboBox();

        bovenBalk = new ModuleBalk();
        middenBalk = new JPanel();
        onderBalk = new JPanel();
        naamBalk = new JPanel();
        menuBalk = new ModuleBalk();

        levelModule = new CakeModule();
        maxWindModule = new CakeModule();
        startHPModule = new CakeModule();
        naamSpelerAModule = new Module();
        naamSpelerBModule = new Module();
        terugKnopModule = new Module();

        lefty = new ImagePanel(Constanten.FANCYTANK_A.getImage(), false, true, ImagePanel.Houd.NONE,  ImagePanel.KantX.LEFT, ImagePanel.KantY.BOTTOM);
        righty = new ImagePanel(Constanten.FANCYTANK_B.getImage(), false, true, ImagePanel.Houd.NONE, ImagePanel.KantX.RIGHT, ImagePanel.KantY.BOTTOM);
    }

    private void layoutComponenten(){
        this.setLayout(new BorderLayout());
        this.setBackground(Constanten.BALK_BG_KLEUR);

        bovenBalk.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Constanten.BALK_STREEP_KLEUR));
        middenBalk.setLayout(new BoxLayout(middenBalk, BoxLayout.LINE_AXIS));
        onderBalk.setLayout(new BoxLayout(onderBalk, BoxLayout.PAGE_AXIS));

        bovenBalk.setBackground(Constanten.BALK_BG_KLEUR);
        middenBalk.setBackground(Constanten.BALK_BG_KLEUR);
        onderBalk.setBackground(Constanten.BALK_BG_KLEUR);

        naamBalk.setLayout(new BoxLayout(naamBalk, BoxLayout.LINE_AXIS));
        naamBalk.setBorder(new EmptyBorder(25, 25, 25, 25));
        naamBalk.setOpaque(false);

        Dimension naamInputSize = new Dimension(400, 50);
        naamSpelerA.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        naamSpelerA.setHorizontalAlignment(JTextField.CENTER);
        naamSpelerA.setForeground(Constanten.BALK_TEKST_KLEUR);
        naamSpelerA.setBackground(Constanten.BALK_BG_KLEUR);
        naamSpelerA.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Constanten.BALK_STREEP_KLEUR, 2), new EmptyBorder(3, 0, 3, 0)));
        naamSpelerA.setAlignmentY(Box.CENTER_ALIGNMENT);
        naamSpelerAModule.setPreferredSize(naamInputSize);
        naamSpelerAModule.setMaximumSize(naamInputSize);

        naamSpelerB.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        naamSpelerB.setHorizontalAlignment(JTextField.CENTER);
        naamSpelerB.setForeground(Constanten.BALK_TEKST_KLEUR);
        naamSpelerB.setBackground(Constanten.BALK_BG_KLEUR);
        naamSpelerB.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Constanten.BALK_STREEP_KLEUR, 2), new EmptyBorder(3, 0, 3, 0)));
        naamSpelerB.setAlignmentY(Box.CENTER_ALIGNMENT);
        naamSpelerBModule.setPreferredSize(naamInputSize);
        naamSpelerBModule.setMaximumSize(naamInputSize);


        menuBalk.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Constanten.BALK_STREEP_KLEUR));

        levelCB.setBorder(new EmptyBorder(0, 0, 0, 0));

        maxWindModule.setMaximumSize(new Dimension(200,100));
        maxWindInput.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        maxWindInput.setForeground(Constanten.BALK_TEKST_KLEUR);
        maxWindInput.setBackground(Constanten.BALK_BG_KLEUR);
        maxWindInput.setHorizontalAlignment(JTextField.CENTER);
        maxWindInput.setBorder(new EmptyBorder(4,0,4,0));

        startHPModule.setLayout(new BoxLayout(startHPModule, BoxLayout.PAGE_AXIS));
        startHPModule.setMaximumSize(new Dimension(200,100));
        startHPInput.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        startHPInput.setForeground(Constanten.BALK_TEKST_KLEUR);
        startHPInput.setBackground(Constanten.BALK_BG_KLEUR);
        startHPInput.setHorizontalAlignment(JTextField.CENTER);
        startHPInput.setBorder(new EmptyBorder(4,0,4,0));
    }

    private void addComponenten(){
        this.add(bovenBalk, BorderLayout.NORTH);
        this.add(middenBalk, BorderLayout.CENTER);
        this.add(onderBalk, BorderLayout.SOUTH);


        bovenBalk.add(levelModule, ModuleBalk.Kant.LINKS);
        bovenBalk.add(maxWindModule, ModuleBalk.Kant.RECHTS);
        bovenBalk.add(startHPModule, ModuleBalk.Kant.RECHTS);

        middenBalk.add(lefty);
        middenBalk.add(righty);

        terugKnopModule.add(terugButton);
        menuBalk.add(terugKnopModule, ModuleBalk.Kant.LINKS);

        naamSpelerAModule.add(naamSpelerA);
        naamSpelerBModule.add(naamSpelerB);

        naamBalk.add(naamSpelerAModule);
        Box.Filler tussenSpacingA = new Box.Filler(new Dimension(20, 1), new Dimension(100, 1), new Dimension(300, 1));
        Box.Filler tussenSpacingB = new Box.Filler(new Dimension(20, 1), new Dimension(100, 1), new Dimension(300, 1));
        naamBalk.add(tussenSpacingA);
        naamBalk.add(startButton);
        naamBalk.add(tussenSpacingB);
        naamBalk.add(naamSpelerBModule);

        onderBalk.add(naamBalk);
        onderBalk.add(menuBalk);
        onderBalk.add(Box.createHorizontalGlue());

        levelModule.setTopComponent(levelLabel);
        levelModule.setBottomComponent(levelCB);

        maxWindModule.setTopComponent(maxWindLabel);
        maxWindModule.setBottomComponent(maxWindInput);

        startHPModule.setTopComponent(startHPLabel);
        startHPModule.setBottomComponent(startHPInput);

    }

    private void addListeners(){
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stelSpelIn();
                gui.startSpel();
            }
        });

        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.openHoofdMenuPanel();
            }
        });
    }

    private void stelSpelIn(){ //geeft alle ingegeven opties en waarden door aan de controller
        try{
            spel.resetSpel();
            spel.setLevel((String) levelCB.getSelectedItem());
            try{
                spel.setMaxWind(Integer.parseInt(maxWindInput.getText()));
            } catch(NumberFormatException e){ throw new NumberFormatException("Max. wind moet een nummerieke waarde van 0 of groter zijn zijn."); }
            try{
                spel.setStartHP(Integer.parseInt(startHPInput.getText()));
            } catch(NumberFormatException e){ throw new NumberFormatException("Start-HP moet een nummerieke waarde van 1 of groter zijn."); }
            spel.maakSpelerA(naamSpelerA.getText());
            spel.maakSpelerB(naamSpelerB.getText());
        } catch(Exception e){
            gui.toonError(e);
        }
    }

    private void laadLevels(){
        Map<String, Level> levels = new HashMap<String, Level>();
        try{
            spel.laadLevels();
            levels.putAll(spel.getLevels());
        } catch (AngryTanksException e){
            gui.toonError(e);
        }

        levelCB.removeAllItems();
        for(String level : levels.keySet()){
            levelCB.addItem(level);
        }
    }
}
