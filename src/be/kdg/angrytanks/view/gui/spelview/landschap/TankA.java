package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/02/14
 */
/*
    TankA is een Tank met Afbeeldingen van een rechtsgeoriënteerde tank
 */
public class TankA extends Tank {
    public static final Afbeelding[] TANK_A_AFBEELDINGEN = {
        Constanten.TANK_A_00, Constanten.TANK_A_10, Constanten.TANK_A_20, Constanten.TANK_A_30, Constanten.TANK_A_40, Constanten.TANK_A_50, Constanten.TANK_A_60, Constanten.TANK_A_70, Constanten.TANK_A_80, Constanten.TANK_A_90, Constanten.TANK_A_SCHROOT
    };

    public TankA(){
        super(TANK_A_AFBEELDINGEN);
    }


}
