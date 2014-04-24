package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.constanten.Afbeelding;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */
/*
    Een WolkBlok is een GeranddeBlok met Afbeeldingen van wolken.
 */
public class WolkBlok extends GeranddeBlok {
    private static final Afbeelding[] AFBEELDINGEN = {
            Constanten.WOLK_NO_EDGE, Constanten.WOLK_T, Constanten.WOLK_R, Constanten.WOLK_TR, Constanten.WOLK_B, Constanten.WOLK_TB, Constanten.WOLK_RB, Constanten.WOLK_TRB, Constanten.WOLK_L, Constanten.WOLK_TL, Constanten.WOLK_RL, Constanten.WOLK_TRL, Constanten.WOLK_BL, Constanten.WOLK_TBL, Constanten.WOLK_RBL, Constanten.WOLK_TRBL
    };

    public WolkBlok(){
        super(AFBEELDINGEN);
    }
}
