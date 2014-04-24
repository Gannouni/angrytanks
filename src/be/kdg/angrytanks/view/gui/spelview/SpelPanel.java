package be.kdg.angrytanks.view.gui.spelview;

import be.kdg.angrytanks.controller.Spel;
import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.view.gui.GUI;
import be.kdg.angrytanks.view.gui.spelview.landschap.Landschap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.Callable;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 13/02/14
 */

/**
    Het SpelPanel is een JPanel dat de volledige in-game spelinterface bevat.
    Het brengt het dashboard, de controls en het landschap samen.
 */
public class SpelPanel extends JPanel{
    Spel spel;
    GUI gui;

    private Dashboard dashboard;
    private Controls controls;
    private Landschap landschap;

    private JScrollPane uitzicht;


    public SpelPanel(Spel spel) {
        this.spel = spel;

        this.initComponenten();
        this.layoutComponenten();
        this.addComponenten();
    }

    public void readySpelPanel(){
        this.gui = (GUI) SwingUtilities.getAncestorOfClass(GUI.class, this);
        this.controls.readyControls();
    }

    private void initComponenten(){
        dashboard = new Dashboard(spel);
        controls = new Controls(spel);
        landschap = new Landschap(spel);

        uitzicht = new JScrollPane(landschap);
    }

    private void layoutComponenten(){
        this.setLayout(new BorderLayout());
        uitzicht.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private void addComponenten(){
        this.add(dashboard,BorderLayout.NORTH);
        this.add(uitzicht, BorderLayout.CENTER);
        this.add(controls, BorderLayout.SOUTH);
    }


    public void resetSpelPanel(){
        dashboard.updateInfo();
        landschap.resetLandschap();
        controls.resetControls();
        speelModus();
    }

    // bij speelModus zullen het dashboard, de controls en het landschap in speelModus gezet worden.
    public void speelModus(){
        dashboard.speelModus();
        controls.speelModus();
        landschap.speelModus();
    }

    // bij feedbackModus zullen het dashboard, de controls en het landschap in feedbackModus gezet worden.
    public void feedbackModus(){
        dashboard.feedbackModus();
        controls.feedbackModus();
        try{
            landschap.feedbackModus();
        } catch(AngryTanksException e){
            gui.toonError(e);
        }
    }

    // bij animatieModus zal het landschap in animatieModus gezet worden, met als resultaat-actie het activeren van de feedbackmodus
    public void animatieModus(){
        landschap.animatieModus(new Callable() {
            @Override
            public Object call() throws Exception {
                feedbackModus();
                return null;
            }
        });
    }

    // setTankLoopGraden wordt gebruikt door de controls om de stand van de tankloop aan te passen aan de hoekslider
    public void setTankLoopGraden(int graden){
        if(spel.getSpelerAanDeBeurt() == spel.getSpelerA()){
            landschap.setTankALoopGraden(graden);
        } else{
            landschap.setTankBLoopGraden(graden);
        }
    }
}
