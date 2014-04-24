package be.kdg.angrytanks.dom.veld;

import java.util.HashSet;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/*
  Een Obstructie is een HashSet van Positie's waarop een blokje obstructie (rots) staat.
  De Obstructie breidt de HashSet uit doordat hij ook kan 'extenden'
  bvb:
                        ^
                        |         ##
                     extendY      ##
                        |         ##
                        *------------------------*
                        | Level   ##             |
                        |         ##   ##        |
                        |         ###  ##        |
              standaardhoogte     #######  #     |
                        |         ##########     |
                        |     ### ##########     |
                        |     ### ##########     |
            ############|########################|###########
            ############|########################|###########
            <--extendX--*----standaardbreedte----*--extendX-->
 */

public class Obstructie extends HashSet<Positie> {

    public void extendX(int startX, int eindX, int startY, int eindY){ //extend de rij op startX tot op eindX, in de range van startY tot eindY
        for(int y = startY; y <= eindY; y++){
            if(this.contains(new Positie(startX, y))){
                int startLoopX = startX;
                int eindLoopX = eindX;
                if(eindX < startX){
                    startLoopX = eindX;
                    eindLoopX = startX;
                }
                for(int x = startLoopX; x <= eindLoopX; x++ ){
                    this.add(new Positie(x, y));
                }
            }
        }
    }

    public void extendY(int startY, int eindY, int startX, int eindX){ //extend de rij op startX tot op eindX, in de range van startY tot eindY
        for(int x = startX; x <= eindX; x++){
            if(this.contains(new Positie(x, startY))){
                int startLoopY = startY;
                int eindLoopY = eindY;
                if(eindY < startY){
                    startLoopY = eindY;
                    eindLoopY = startY;
                }
                for(int y = startLoopY; y <= eindLoopY; y++){
                    this.add(new Positie(x, y));
                }
            }
        }
    }
}
