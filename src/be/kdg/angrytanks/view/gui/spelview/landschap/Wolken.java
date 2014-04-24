package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.dom.veld.Positie;
import be.kdg.angrytanks.view.gui.constanten.Constanten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
    Wolken is een GeranddeBlokGridPanel dat zichzelf kan vullen met gegenereerde wolken en die wolken kan animeren op basis van de ingestelde wind.
 */
public class Wolken extends GeranddeBlokGridPanel {
    private static final int MAXBREEDTEWOLK = 8;
    private static final int MINBREEDTEWOLK = 4;
    private static final int MAXHOOGTEWOLK = 4;
    private static final int MINHOOGTEWOLK = 2;
    private static final int MAXSPACING = 5;

    private int wind = 0;
    private Timer timer;
    private Random random;

    public Wolken(){
        setHerhaalX(true);
        this.random = new Random();
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerActie();
            }
        });
    }

    public void setWind(int wind){
        this.wind = wind;
        if(wind == 0) stopWaaien();
        else{
            timer.setDelay(10 + 200 / Math.abs(wind));
            startWaaien();
        }

    }

    public void startWaaien(){
        timer.start();
    }

    public void stopWaaien(){
        timer.stop();
    }

    private void waaiWolken(int invloed){
        setOffsetX(getOffsetX() + invloed);
        repaint();
    }

    private void timerActie(){
        int operator = 0;
        if(wind != 0) operator = wind / Math.abs(wind); //1 of -1
        waaiWolken(Constanten.EENHEIDGROOTTE * operator);
    }

    public void genereerWolken(){ //magische methode om wolken te genereren op basis van de breedte en hoogte van de window
        HashMap<Positie,Blok> wolkBlokken = new HashMap<Positie, Blok>();

        int windowBreedte = (this.getWidth() - (this.getWidth() % Constanten.BLOKGROOTTE)) / Constanten.BLOKGROOTTE + 2;
        int windowHoogte = (this.getHeight() - (this.getHeight() % Constanten.BLOKGROOTTE)) / Constanten.BLOKGROOTTE;


        int breedteOver = windowBreedte;
        while(breedteOver >= MINBREEDTEWOLK + MAXSPACING){
            int maxBreedte = MAXBREEDTEWOLK;
            if(breedteOver < MAXBREEDTEWOLK + MAXSPACING) maxBreedte = breedteOver - MAXSPACING;

            int breedte = random.nextInt(maxBreedte - MINBREEDTEWOLK + 1) + MINBREEDTEWOLK;
            int hoogte = random.nextInt(MAXHOOGTEWOLK - MINHOOGTEWOLK + 1) + MINHOOGTEWOLK;


            int x = windowBreedte - breedteOver + random.nextInt(MAXSPACING);
            int y = windowHoogte / 4 + random.nextInt(windowHoogte / 4 * 3 - hoogte);

            int basisLijnYStart = (int)Math.round((double)(hoogte - 1) / 5 * 1);
            int basisLijnYStop = (int)Math.round((double)(hoogte - 1) / 5 * 2);

            for(int blokY = 0; blokY < hoogte; blokY++){ //complex stukje om wolk-achtige vormen te verkrijgen
                int XStart;
                int XEnd;

                int minXStart;
                int maxXStart;
                int minXEnd;
                int maxXEnd;


                if(blokY >= basisLijnYStart && blokY <= basisLijnYStop){
                    minXStart = 0;
                    maxXStart = (int)Math.round((double)(breedte - 1) / 7 * 1);
                    minXEnd = breedte - (int)Math.round((double)(breedte - 1) / 9 * 1);
                    maxXEnd = breedte;
                } else if(blokY < basisLijnYStart){
                    minXStart = (int)Math.round((double)(breedte - 1) / 7 * (1 + basisLijnYStart - blokY));
                    maxXStart = (int)Math.round((double)(breedte - 1) / 7 * (1 + basisLijnYStart - blokY) * 2);
                    minXEnd = breedte - (int)Math.round((double)(breedte - 1) / 7 * (1 + basisLijnYStart - blokY) * 2);
                    maxXEnd = breedte - (int)Math.round((double)(breedte - 1) / 7 * (1 + basisLijnYStart - blokY));
                } else{
                    minXStart = (int)Math.round((double)(breedte - 1) / 7 * (1 + blokY - basisLijnYStop));
                    maxXStart = (int)Math.round((double)(breedte - 1) / 7 * (1 + blokY - basisLijnYStop) * 2);
                    minXEnd = breedte - (int)Math.round((double)(breedte - 1) / 7 * (1 + blokY - basisLijnYStop) * 2);
                    maxXEnd = breedte - (int)Math.round((double)(breedte - 1) / 7 * (1 + blokY - basisLijnYStop));
                }

                XStart = random.nextInt(maxXStart - minXStart + 1) + minXStart;
                XEnd = random.nextInt(maxXEnd - minXEnd + 1) + minXEnd;

                for(int blokX = 0; blokX < breedte; blokX++){
                    if(blokX >= XStart && blokX <= XEnd){
                        wolkBlokken.put(new Positie(blokX + x, blokY + y), new WolkBlok());
                    }
                }
            }

            breedteOver = windowBreedte - x - breedte;
        }

        setBlokken(wolkBlokken); //wolkblokken instellen in BlokGridPanel
        resetRanden(); //randen opnieuw berekenen in GeranddeBlokGridPanel
    }
}
