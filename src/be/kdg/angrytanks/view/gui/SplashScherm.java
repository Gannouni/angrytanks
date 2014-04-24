package be.kdg.angrytanks.view.gui;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/03/14
 */

/**
    Het SplashScherm is een JWindow met een JLabel met een ImageIcon.
    Er zijn methodes beschikbaar om
        het SplashScherm een bepaald aantal milliseconden te tonen (en een resultaat-methode uit te voeren),
        het SplashScherm te tonen tot er op de JLabel geklikt wordt
 */
public class SplashScherm extends JWindow
{
    private JLabel afbeelding;
    private SplashScherm splash;
    private Timer timer;
    private java.util.concurrent.Callable onCompleteFunc;

    public SplashScherm(){
        splash = this;
        afbeelding = new JLabel(new ImageIcon(Constanten.SPLASH_AFBEELDING.getImage()));
        getContentPane().add(afbeelding, BorderLayout.CENTER);
        pack();
        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = afbeelding.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2),
                screenSize.height/2 - (labelSize.height/2));
    }

    public void toonSplashTijd(int millis, java.util.concurrent.Callable onCompleteFunc){ //toont de SplashScreen voor een meegegeven aantal milliseconden, en voert nadien een resultaat-methode uit
        setVisible(true);
        this.onCompleteFunc = onCompleteFunc;
        this.timer = new Timer(millis, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eindSplashTijd();
            }
        });
        this.timer.start();
    }

    public void toonSplashTijd(int millis){ //toont de SplashScreen voor een meegegeven aantal milliseconden
        toonSplashTijd(millis, new java.util.concurrent.Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }

    private void eindSplashTijd(){ //stopt de timer, verbergt de SplashScreen terug en roept de resultaat-methode aan
        timer.stop();
        setVisible(false);
        try{
            onCompleteFunc.call();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void toonSplash(){ //toont de SplashScreen totdat er op geklikt wordt
        setVisible(true);

        splash.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                splash.setVisible(false);
                splash.removeMouseListener(this);
            }

            @Override public void mousePressed(MouseEvent e) {}

            @Override public void mouseReleased(MouseEvent e) {}

            @Override public void mouseEntered(MouseEvent e) {}

            @Override public void mouseExited(MouseEvent e) {}
        });
    }
}