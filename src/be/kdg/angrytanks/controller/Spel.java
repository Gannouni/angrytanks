package be.kdg.angrytanks.controller;

import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.dom.io.FileLevelLezer;
import be.kdg.angrytanks.dom.io.LevelLezer;
import be.kdg.angrytanks.dom.veld.*;
import be.kdg.angrytanks.dom.spelers.Mens;
import be.kdg.angrytanks.dom.spelers.Speler;

import java.util.*;

/*
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/* De Spel-klasse is de controllerklasse
 * Ze beheert de mogelijke Levels, het Speelveld en het spelverloop.
 */

public class Spel {
    public static final int MIN_HOEK = 0;
    public static final int MAX_HOEK = 90;
    public static final int MIN_KRACHT = 1;
    public static final int MAX_KRACHT = 30;
    public static final int MIN_MAXWIND = 0;
    public static final int REG_MAXWIND = 3;
    public static final int MAX_MAXWIND = 30;
    public static final int MIN_STARTHP = 1;
    public static final int REG_STARTHP = 3;
    public static final int MAX_STARTHP = Integer.MAX_VALUE;

    Speelveld speelveld; //het speelveld waarop gespeeld zal worden
    Random random;
    LevelLezer levelLezer; //LevelLezer om mogelijke Levels in te laden
    Map<String, Level> levels; //Map om mogelijke Levels te bewaren
    Speler spelerA; //spelerA en ...
    Speler spelerB; //... spelerB zullen strijden voor de overwinning!
    Speler winnaar; //Wie is er gewonnen?
    Speler spelerNu; //Wie is er aan de beurt?
    Schutter schutterA; //de Schutter die bestuurd wordt door spelerA
    Schutter schutterB; //de Schutter die bestuurd wordt door spelerB

    Level gekozenLevel; //het gekozen Level dat gespeeld zal worden
    int gekozenMaxWind; //de gekozen maximale wind waarmee gespeeld zal worden; de maxWind is vergelijkbaar met een moeilijkheidsgraad

    boolean isBezig; //Is het spel nog bezig?
    boolean alGespeeld; //Is er deze beurt al gespeeld?


    public Spel() throws AngryTanksException{
        this.random = new Random();
        this.levels = new HashMap<String, Level>();

        //alvast de Levels inladen...
        this.levelLezer = new FileLevelLezer();
        laadLevels();

        //spelinstellingen klaarmaken
        resetSpel();
    }


    //methodes voor levels

    public void laadLevels() throws AngryTanksException{ //laadt Levels in
        levels = levelLezer.lees();
    }

    public Map<String,Level> getLevels(){
        return levels;
    }


    //methodes voor spelverloop

    public void resetSpel(){ //spelinstellingen resetten
        speelveld = new Speelveld();
        spelerA = null;
        spelerB = null;
        winnaar = null;
        spelerNu = null;
        isBezig = false;
        schutterA = new Schutter(Orientatie.RECHTS);
        schutterB = new Schutter(Orientatie.LINKS);
    }


    public void startSpel() throws AngryTanksException {
        if(!isSpelStartKlaar()) throw new AngryTanksException("Spel kan niet gestart worden.");

        speelveld = new Speelveld();
        speelveld.setMaxWind(gekozenMaxWind); //de gekozen maxWind instellen
        speelveld.setLevel(gekozenLevel); //het gekozen Level instellen
        spelerNu = null;
        schutterA.resetHP();
        schutterB.resetHP();
        speelveld.setSchutterA(spelerA.getSchutter());
        speelveld.setSchutterB(spelerB.getSchutter());
        speelveld.bouwSpeelveld(); //speelveld laten bouwen

        if(!speelveld.isSpeelveldKlaar()) throw new AngryTanksException("Spel kan niet gestart worden.");

        this.isBezig = true; //het spel is begonnen!
        nieuweBeurt(); //de eerste beurt kan gespeeld worden
    }

    public void nieuweBeurt() throws AngryTanksException{
        if(!isBezig) throw new AngryTanksException("Spel is niet bezig. Begin een nieuw spel.");

        kiesSpelerNu(); //beslis wie aan de beurt is
        alGespeeld = false; //de beurt is nog niet gespeeld
        speelveld.setWindRandom(); //de wind mag gerandomized worden
    }

    private void kiesSpelerNu(){
        spelerNu = getAndereSpeler(spelerNu); //de andere speler is nu aan de beurt

        if(spelerNu == null){ //is er nog geen speler aan de beurt?
            spelerNu = spelerA;
            if(random.nextBoolean()) //wie zal het worden?
                spelerNu = spelerB;
        }
    }

    public void speel(double kracht, double hoek) throws AngryTanksException{
        if(!isBezig) throw new AngryTanksException("Spel is niet bezig. Begin een nieuw spel.");
        if(alGespeeld) throw new AngryTanksException("Je hebt deze beurt al gespeeld!");
        if(kracht < MIN_KRACHT || kracht > MAX_KRACHT) throw new AngryTanksException("Kracht moet tussen " + MIN_KRACHT + " en " + MAX_KRACHT + " liggen.");
        if(hoek < MIN_HOEK || hoek > MAX_HOEK) throw new AngryTanksException("Hoek moet tussen " + MIN_HOEK + " en " + MAX_HOEK + " liggen.");

        speelveld.schiet(spelerNu.getSchutter(), kracht, hoek); //het schot lanceren...

        alGespeeld = true; //er is gespeeld!

        checkOfGeraakt(); //is er iemand geraakt?
        checkOfGedaan(); //is het spel nu gedaan?
    }

    private void checkOfGeraakt(){
        if(speelveld.isVijandGeraakt()){
            raak(getAndereSpeler(spelerNu));
        }
        if(speelveld.isZelfGeraakt()){
            raak(spelerNu);
        }
    }

    private void raak(Speler speler){
        speler.getSchutter().verlaagHP(1);
    }

    private void checkOfGedaan(){
        if(spelerA.getSchutter().getHp() < 1){
            win(spelerB);
        } else if(spelerB.getSchutter().getHp() < 1){
            win(spelerA);
        }
    }

    private void win(Speler speler){
        winnaar = speler;
        isBezig = false;
    }


    //setters voor spel-instellingen

    public void setLevel(String levelNaam) throws AngryTanksException{
        if(!levels.containsKey(levelNaam))
            throw new AngryTanksException("Level kon niet gevonden worden.");
        this.gekozenLevel = levels.get(levelNaam);
    }

    public void setMaxWind(int maxWind) throws AngryTanksException{
        if(maxWind > MAX_MAXWIND || maxWind < MIN_MAXWIND) throw new AngryTanksException("Max. wind moet tussen " + MIN_MAXWIND + " en " + MAX_MAXWIND + " liggen.");
        this.gekozenMaxWind = maxWind;
    }

    public void setStartHP(int startHP) throws AngryTanksException{
        if(startHP > MAX_STARTHP || startHP < MIN_STARTHP) throw new AngryTanksException("Start-HP moet tussen" + MIN_STARTHP + " en " + MAX_STARTHP + " liggen.");
        this.schutterA.setMaxHp(startHP);
        this.schutterA.setHp(startHP);
        this.schutterB.setMaxHp(startHP);
        this.schutterB.setHp(startHP);
    }

    public void maakSpelerA(String naam){
        if(naam.equals("")) naam = "Speler A";
        spelerA = new Mens(naam, schutterA);
    }

    public void maakSpelerB(String naam){
        if(naam.equals("")) naam = "Speler B";
        spelerB = new Mens(naam, schutterB);
    }


    //getters voor spelinformatie


    public boolean isSpelStartKlaar(){
        if(spelerA == null) return false;
        if(spelerB == null) return false;
        if(schutterA == null) return false;
        if(schutterB == null) return false;
        if(gekozenLevel == null) return false;
        return true;
    }

    public boolean isSpelBezig(){
        return isBezig;
    }

    public boolean isObstructieGeraakt(){
        return speelveld.isObstructieGeraakt();
    }

    public boolean isVijandGeraakt(){
        return speelveld.isVijandGeraakt();
    }

    public boolean isZelfGeraakt(){
        return speelveld.isZelfGeraakt();
    }

    //getters voor spelers

    public Speler getSpelerA(){
        return spelerA;
    }

    public Speler getSpelerB(){
        return spelerB;
    }

    public Speler getAndereSpeler(Speler speler){
        if(speler == spelerA)
            return spelerB;
        else if(speler == spelerB)
            return spelerA;
        return null;
    }

    public Speler getSpelerAanDeBeurt(){
        return spelerNu;
    }

    public Speler getWinnaar() throws AngryTanksException{
        if(isBezig) throw new AngryTanksException("Spel is nog bezig. Winnaar nog niet bekend.");

        return winnaar;
    }


    //getters voor schot-informatie

    public int getWind(){
        return speelveld.getWind().getWind();
    }

    public double getHoek(){
        return speelveld.getLaatsteSchot().getHoek();
    }

    public double getKracht(){
        return speelveld.getLaatsteSchot().getKracht();
    }

    public ArrayList<Positie> getBaan(){
        return speelveld.getLaatsteSchot().getBaanClean();
    }


    //getters voor opbouw landschap

    public Positie getSchutterAPositie(){
        return speelveld.getSchutterAPositie();
    }

    public Positie getSchutterBPositie(){
        return speelveld.getSchutterBPositie();
    }

    public int getLevelHoogte(){
        return speelveld.getStdHoogte();
    }

    public int getLevelBreedte(){
        return speelveld.getStdBreedte();
    }

    public HashSet<Positie> getObstructie(){
        return speelveld.getObstructie();
    }

    public int getExtensieTop(){
        return speelveld.getExtensieTop();
    }

    public int getExtensieRight(){
        return speelveld.getExtensieRight();
    }

    public int getExtensieBottom(){
        return speelveld.getExtensieBottom();
    }

    public int getExtensieLeft(){
        return speelveld.getExtensieLeft();
    }


    //setters voor opbouw landschap

    public void setExtensieTop(int extensieTop){
        speelveld.setExtensieTop(extensieTop);
    }

    public void setExtensieRight(int extensieRight){
        speelveld.setExtensieRight(extensieRight);
    }

    public void setExtensieBottom(int extensieBottom){
        speelveld.setExtensieBottom(extensieBottom);
    }

    public void setExtensieLeft(int extensieLeft){
        speelveld.setExtensieLeft(extensieLeft);
    }
}
