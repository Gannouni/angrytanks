package be.kdg.angrytanks.view.gui;

import be.kdg.angrytanks.controller.Spel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 05/03/14
 */

/**
    De MenuBar is een JMenuBar die gevuld wordt met enkele MenuItems met handige functies voor Angry Tanks.
 */
public class MenuBar extends JMenuBar {
    private GUI gui;
    private Spel spel;
    private JMenu angryTanksMenu;
    private JMenu spelMenu;

    private JMenuItem nieuwSpel;
    private JMenuItem beginOpnieuw;
    private JMenuItem stoppen;
    private JMenuItem info;
    private JMenuItem help;
    private JMenuItem exit;

    public MenuBar(Spel spel)
    {
        this.spel = spel;
        initComponents();
        addComponents();
        addListeners();
    }

    public void readyMenuBar(){
        this.gui = (GUI) SwingUtilities.getAncestorOfClass(GUI.class, this);
    }

    private void initComponents(){
        angryTanksMenu = new JMenu("Angry Tanks");
        spelMenu = new JMenu("Spel");
        nieuwSpel = new JMenuItem("Nieuw Spel");
        beginOpnieuw = new JMenuItem("Begin opnieuw");
        stoppen = new JMenuItem("Stoppen");
        info = new JMenuItem("Info");
        help = new JMenuItem("Help");
        exit = new JMenuItem("Exit");

    }

    private void addComponents(){
        spelMenu.add(nieuwSpel);
        spelMenu.add(beginOpnieuw);
        spelMenu.add(stoppen);
        angryTanksMenu.add(info);
        angryTanksMenu.add(help);
        angryTanksMenu.add(exit);
        this.add(angryTanksMenu);
        this.add(spelMenu);
    }

    private void addListeners() {
        nieuwSpel.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) { gui.openStartOptiesPanel(); }
        });
        beginOpnieuw.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) { gui.startSpel(); }
        });
        stoppen.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e) { gui.stopSpel(); }
        });
        info.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e) { gui.openInfo(); }
        });
        help.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) { gui.openHelpDialog(); }
        });
        exit.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e) { gui.exitMetBevestiging(); }
        });
    }

    public void updateMenu(){ //enabled of disabled elementen in de menubar adhv. de status van het spel
        beginOpnieuw.setEnabled(false);
        if(spel.isSpelStartKlaar()){ //als het spel klaar is om te starten
            beginOpnieuw.setEnabled(true); //kan het spel opnieuw begonnen worden
        }
    }
}
