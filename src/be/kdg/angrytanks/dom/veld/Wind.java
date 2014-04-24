package be.kdg.angrytanks.dom.veld;

import java.util.Random;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
  De Wind-klasse beheert de wind, en kan een willekeurige waarde genereren tussen maxWind en -maxWind
 */

public class Wind {
    private Random random;
    private int maxWind;
    private int wind = 0;

    public Wind(int maxWind){
        random = new Random();
        setMaxWind(maxWind);
    }


    //getters:

    public int getMaxWind(){
        return maxWind;
    }

    public int getWind(){
        return wind;
    }


    //setters:

    public void setWind(int wind){
        if(wind != 0 && Math.abs(wind) > maxWind)
            wind = maxWind * wind / Math.abs(wind); //als nieuwe absolute waarde van wind meer is dan maxWind, stel nieuwe windwaarde gelijk aan maxwind (positief of negatief)
        this.wind = wind;
    }

    public void setMaxWind(int maxWind){
        this.maxWind = Math.abs(maxWind);
        setWind(wind); //wind opnieuw instellen, zodat deze nieuwe maxWind zeker niet overstijgt
    }

    public void setWindRandom(){
        if(maxWind != 0){
            this.wind = random.nextInt(maxWind*2)-maxWind;
        } else{
            this.wind = 0;
        }

    }
}
