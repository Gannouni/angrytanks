package be.kdg.angrytanks.dom.veld;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

//Klasse die coördinaten kan bewaren

public class Positie {
    private int x;
    private int y;

    public Positie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Positie() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Positie positie = (Positie) o;

        if (x != positie.x) return false;
        if (y != positie.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
