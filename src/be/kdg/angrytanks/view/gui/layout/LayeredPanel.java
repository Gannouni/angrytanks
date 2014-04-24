package be.kdg.angrytanks.view.gui.layout;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 11/02/14
 */

/*
    Een LayeredPanel is een JPanel die uit verschillende overlappende JPanels kan bestaan.
 */

public class LayeredPanel extends JPanel{
    private ArrayList<JPanel> lagen;

    public LayeredPanel(ArrayList<JPanel> lagen){
        this.lagen = new ArrayList<JPanel>();
        voegLaagToe(lagen);
        this.setLayout(null);
        this.setBounds(this.getX(),this.getY(), this.getWidth(), this.getHeight()); //stel width en height van lagen gelijk aan width en height van layeredpanel
        bouwLagen();

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                e.getComponent().setBounds(e.getComponent().getX(), e.getComponent().getY(), e.getComponent().getWidth(), e.getComponent().getHeight());
            }

            @Override public void componentMoved(ComponentEvent e) {}

            @Override public void componentShown(ComponentEvent e) {}

            @Override public void componentHidden(ComponentEvent e) {}
        });
    }

    public LayeredPanel(){
        this(new ArrayList<JPanel>());
    }

    public void voegLaagToe(ArrayList<JPanel> lagen){
        this.lagen.addAll(lagen);

    }

    public void voegLaagToe(JPanel laag){
        lagen.add(laag);
    }

    public void verwijderLaag(JPanel laag){
        if(lagen.contains(laag))
            lagen.remove(lagen.indexOf(laag));
    }

    public void bouwLagen(){
        this.removeAll();
        for(int i = lagen.size() - 1; i >= 0; i--){
            if(lagen.get(i) != null){
                this.add(lagen.get(i));
            }
        }
        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        for(JPanel laag : lagen){
            laag.setBounds(0, 0, width, height);
        }
    }


}
