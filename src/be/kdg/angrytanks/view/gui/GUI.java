package be.kdg.angrytanks.view.gui;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.spelview.SpelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
    De GUI is een JFrame.
    In de GUI bevindt zich een JPanel met CardLayout, guiCardLayout.
        De guiCardLayout bevat drie JPanels: hoofdMenuPanel, startOptiesPanel en spelPanel
        Deze worden op aanvraag uitgewisseld.
    De GUI bevat ook:
        een SplashScherm dat getoond kan worden bij het opstarten van het spel en op aanvraag
        de mogelijkheid om een help-dialog te tonen
        de mogelijkheid om het spel te starten en het spel te stoppen

 */
public class GUI extends JFrame {
    final static int MIN_BREEDTE = 800;
    final static int MIN_HOOGTE = 600;

    //id's voor cards in cardlayout
    final static String HOOFDMENUPANEL_ID = "HoofdMenuPanel";
    final static String STARTOPTIESPANEL_ID = "StartOptiesPanel";
    final static String SPELPANEL_ID = "SpelPanel";

    private Spel spel; //het spel!

    //panels
    private JPanel guiCardLayout;
    private HoofdMenuPanel hoofdMenuPanel;
    private StartOptiesPanel startOptiesPanel;
    private SpelPanel spelPanel;

    //extra
    private SplashScherm splash;
    private MenuBar menuBar;
    private Muziek muziek;


    public GUI() {
        this.setTitle("Angry Tanks"); //Titel instellen

        muziek = new Muziek(); //Muziek aanmaken

        //Spel aanmaken
        try{
            spel = new Spel();
        } catch(AngryTanksException e){
            toonError(e);
        }

        //Nimbus L&F instellen
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    toonError(e);
                }
            }
        }

        //componenten in orde brengen
        initComponenten();
        layoutComponenten();
        addComponenten();
        addListeners();

        //Panels klaarmaken
        hoofdMenuPanel.readyHoofdMenuPanel();
        startOptiesPanel.readyStartOptiesPanel();
        spelPanel.readySpelPanel();
        menuBar.readyMenuBar();

        //Splash tonen
        final GUI gui = this;
        splash.toonSplashTijd(5000, new java.util.concurrent.Callable() {
            @Override
            public Object call() throws Exception { //Wanneer splashtijd gedaan is:
                gui.openHoofdMenuPanel(); //hoofdmenupanel openen
                gui.setVisible(true); //GUI tonen
                return null;
            }
        });

    }

    private void initComponenten(){
        splash = new SplashScherm();
        menuBar = new MenuBar(spel);

        //cardlayout
        guiCardLayout = new JPanel(new CardLayout());
        hoofdMenuPanel = new HoofdMenuPanel();
        startOptiesPanel = new StartOptiesPanel(spel);
        spelPanel = new SpelPanel(spel);
    }

    private void layoutComponenten(){
        this.setBounds(100, 100, MIN_BREEDTE, MIN_HOOGTE);
        this.setMinimumSize(new Dimension(MIN_BREEDTE, MIN_HOOGTE));
    }

    private void addComponenten(){
        this.setJMenuBar(menuBar);
        this.add(guiCardLayout);

        guiCardLayout.add(hoofdMenuPanel, HOOFDMENUPANEL_ID);
        guiCardLayout.add(startOptiesPanel, STARTOPTIESPANEL_ID);
        guiCardLayout.add(spelPanel, SPELPANEL_ID);
    }

    private void addListeners(){
        //als de window gesloten wordt en het spel is nog bezig, wordt eerst om bevestiging gevraagd
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                exitMetBevestiging();
            }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
    }



    //managen van getoonde panels en cardLayout

    public void openHoofdMenuPanel(){ //opent het hoofdMenuPanel
        ((CardLayout) guiCardLayout.getLayout()).show(guiCardLayout, HOOFDMENUPANEL_ID); //toon panel in cardlayout
        muziek.speelMuziek(Muziek.Soundtracks.MenuMuziek); //speel bijhorende muziek
        menuBar.updateMenu(); //update het menu
    }

    public void openStartOptiesPanel(){ //opent het startOptiesPanel
        ((CardLayout) guiCardLayout.getLayout()).show(guiCardLayout, STARTOPTIESPANEL_ID); //toon panel in cardlayout
        muziek.speelMuziek(Muziek.Soundtracks.MenuMuziek); //speel bijhorende muziek
        menuBar.updateMenu(); //update het menu
    }

    public void openSpelPanel(){ //opent het spelPanel
        ((CardLayout) guiCardLayout.getLayout()).show(guiCardLayout, SPELPANEL_ID); //toon panel in cardlayout
        muziek.speelMuziek(Muziek.Soundtracks.SpelMuziek); //speel bijhorende muziek
        menuBar.updateMenu(); //update het menu
    }

    public void openHelpDialog(){ //opent de helpdialog
        JOptionPane.showMessageDialog(this,Constanten.HELPTEKST.getTekst(),"Help", JOptionPane.CLOSED_OPTION);
    }

    public void openInfo(){ //toont de splash met info
        splash.toonSplash();
    }



    //spel starten en stoppen

    public void startSpel(){ //start het spel en open het spelpanel
        try{
            spel.startSpel();
            openSpelPanel();
            spelPanel.resetSpelPanel();
        } catch (AngryTanksException e){
            toonError(e);
        }

    }

    public void stopSpel(){ //stopt het spel en opent hoofdMenuPanel (als spel nog bezig is, eerst bevestiging via dialog)
        if(spel.isSpelBezig() && toonStopDialog() == JOptionPane.NO_OPTION) return;

        try{
            spel.resetSpel();
        } catch(Exception e){
            toonError(e);
        }
        openHoofdMenuPanel();
    }



    //hulpmethodes

    public int toonStopDialog(){ //vraagt of je zeker bent dat je wilt stoppen
        return JOptionPane.showConfirmDialog(this, "Wilt u stoppen?", "Opgepast", JOptionPane.YES_NO_OPTION);
    }

    public void exitMetBevestiging(){ //zal het programma afsluiten (als spel nog bezig is, eerst bevestiging via dialog)
        if(spel.isSpelBezig() && toonStopDialog() == JOptionPane.NO_OPTION) return; //als het spel bezig is, toon een stopDialog
        System.exit(0);
    }

    public void toonError(Throwable t){ //toont de meegegeven Throwable in een errormessage
        JOptionPane.showMessageDialog(this, t.getMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
    }
}
