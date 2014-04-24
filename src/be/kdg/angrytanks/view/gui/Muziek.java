package be.kdg.angrytanks.view.gui;

import be.kdg.angrytanks.view.gui.constanten.Constanten;
import be.kdg.angrytanks.view.gui.constanten.Geluid;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/03/14
 */

/**
    Muziek bevat een Soundtracks-enum met verschillende soundtracks en hun Geluid-objecten.
    Uit de enum kan een soundtrack gekozen worden om af te spelen.
    Er kan maar één soundtrack tegelijk afgespeeld worden.
 */
public class Muziek {
    public enum Soundtracks{
        MenuMuziek(Constanten.MENU_MUZIEK),SpelMuziek(Constanten.INGAME_MUZIEK);

        private Geluid geluid;
        Soundtracks(Geluid geluid){
            this.geluid = geluid;
        }

        public Geluid getGeluid(){
            return geluid;
        }
    }

    private Soundtracks huidigeSoundtrack;

    public Muziek(Soundtracks huidigeSoundtrack){
        speelMuziek(huidigeSoundtrack);
    }

    public Muziek(){
        this(null);
    }

    public void speelMuziek(Soundtracks huidigeSoundtrack){
        if(this.huidigeSoundtrack != huidigeSoundtrack){
            if(this.huidigeSoundtrack != null) this.huidigeSoundtrack.getGeluid().stop();
            this.huidigeSoundtrack = huidigeSoundtrack;
            if(this.huidigeSoundtrack != null) huidigeSoundtrack.getGeluid().loop();
        }
    }
}
