package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 16/02/14
 */

/*
    Een ModuleBalk is een gelayoutte JPanel die Modules kan bevatten, naar wens links of rechts.
    De tussenruimte wordt opgevuld met een arcering.
 */

public class ModuleBalk extends JPanel{
    public enum Kant{
        LINKS, RECHTS
    }

    private LinkedHashSet<Module> linksModules;
    private LinkedHashSet<Module> rechtsModules;

    public ModuleBalk(){
        linksModules = new LinkedHashSet<Module>();
        rechtsModules = new LinkedHashSet<Module>();

        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        this.setBackground(Constanten.BALK_BG_KLEUR);
        this.setOpaque(true);
        this.setMinimumSize(new Dimension(1, Constanten.BALK_HOOGTE));
    }

    private void bouw(){
        Iterator<Module> itr;
        this.removeAll();
        itr = linksModules.iterator();
        while (itr.hasNext()){
            this.add(itr.next());
        }
        this.add(Box.createRigidArea(new Dimension(5, 1)));
        this.add(Box.createHorizontalGlue());

        LinkedList<Module> revertList = new LinkedList<Module>(rechtsModules); //enkel om in tegengestelde richting te kunnen iteraten
        itr = revertList.descendingIterator();
        while (itr.hasNext()){
            this.add(itr.next());
        }

    }


    //adden en removen

    public void add(Module module, Kant kant){
        if(kant == Kant.LINKS){
            linksModules.add(module);
            module.setBorderLinks(false);
            module.setBorderRechts(true);
        }
        if(kant == Kant.RECHTS){
            rechtsModules.add(module);
            module.setBorderLinks(true);
            module.setBorderRechts(false);
        }

        bouw();
    }

    public void remove(Module module){
        linksModules.remove(module);
        rechtsModules.remove(module);
        bouw();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = Constanten.MARKERING_PATTERN.getImage();
        int iw = image.getWidth(this);
        int ih = image.getHeight(this);
        if (iw > 0 && ih > 0) {
            for (int x = 0; x < getWidth(); x += iw) {
                for (int y = 0; y < getHeight(); y += ih) {
                    g.drawImage(image, x, y, iw, ih, this);
                }
            }
        }
    }


}

