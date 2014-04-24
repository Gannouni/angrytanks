package be.kdg.angrytanks.dom.veld;


/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
  De Schutter-klasse bewaart en beheert informatie over de schutter; het aantal HP, het maximum aantal HP, en de orientatie van de schutter

 */
public class Schutter{

    private Orientatie orientatie;
    private int hp;
    private int maxHp;


    public Schutter(Orientatie orientatie, int maxHp){
        this.orientatie = orientatie;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public Schutter(Orientatie orientatie){
        this(orientatie, 1);
    }


    //getters

    public Orientatie getOrientatie(){
        return orientatie;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getHp(){
        return hp;
    }


    //setters

    public void setHp(int hp){
        if(hp > maxHp){
            hp = maxHp;
        }
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setOrientatie(Orientatie orientatie){
        this.orientatie = orientatie;

  }


    //extra methodes

    public void verlaagHP(int hp){
        this.hp -= hp;

    }

    public void verhoogHP(int hp){
        this.hp += hp;

    }

    public void resetHP(){
        this.hp = this.maxHp;
    }



}
