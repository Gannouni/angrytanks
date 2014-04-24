package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/02/14
 */
/*
    TankB is een Tank met Afbeeldingen van een linksgeoriënteerde tank
 */
public class TankB extends Tank {
    private static final Afbeelding[] TANK_B_AFBEELDINGEN = {
        Constanten.TANK_B_00, Constanten.TANK_B_10, Constanten.TANK_B_20, Constanten.TANK_B_30, Constanten.TANK_B_40, Constanten.TANK_B_50, Constanten.TANK_B_60, Constanten.TANK_B_70, Constanten.TANK_B_80, Constanten.TANK_B_90, Constanten.TANK_B_SCHROOT
    };

    public TankB(){
        super(TANK_B_AFBEELDINGEN);
    }

}
