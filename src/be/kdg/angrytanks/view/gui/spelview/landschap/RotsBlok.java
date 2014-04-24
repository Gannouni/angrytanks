package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */
/*
    Een RotsBlok is een GeranddeBlok met Afbeeldingen van rotsen.
 */
public class RotsBlok extends GeranddeBlok {
    private static final Afbeelding[] AFBEELDINGEN = {
        Constanten.ROTS_NO_EDGE, Constanten.ROTS_T, Constanten.ROTS_R, Constanten.ROTS_TR, Constanten.ROTS_B, Constanten.ROTS_TB, Constanten.ROTS_RB, Constanten.ROTS_TRB, Constanten.ROTS_L, Constanten.ROTS_TL, Constanten.ROTS_RL, Constanten.ROTS_TRL, Constanten.ROTS_BL, Constanten.ROTS_TBL, Constanten.ROTS_RBL, Constanten.ROTS_TRBL
    };

    public RotsBlok(){
        super(AFBEELDINGEN);
    }
}
