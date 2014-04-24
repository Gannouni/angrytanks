package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.veld.Positie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
    Een Baan is een BlokGridPanel.
    Een Baan kan de baan van een schot tekenen aan de hand van een ArrayList.
    Een Baan kan de baan ook animeren met behulp van een Timer, en een resultaat-methode uitvoeren wanneer de animatie gedaan is.
 */

public class Baan extends BlokGridPanel{
    private Timer timer;
    private Callable onCompleteFunc;
    private int snelheid = 15;
    private ArrayList<Positie> baanPad;
    private HashMap<Positie, Blok> afgelegdeBaan;
    private int baanTeller = 0;

    public Baan(ArrayList<Positie> baanPad){
        super(new HashMap<Positie, Blok>(), true);
        afgelegdeBaan = new HashMap<Positie, Blok>();
        onCompleteFunc = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
        setBaan(baanPad);
        this.setOpaque(false);

        timer = new Timer(1000 / snelheid, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerActie();
            }
        });
    }

    public Baan(){
        this(new ArrayList<Positie>());
    }

    public void setBaan(ArrayList<Positie> baanPad){
        this.baanPad = baanPad;
        resetAfgelegdeBaan();
    }

    public void setSnelheid(int snelheid){
        this.snelheid = snelheid;
    }

    public void resetAfgelegdeBaan(){
        baanTeller = 0;
        afgelegdeBaan.clear();
        super.setBlokken(afgelegdeBaan);
    }

    public void toonAfgelegdeBaanVolledig(){
        afgelegdeBaan.clear();
        for(int i = 0; i < baanPad.size(); i++){
            if(i == baanPad.size() - 1)
                afgelegdeBaan.put(baanPad.get(i), new ExplosieBlok());
            else
                afgelegdeBaan.put(baanPad.get(i), new PoefBlok());
        }
        super.setBlokken(afgelegdeBaan);
    }

    public void volgendeStapAfgelegdeBaan(){
        if(baanTeller < baanPad.size()){
            if(baanTeller > 0){
                afgelegdeBaan.put(baanPad.get(baanTeller - 1), new PoefBlok());
            }
            if(afgelegdeBaan.size() == baanPad.size() - 1){
                afgelegdeBaan.put(baanPad.get(baanTeller), new ExplosieBlok());
            } else{
                afgelegdeBaan.put(baanPad.get(baanTeller), new KogelBlok());
            }
            baanTeller++;
        }
        super.setBlokken(afgelegdeBaan);
    }

    public void animeer(Callable onCompleteFunc){
        this.onCompleteFunc = onCompleteFunc;

        timer.stop();
        resetAfgelegdeBaan();
        timer.setDelay(1000 / snelheid);
        timer.start();
    }

    private void timerActie(){
        if(baanTeller < baanPad.size()){
            volgendeStapAfgelegdeBaan();
            if(baanTeller == baanPad.size()){
                try{
                    onCompleteFunc.call(); //moet gecalld worden op de voorlaatste stap van de animatie
                } catch(Exception e){
                    e.printStackTrace();
                }
                timer.stop();
            }
            repaint();
        }
    }

}
