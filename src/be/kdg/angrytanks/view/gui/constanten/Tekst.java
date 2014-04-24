package be.kdg.angrytanks.view.gui.constanten;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/03/14
 */

/*
    De Tekst-klasse wordt gebruikt om een tekst als String in te laden en te bewaren.
    Door deze klasse te gebruiken, kan de manier van inladen van tekst voor heel de GUI bepaald worden en makkelijk veranderd worden.
 */

public class Tekst {
    private String pad;
    private String tekst;

    public Tekst(String pad){
        this.pad = pad;
        laadTekst(pad);
    }

    public void laadTekst(String pad){
        InputStream stream = getClass().getResourceAsStream(pad);
        tekst = new Scanner(stream).useDelimiter("\\A").next();
    }

    public String getTekst(){
        return tekst;
    }

}
