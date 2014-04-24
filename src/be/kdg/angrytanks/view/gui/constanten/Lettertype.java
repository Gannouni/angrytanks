package be.kdg.angrytanks.view.gui.constanten;

import java.awt.*;
import java.io.BufferedInputStream;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 16/02/14
 */

/*
    De Lettertype-klasse wordt gebruikt om een Font in te laden en te bewaren.
    Door deze klasse te gebruiken, kan de manier van inladen van fonts voor heel de GUI bepaald worden en makkelijk veranderd worden.
 */

public class Lettertype {
    private java.awt.Font font;

    public Lettertype(String pad, float grootte){
        try{
            BufferedInputStream fontInput = new BufferedInputStream(getClass().getResourceAsStream(pad));
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontInput);
            font = font.deriveFont(grootte);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public java.awt.Font getFont(){
        return font;
    }
}
