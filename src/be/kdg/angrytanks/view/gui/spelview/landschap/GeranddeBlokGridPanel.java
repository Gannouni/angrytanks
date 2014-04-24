package be.kdg.angrytanks.view.gui.spelview.landschap;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.veld.Positie;

import java.util.HashMap;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 10/02/14
 */
/*
    Een GeranddeBlokGridPanel is een BlokGridPanel dat bestaat uit GeranddeBlokken.
    Het GeranddeBlokGridPanel kan haar eigen GeranddeBlokken beheren, door hun afbeeldingen in te stellen op basis van aangrenzende GeranddeBlokken.
 */
public class GeranddeBlokGridPanel extends BlokGridPanel {

    int topLimit = 0;
    int rightLimit = 0;
    int bottomLimit = 0;
    int leftLimit = 0;

    boolean gebruikTopLimit = false;
    boolean gebruikRightLimit = false;
    boolean gebruikBottomLimit = false;
    boolean gebruikLeftLimit = false;

    public GeranddeBlokGridPanel(HashMap<Positie, Blok> blokken, boolean forceerBlokGrootte, int topLimit, int rightLimit, int bottomLimit, int leftLimit, boolean gebruikTopLimit, boolean gebruikRightLimit, boolean gebruikBottomLimit, boolean gebruikLeftLimit){
        super(blokken, forceerBlokGrootte);
        this.topLimit = topLimit;
        this.rightLimit = rightLimit;
        this.bottomLimit = bottomLimit;
        this.leftLimit = leftLimit;
        this.gebruikTopLimit = gebruikTopLimit;
        this.gebruikRightLimit = gebruikRightLimit;
        this.gebruikBottomLimit = gebruikBottomLimit;
        this.gebruikLeftLimit = gebruikLeftLimit;
    }

    public GeranddeBlokGridPanel(HashMap<Positie, Blok> blokken, boolean forceerBlokGrootte, int topLimit, int rightLimit, int bottomLimit, int leftLimit){
        this(blokken, forceerBlokGrootte, topLimit, rightLimit, bottomLimit, leftLimit, true, true, true, true);
    }

    public GeranddeBlokGridPanel(HashMap<Positie, Blok> blokken, boolean forceerBlokGrootte){
        this(blokken, forceerBlokGrootte, 0, 0, 0, 0, false, false, false, false);
    }

    public GeranddeBlokGridPanel(){
        super();
    }

    public void setTopLimit(int topLimit, boolean gebruikTopLimit){
        this.topLimit = topLimit;
        this.gebruikTopLimit = gebruikTopLimit;
    }

    public void setBottomLimit(int bottomLimit, boolean gebruikBottomLimit){
        this.bottomLimit = bottomLimit;
        this.gebruikBottomLimit = gebruikBottomLimit;
    }

    public void setRightLimit(int rightLimit, boolean gebruikRightLimit){
        this.rightLimit = rightLimit;
        this.gebruikRightLimit = gebruikRightLimit;
    }

    public void setLeftLimit(int leftLimit, boolean gebruikLeftLimit){
        this.leftLimit = leftLimit;
        this.gebruikLeftLimit = gebruikLeftLimit;
    }

    @Override
    public void update(){
        resetRanden();
        super.update();
    }

    public void resetRanden(){
        for(Positie pos : super.blokken.keySet()){
            if(super.blokken.get(pos) instanceof GeranddeBlok){
                int x = pos.getX(), y = pos.getY();
                boolean top = true, right = true, bottom = true, left = true;

                if(blokken.containsKey(new Positie(x, y + 1)) || (gebruikTopLimit && y == topLimit))
                    top = false;
                if(blokken.containsKey(new Positie(x + 1, y))  || (gebruikRightLimit && x == rightLimit))
                    right = false;
                if(blokken.containsKey(new Positie(x, y - 1))  || (gebruikBottomLimit && y == bottomLimit))
                    bottom = false;
                if(blokken.containsKey(new Positie(x - 1, y))|| (gebruikLeftLimit && x == leftLimit))
                    left = false;

                ((GeranddeBlok) super.blokken.get(pos)).stelRandIn(top, right, bottom, left);
            }
        }
    }
}