package be.kdg.angrytanks.dom.veld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
    De Schot-klasse berekent en bewaart de baan van een schot op basis van beginSnelheid, hoek, oorsprong en windSnelheid
    Ze kan ook nakijken of het schot botst op verschillende zones (obstructie, tank, ...), en op welke positie
 */

public class Schot {
    public static final double GRAVITATIECONSTANTE = 9.81; //de zwaartekracht!

    private Positie oorsprong; //oorsprong van het schot; positie van de tank die het schot afvuurt
    private Orientatie tankRichting; //de orientatie van de tank die het schot afvuurt
    private double kracht; //kracht waarmee het schot is afgevuurd (oftewel beginsnelheid)
    private double hoek; //hoek van 0-90 graden
    private double windSnelheid; //de windsnelheid
    private HashMap<String, HashSet<Positie>> elementen; //bewaart verschillende zones waarop het schot kan botsen, geïdentificeerd adhv. strings

    private double hoekEcht; //praktische hoek van 0-180
    private int schotRichting; //<0 als schot naar links is, 0 als schot naar boven is, >0 als schot naar rechts
    private boolean ietsGeraakt; //of er iets is geraakt
    private String geraaktId; //bewaart String identifier van geraakte zone
    private Positie raakpunt; //positie waarop het schot is gebotst
    private ArrayList<Positie> baan; //de volledige baan die het schot aflegt

    public Schot(Orientatie tankRichting, double kracht, double hoek, Positie oorsprong, double windSnelheid, HashMap<String, HashSet<Positie>> elementen){
        this.tankRichting = tankRichting;
        this.kracht = kracht;
        this.hoek = hoek;
        this.hoekEcht = berekenHoekInRichting(hoek, tankRichting);
        this.windSnelheid = windSnelheid;
        this.elementen = elementen;
        this.oorsprong = new Positie(oorsprong.getX(), oorsprong.getY());

        this.schotRichting = berekenSchotRichting(berekenRadianHoek(this.hoekEcht), this.kracht, this.windSnelheid, GRAVITATIECONSTANTE, this.tankRichting);
        this.oorsprong = oorsprongFix(this.oorsprong, this.schotRichting);
        this.baan = berekenBaan(this.oorsprong, this.schotRichting, berekenRadianHoek(this.hoekEcht), this.kracht, this.windSnelheid, GRAVITATIECONSTANTE);
        berekenBotsing(this.baan, this.elementen);
    }


    private ArrayList<Positie> berekenBaan(Positie oorsprong, int schotRichting, double hoekRad, double kracht, double windSnelheid, double zwaartekracht){

        //baanpunten berekenen
        int increment;
        ArrayList<Positie> baanPunten = new ArrayList<Positie>();
        if(schotRichting == 0){ //schot recht naar boven! bugfix
            increment = 1;
            for(int y = oorsprong.getY(); y >= 0; y += increment){
                if(y == oorsprong.getY() + (int)kracht) increment = -1; //als y hoogtepunt (kracht) heeft bereikt, terug naar beneden vallen
                baanPunten.add(new Positie(oorsprong.getX(), y));
            }
        } else{ //gewoon schot naar links of rechts
            int x = 0;
            increment = schotRichting / Math.abs(schotRichting); //bepaalt of de baan van links naar rechts berekend wordt (1) of van rechts naar links (-1), belangrijk voor volgorde posities in ArrayList baan
            boolean above0 = true;
            while(above0){ //loop berekent parabolische baan, stopt met berekenen als de baan y-positie 0 bereikt (basislijn)
                int y = oorsprong.getY() + (int)Math.round(berekenY((double)x, hoekRad, kracht, windSnelheid, zwaartekracht));
                x+= increment;
                baanPunten.add(new Positie(oorsprong.getX() + x, y));
                if(y <= 0) above0 = false;
            }
        }

        //volledige baan creëren door opvullen y-waarden
        ArrayList<Positie> baan = new ArrayList<Positie>();

        for(int i = 0; i < baanPunten.size(); i++){
            baan.add(baanPunten.get(i));

            if(i < baanPunten.size() - 1){
                int afstand = baanPunten.get(i + 1).getY() - baanPunten.get(i).getY();
                if(afstand != 0){
                    int operator = afstand / Math.abs(afstand);
                    for(int j = 1; j < Math.abs(afstand); j++){
                        int fillX = baanPunten.get(i).getX();
                        if(operator < 0){
                            fillX = baanPunten.get(i + 1).getX();
                        }
                        baan.add(new Positie(fillX, baanPunten.get(i).getY() + operator * j));
                    }
                }
            }
        }

        return baan;

    }

    private void berekenBotsing(ArrayList<Positie> baan, HashMap<String, HashSet<Positie>> elementen){
        ietsGeraakt = false;
        for(int i = 1; i < baan.size(); i++){
            for(String id : elementen.keySet()){
                for(Positie posElement : elementen.get(id)){
                    if(baan.get(i).equals(posElement)){
                        geraaktId = id;
                        raakpunt = new Positie(posElement.getX(), posElement.getY());
                        ietsGeraakt = true;
                        return;
                    }
                }
            }
        }
    }

    private double berekenY(double x, double hoekRad, double kracht, double windSnelheid, double zwaartekracht){
        //le magic formula:
        return (-zwaartekracht / 2) / Math.pow((kracht * Math.cos(hoekRad) + windSnelheid), 2) * Math.pow(x, 2) + (x * Math.sin(hoekRad)) / (Math.cos(hoekRad) + (windSnelheid / kracht));

    }

    private int berekenSchotRichting(double hoekRad, double kracht, double windSnelheid, double zwaartekracht, Orientatie tankRichting){ //testen in welke richting het schot gaat, returnt 0 wanneer rechtop, -1 wanneer links en 1 wanneer rechts
        if((int)Math.round(Math.pow((kracht * Math.cos(hoekRad) + windSnelheid), 2)) == 0){ //berekent of het schot rechtop gaat
            return 0; //schot gaat naar boven
        } else{ // bereken welke richting het schot gaat (gaat er van uit dat je niet naar beneden kan schieten!)
            double[] richtingTest = new double[3];
            for(int i = 0; i <= 2; i++){
                richtingTest[i] = berekenY(i-1, hoekRad, kracht, windSnelheid, zwaartekracht); //berekent y van baan op x-positie -1, 0 en 1
            }
            if(richtingTest[0] > richtingTest[1]){ //als y op -1 groter is dan op 1
                return -1; //schot gaat naar links
            } else if (richtingTest[2] > richtingTest[1]){ //als y op 1 groter is dan op -1
                return 1; //schot gaat naar rechts
            } else if(tankRichting == Orientatie.LINKS){
                return -1;
            } else{
                return 1;
            }
        }
    }


    private double berekenRadianHoek(double gradenHoek){ //berekent radian-versie van hoek
        return gradenHoek / 180 * Math.PI;
    }

    private double berekenHoekInRichting(double hoek, Orientatie orientatie){ //berekent 'echte' hoek op basis van orientatie
        if(orientatie == Orientatie.LINKS){ //als orientatie links is
            return 180 - hoek; //hoek horizontaal spiegelen
        } else return hoek;
    }

    private Positie oorsprongFix(Positie oorsprong, int schotRichting){ //bugfix, past oorsprong schot aan
        if(schotRichting != 0)
            return new Positie(oorsprong.getX() + (schotRichting / Math.abs(schotRichting))*-1,oorsprong.getY());
        return new Positie(oorsprong.getX(),oorsprong.getY());
    }


    //info-getters

    public ArrayList<Positie> getBaanVol(){
        return baan;
    } //returnt volledige baan

    public ArrayList<Positie> getBaanClean(){ //returnt baan tot op botsing, minus 1ste positie
        if(!isIetsGeraakt()) return baan;

        ArrayList<Positie> baanClean = new ArrayList<Positie>();
        for(int i = 1; i < baan.size(); i++){
            baanClean.add(baan.get(i));
            if(baan.get(i).equals(raakpunt)){
                return baanClean;
            }
        }
        return baanClean;
    }

    public boolean isIetsGeraakt(){
        return ietsGeraakt;
    }

    public String getGeraaktId(){
        return geraaktId;
    }

    public Positie getRaakpunt(){
        return new Positie(raakpunt.getX(),raakpunt.getY());
    }


    //basic-getters

    public double getHoek(){
        return hoek;
    }

    public double getKracht(){
        return kracht;
    }

    public double getWindSnelheid(){
        return windSnelheid;
    }

    public Orientatie getTankRichting(){
        return tankRichting;
    }
}
