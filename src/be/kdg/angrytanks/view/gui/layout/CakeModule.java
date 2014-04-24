package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 20/02/14
 */

/*
    Een CakeModule is een Module die uit twee horizontale lagen bestaat, gescheiden door een middenlijn.
    Op elke laag kan een JComponent ingevoegd worden.
 */
public class CakeModule extends Module{

    private JPanel topBox;
    private JPanel bottomBox;

    private JComponent topComponent;
    private JComponent bottomComponent;

    public CakeModule(JComponent topComponent, JComponent bottomComponent){
        super();

        topBox = new JPanel();
        bottomBox = new JPanel();

        this.topComponent = topComponent;
        this.bottomComponent = bottomComponent;

        bouwModule();
    }

    public CakeModule(){
        this(new JPanel(), new JPanel());
    }

    public void setTopComponent(JComponent topComponent){
        this.topComponent = topComponent;
        bouwModule();
    }

    public void setBottomComponent(JComponent bottomComponent){
        this.bottomComponent = bottomComponent;
        bouwModule();
    }

    private void bouwModule(){
        this.removeAll();
        topBox.removeAll();
        bottomBox.removeAll();

        topBox.setLayout(new BoxLayout(topBox, BoxLayout.PAGE_AXIS));
        topBox.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Constanten.BALK_STREEP_KLEUR));
        topBox.setOpaque(false);
        topBox.add(topComponent);
        topBox.add(Box.createHorizontalGlue());

        bottomBox.setLayout(new BoxLayout(bottomBox, BoxLayout.PAGE_AXIS));
        bottomBox.setBorder(new EmptyBorder(0, 0, 0, 0));
        bottomBox.setOpaque(false);
        bottomBox.add(bottomComponent);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(topBox);
        this.add(bottomBox);
    }
}
