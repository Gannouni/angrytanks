package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 16/02/14
 */

/*
    Een Module is een gelayoutte JPanel die gebruikt kan worden in een ModuleBalk.
    De Module kan optioneel link, rechts of langs beide kanten een rand hebben.
 */

public class Module extends JPanel {
    private boolean borderLinks;
    private boolean borderRechts;
    private Border border;


    public Module(boolean borderLinks, boolean borderRechts){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorderLinks(borderLinks);
        setBorderRechts(borderRechts);

        setBackground(Constanten.BALK_BG_KLEUR);
        setOpaque(true);

        int margin = 10;
        setBorder(new EmptyBorder(margin, margin, margin, margin));
    }

    public Module(){
        this(false, false);
    }

    public void setBorderLinks(boolean borderLinks){
        this.borderLinks = borderLinks;
        setBorder();
    }

    public void setBorderRechts(boolean borderRechts){
        this.borderRechts = borderRechts;
        setBorder();
    }

    @Override
    public void setBorder(Border border){
        this.border = border;

        //stel dikte in naargelang links en/of rechterborder
        int borderLinksBreedte = 0;
        int borderRechtsBreedte = 0;
        if(borderLinks) borderLinksBreedte = Constanten.BASIS_DIKTE;
        if(borderRechts) borderRechtsBreedte = Constanten.BASIS_DIKTE;

        //stel border in
        super.setBorder(new CompoundBorder(
            BorderFactory.createMatteBorder(0, borderLinksBreedte, 0, borderRechtsBreedte, Constanten.BALK_STREEP_KLEUR),
            border
        ));
    }

    public void setBorder(){
        setBorder(this.border);
    }


}