package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.dom.veld.Positie;
import java.util.HashMap;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 26/02/14
 */
/*
    Tanks is een BlokGridPanel met een TankA en een TankB
 */
public class Tanks extends BlokGridPanel {
    TankA tankA;
    TankB tankB;

    public Tanks(Positie tankAPos, Positie tankBPos){
        super(new HashMap<Positie, Blok>(), false);
        tankA = new TankA();
        tankB = new TankB();
        setTanks(tankAPos, tankBPos);
    }

    public Tanks(){
        this(new Positie(1,1), new Positie (5,1));
    }

    public void setTanks(Positie tankAPos, Positie tankBPos){
        HashMap<Positie, Blok> tanks = new HashMap<Positie, Blok>();
        tanks.put(new Positie(tankAPos.getX() - 1, tankAPos.getY()), tankA);
        tanks.put(new Positie(tankBPos.getX() - 1, tankBPos.getY()), tankB);
        super.setBlokken(tanks);
    }

    public void setTankALoopGraden(int graden){
        tankA.stelLoopGradenIn(graden);
        repaint();
    }

    public void setTankBLoopGraden(int graden){
        tankB.stelLoopGradenIn(graden);
        repaint();
    }

    public void setTankASchroot(boolean schroot){
        tankA.setSchroot(schroot);

    }

    public void setTankBSchroot(boolean schroot){
        tankB.setSchroot(schroot);
    }

    public void resetTanks(){
        tankA.setSchroot(false);
        tankB.setSchroot(false);
        tankA.stelLoopGradenIn(0);
        tankB.stelLoopGradenIn(0);
    }
}
