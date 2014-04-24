package be.kdg.angrytanks.dom.veld;

import be.kdg.angrytanks.dom.exceptions.AngryTanksException;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 07/02/14
 */

/*
    De klasse Speelveld brengt alle spel-elementen samen en beheert ze.
 */

public class Speelveld {

    private Level level;

    private int extensieTop = 0; //hoeveel het speelveld bovenaan is geextend
    private int extensieRight = 0; //hoeveel het speelveld rechts is geextend
    private int extensieBottom = 0; //hoeveel het speelveld onderaan is geextend
    private int extensieLeft = 0; //hoeveel het speelveld links is geextend
    private Obstructie obstructie;
    private Schutter schutterA;
    private Positie schutterAPositie;
    private HashSet<Positie> schutterAZone;
    private Schutter schutterB;
    private Positie schutterBPositie;
    private HashSet<Positie> schutterBZone;
    private Wind wind;

    private Schot laatsteSchot;
    private boolean zelfGeraakt;
    private boolean vijandGeraakt;
    private boolean obstructieGeraakt;

    public void bouwSpeelveld() throws AngryTanksException { //bouwt het speelveld op basis van level
        if(!isSpeelveldBouwKlaar()) throw new AngryTanksException("Speelveld kan niet gebouwd worden. Stel eerst een level in.");

        laatsteSchot = new Schot(Orientatie.RECHTS, 0, 0, new Positie(0,0), 0, new HashMap<String, HashSet<Positie>>()); //dummy schot
        schutterAPositie = null;
        schutterBPositie = null;
        obstructie = new Obstructie();
        schutterAZone = new HashSet<Positie>();
        schutterBZone = new HashSet<Positie>();

        HashMap<Positie, Onderdeel> opbouw = new HashMap<Positie, Onderdeel>(level.getOpbouw());
        for(Positie p : opbouw.keySet()){
            if(opbouw.get(p) == Onderdeel.OBSTRUCTIE){
                obstructie.add(new Positie(p.getX(), p.getY()));
            } else if(opbouw.get(p) == Onderdeel.SCHUTTER_A){
                schutterAPositie = new Positie(p.getX(), p.getY());
                schutterAZone.add(new Positie(p.getX(), p.getY()));
            } else if(opbouw.get(p) == Onderdeel.SCHUTTER_B) {
                schutterBPositie = new Positie(p.getX(), p.getY());
                schutterBZone.add(new Positie(p.getX(), p.getY()));
            } else if(opbouw.get(p) == Onderdeel.SCHUTTER_A_ZONE){
                schutterAZone.add(new Positie(p.getX(), p.getY()));
            } else if(opbouw.get(p) == Onderdeel.SCHUTTER_B_ZONE){
                schutterBZone.add(new Positie(p.getX(), p.getY()));
            }
        }
    }


    public void schiet(Schutter schutter, double kracht, double hoek) throws AngryTanksException { //maakt een Schot aan, en kijkt na of dit botst met de obstructie of met de tegenstander.
        if(!isSpeelveldKlaar()) throw new AngryTanksException("Speelveld is niet klaar om te spelen.");
        if(schutter != schutterA && schutter != schutterB) throw new AngryTanksException("Schutter in parameter is geen deel van het veld.");

        //juiste posities en zones kiezen
        Positie oorsprong;
        HashSet<Positie> zelfZone;
        HashSet<Positie> vijandZone;
        if(schutter == schutterA){ oorsprong = schutterAPositie; zelfZone = schutterAZone; vijandZone = schutterBZone;}
        else { oorsprong = schutterBPositie; zelfZone = schutterBZone; vijandZone = schutterAZone;}

        //zones verenigen in HashMap
        HashMap<String, HashSet<Positie>> elementen = new HashMap<String, HashSet<Positie>>();
        elementen.put("obstructie", obstructie);
        elementen.put("vijandZone", vijandZone);
        elementen.put("zelfZone", zelfZone);

        //schot maken
        laatsteSchot = new Schot(schutter.getOrientatie(), kracht, hoek, oorsprong, wind.getWind(), elementen);

        //controle!
        zelfGeraakt = false;
        vijandGeraakt = false;
        obstructieGeraakt = false;
        if(laatsteSchot.isIetsGeraakt()){
            if(laatsteSchot.getGeraaktId().equals("obstructie")){ //is de obstructie geraakt?
                obstructieGeraakt = true;
                obstructie.remove(laatsteSchot.getRaakpunt()); //verwijder geraakte positie uit obstructie

            } else if(laatsteSchot.getGeraaktId().equals("vijandZone")){ //is de vijand geraakt?
                vijandGeraakt = true;
            } else if(laatsteSchot.getGeraaktId().equals("zelfZone")){ //is de speler zelf geraakt?
                zelfGeraakt = true;
            }
        }
    }


    //setters:

    public void setWindRandom(){
        wind.setWindRandom();
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public void setSchutterA(Schutter schutterA){
        this.schutterA = schutterA;
    }

    public void setSchutterB(Schutter schutterB){
        this.schutterB = schutterB;
    }

    public void setMaxWind(int maxWind){
        this.wind = new Wind(maxWind);
    }

    public void setExtensieTop(int extensieTop){
        if(this.extensieTop < extensieTop){ //als argument extensieTop groter is dan oude extensieTop
            int prevExtensieTop = this.extensieTop; //oude extensieTop bijhouden
            this.extensieTop = extensieTop; //nieuwe extensieTop instellen
            //obstructie extenden vanaf bovenste rand tot nieuwe extensieTop
            obstructie.extendY(level.getHoogte() + prevExtensieTop, level.getHoogte() + this.extensieTop, 0 - extensieLeft, level.getBreedte() + extensieRight);

        }
    }

    public void setExtensieRight(int extensieRight){
        if(this.extensieRight < extensieRight){ //als argument extensieRight groter is dan oude extensieRight
            int prevExtensieRight = this.extensieRight; //oude extensieRight bijhouden
            this.extensieRight = extensieRight; //nieuwe extensieRight instellen
            //obstructie extenden vanaf meest rechtse rand tot nieuwe extensieRight
            obstructie.extendX(level.getBreedte() + prevExtensieRight, level.getBreedte() + this.extensieRight, 0 - extensieBottom, level.getHoogte() + extensieTop);

        }
    }

    public void setExtensieBottom(int extensieBottom){
        if(this.extensieBottom < extensieBottom){ //als argument extensieBottom groter is dan oude extensieBottom
            int prevExtensieBottom = this.extensieBottom; //oude extensieBottom bijhouden
            this.extensieBottom = extensieBottom; //nieuwe extensieBottom instellen
            //obstructie extenden vanaf onderste rand tot nieuwe extensieBottom
            obstructie.extendY(0 - prevExtensieBottom, 0 - this.extensieBottom, 0 - extensieLeft, level.getBreedte() + extensieRight);
        }
    }

    public void setExtensieLeft(int extensieLeft){
        if(this.extensieLeft < extensieLeft){ //als argument extensieLeft groter is dan oude extensieLeft
            int prevExtensieLeft = this.extensieLeft; //oude extensieLeft bijhouden
            this.extensieLeft = extensieLeft; //nieuwe extensieLeft instellen
            //obstructie extenden vanaf meest linkse rand tot nieuwe extensieLeft
            obstructie.extendX(0 - prevExtensieLeft, 0 - this.extensieLeft, 0 - extensieBottom, level.getHoogte() + extensieTop);
        }
    }


    //getters:

    public boolean isSpeelveldBouwKlaar(){ //kijkt of het speelveld klaar is om gebouwd te worden
        if(level == null) return false;
        return true;
    }

    public boolean isSpeelveldKlaar(){ //kijkt of het speelveld klaar is om in te spelen
        if( obstructie == null
                || schutterAPositie == null
                || schutterAZone == null
                || schutterBPositie == null
                || schutterBZone == null
                || wind == null
                || schutterA == null
                || schutterB == null)
            return false;

        return true;
    }

    public boolean isVijandGeraakt(){
        return vijandGeraakt;
    }

    public boolean isZelfGeraakt(){
        return zelfGeraakt;
    }

    public boolean isObstructieGeraakt(){
        return obstructieGeraakt;
    }

    public Level getLevel(){
        return level;
    }

    public Obstructie getObstructie() {
        return obstructie;
    }

    public Schutter getSchutterA() {
        return schutterA;
    }

    public Positie getSchutterAPositie() {
        return schutterAPositie;
    }

    public Schutter getSchutterB() {
        return schutterB;
    }

    public Positie getSchutterBPositie() {
        return schutterBPositie;
    }

    public Wind getWind() {
        return wind;
    }

    public Schot getLaatsteSchot() {
        return laatsteSchot;
    }

    public int getExtensieTop(){
        return extensieTop;
    }

    public int getExtensieRight(){
        return extensieRight;
    }

    public int getExtensieBottom(){
        return extensieBottom;
    }

    public int getExtensieLeft(){
        return extensieLeft;
    }

    public int getStdHoogte() {
        return level.getHoogte();
    }

    public int getStdBreedte() { return level.getBreedte(); }

}
