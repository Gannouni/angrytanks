package be.kdg.angrytanks.view.gui.constanten;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 10/02/14
 */

/*
    De Afbeelding-klasse wordt gebruikt om een afbeelding in te laden en te bewaren.
    Door deze klasse te gebruiken, kan de manier van inladen van afbeeldingen voor heel de GUI bepaald worden en makkelijk veranderd worden.
 */

public class Afbeelding {

    Image img;
    String pad;

    public Afbeelding(String pad){
        this.pad = pad;
        laadAfbeelding(pad);
    }

    public void laadAfbeelding(String pad){
        InputStream afbeeldingInput = getClass().getResourceAsStream(pad);
        try{
            img = ImageIO.read(afbeeldingInput);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Image getImage(){
        return img;
    }

    public String getPad(){
        return pad;
    }
}
