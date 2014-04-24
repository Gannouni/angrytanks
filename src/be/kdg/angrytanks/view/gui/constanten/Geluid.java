package be.kdg.angrytanks.view.gui.constanten;


import javax.sound.sampled.*;
import java.io.*;
/**
 * Alexander Gannouni & Bert Willekens
 * Date: 02/03/14
 */

/*
    De Geluid-klasse wordt gebruikt om een geluid in te laden en af te spelen.
    Door deze klasse te gebruiken, kan de manier van inladen van geluiden voor heel de GUI bepaald worden en makkelijk veranderd worden.
 */

public class Geluid {

    String pad;
    Clip clip;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;

    public Geluid(String pad){
        this.pad = pad;
    }

    public void speelAf(){
        try{
            stream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(pad)));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loop(){
        try{
            stream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(pad)));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void stop(){
        clip.stop();
    }


}
