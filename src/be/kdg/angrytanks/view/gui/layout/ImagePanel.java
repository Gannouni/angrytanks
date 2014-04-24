package be.kdg.angrytanks.view.gui.layout;


import javax.swing.*;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 13/02/14
 */

/*
    Een ImagePanel is een JPanel die een afbeelding bevat, en de afbeelding naar wens meeschaalt met de grootte van het ImagePanel.
 */

public class ImagePanel extends JPanel {
    public enum KantY{ TOP, CENTER, BOTTOM}
    public enum KantX{ LEFT, MIDDLE, RIGHT}
    public enum Houd{ X, Y, NONE}

    private Image image;
    private boolean tile;
    private boolean keepScale;
    private Houd houd;
    private KantY kantY;
    private KantX kantX;

    public ImagePanel(Image image, boolean tile, boolean keepScale, Houd houd, KantX kantX, KantY kantY) {
        super(new BorderLayout());
        this.image = image;
        this.tile = tile;
        this.keepScale = keepScale;
        this.houd = houd;
        this.kantX = kantX;
        this.kantY = kantY;

        setOpaque(false);
        setVisible(true);
    }

    public ImagePanel(Image image){
        this(image, false, false, Houd.NONE, KantX.MIDDLE, KantY.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d;
        if (image != null) {
            d = new Dimension(image.getWidth(this), image.getHeight(this));
        } else {
            d = new Dimension(600,400);
        }
        return d;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int iw = image.getWidth(this);
        int ih = image.getHeight(this);
        if (tile) {

            if (iw > 0 && ih > 0) {
                for (int x = 0; x < getWidth(); x += iw) {
                    for (int y = 0; y < getHeight(); y += ih) {
                        g.drawImage(image, x, y, iw, ih, this);
                    }
                }
            }
        } else if(keepScale){
            int x = 0;
            int y = 0;
            double imageAspectRatio = (double)iw/ih;
            double componentAspectRatio = (double)getWidth()/getHeight();

            if(houd == Houd.X){
                iw = getWidth();
                ih = (int)(iw / imageAspectRatio);
            } else if(houd == Houd.Y){
                ih = getHeight();
                iw = (int)(ih * imageAspectRatio);
            } else {
                if(imageAspectRatio <= componentAspectRatio){ // afb. is hoger
                    ih = getHeight();
                    iw = (int)(getHeight() * imageAspectRatio);
                } else { // afb. is lager
                    iw = getWidth();
                    ih = (int)(getWidth() / imageAspectRatio);
                }
            }

            if(kantX == KantX.LEFT || houd == Houd.X){
                x = 0;
            } else if(kantX == KantX.RIGHT){
                x = (getWidth() - iw);
            } else{
                x = (getWidth() - iw)/2;
            }

            if(kantY == KantY.TOP || houd == Houd.Y){
                y = 0;
            } else if(kantY == KantY.BOTTOM){
                y = (getHeight() - ih);
            } else {
                y = (getHeight() - ih)/2;
            }

            g.drawImage(image, x, y, iw, ih, this);


        } else {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
