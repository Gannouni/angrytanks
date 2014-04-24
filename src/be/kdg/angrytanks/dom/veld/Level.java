package be.kdg.angrytanks.dom.veld;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 08/02/14
 */

/* Een Level is een blauwdruk voor een speelveld.
   Het beheert een HashMap die aan Positie-objecten (met coordinaten) een Onderdeel koppelt.
   Omdat een HashMap wordt gebruikt, kunnen er nooit twee onderdelen op dezelfde positie staan.
   Ook de hoogte en de breedte van het level worden bewaard.
   De Speelveld-klasse gebruikt een Level-object als referentie om een veld op te bouwen.

   Een Level-object wordt aangemaakt door de FileLevelLezer-klasse. (of evt. ook door een interne level-editor in het programma)
*/

public class Level{
    private int hoogte = 0;
    private int breedte = 0;

    private HashMap<Positie, Onderdeel> opbouw;

    public Level(){
        opbouw = new HashMap<Positie, Onderdeel>();
    }


    public Map<Positie, Onderdeel> getOpbouw(){
        return Collections.unmodifiableMap(opbouw);
    }

    public void addOnderdeel(Positie positie, Onderdeel onderdeel){
        opbouw.put(new Positie(positie.getX(), positie.getY()), onderdeel);
    }

    public void setHoogte(int hoogte){
        this.hoogte = hoogte;
    }

    public void setBreedte(int breedte){
        this.breedte = breedte;
    }

    public int getHoogte(){
        return hoogte;
    }

    public int getBreedte(){
        return breedte;
    }


}
