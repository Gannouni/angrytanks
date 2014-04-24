package be.kdg.angrytanks.view.gui;

import be.kdg.angrytanks.view.gui.constanten.*;
import be.kdg.angrytanks.view.gui.layout.ImagePanel;
import be.kdg.angrytanks.view.gui.layout.LayeredPanel;
import be.kdg.angrytanks.view.gui.layout.Module;
import be.kdg.angrytanks.view.gui.layout.PixelRoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 13/02/14
 */

/*
    Het HoofdMenuPanel is een JPanel met
        * een LayeredPanel achtergrond opgebouwd uit enkele overlappende ImagePanels
        * een PixelRoundedButton startKnop om de GUI naar het StartOptiesPanel te navigeren
        * een PixelRoundedButton helpKnop om de GUI een help dialog te laten openen
 */
public class HoofdMenuPanel extends JPanel {
    private GUI gui;

    private LayeredPanel achtergrond;
    private ImagePanel achtergrondTank;
    private ImagePanel achtergrondLandschap;
    private ImagePanel achtergrondLogo;
    private JPanel knoppen;
    private PixelRoundedButton startKnop;
    private PixelRoundedButton helpKnop;
    private Module startKnopModule;
    private Module helpKnopModule;

    public HoofdMenuPanel() {
        initComponenten();
        layoutComponenten();
        addComponenten();
        addListeners();
    }

    public void readyHoofdMenuPanel(){
        this.gui = (GUI) SwingUtilities.getAncestorOfClass(GUI.class, this);
    }

    private void initComponenten(){
        //achtergrond
        achtergrond = new LayeredPanel();
        achtergrondLandschap = new ImagePanel(Constanten.HOOFDMENU_BG_LANDSCHAP.getImage(), false, true, ImagePanel.Houd.X,  ImagePanel.KantX.MIDDLE, ImagePanel.KantY.BOTTOM);
        achtergrondTank = new ImagePanel(Constanten.HOOFDMENU_BG_TANK.getImage(), false, true, ImagePanel.Houd.NONE, ImagePanel.KantX.MIDDLE, ImagePanel.KantY.BOTTOM);
        achtergrondLogo = new ImagePanel(Constanten.HOOFDMENU_BG_LOGO.getImage(), false, true, ImagePanel.Houd.NONE, ImagePanel.KantX.LEFT, ImagePanel.KantY.TOP);

        //knoppen
        startKnop = new PixelRoundedButton("PLAY");
        helpKnop = new PixelRoundedButton("HELP");
        knoppen = new JPanel();
        startKnopModule = new Module();
        helpKnopModule = new Module();
    }

    private void layoutComponenten(){
        this.setLayout(new BorderLayout());

        //achtergrond
        achtergrond.setOpaque(true);
        achtergrond.setBackground(new Color(102, 139, 157));

        //knoppen
        knoppen.setLayout(new BoxLayout(knoppen, BoxLayout.LINE_AXIS));
        knoppen.setBackground(Constanten.BALK_BG_KLEUR);
        knoppen.setMinimumSize(new Dimension(1,100));
        knoppen.setPreferredSize(new Dimension(1,100));
    }

    private void addComponenten(){
        // achtergrond bouwen
        achtergrond.voegLaagToe(achtergrondLandschap);
        achtergrond.voegLaagToe(achtergrondTank);
        achtergrond.voegLaagToe(achtergrondLogo);
        achtergrond.bouwLagen();


        this.add(achtergrond, BorderLayout.CENTER);
        this.add(knoppen, BorderLayout.SOUTH);


        // knoppen toevoegen aan buttons
        startKnopModule.add(startKnop);
        helpKnopModule.add(helpKnop);

        knoppen.add(Box.createHorizontalGlue());
        knoppen.add(startKnopModule);
        knoppen.add(helpKnopModule);
        knoppen.add(Box.createHorizontalGlue());
    }

    private void addListeners(){
        startKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.openStartOptiesPanel();
            }
        });
        helpKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.openHelpDialog();
            }
        });
    }





}
