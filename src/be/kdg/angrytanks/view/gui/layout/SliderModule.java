package be.kdg.angrytanks.view.gui.layout;

import be.kdg.angrytanks.view.gui.constanten.Constanten;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.concurrent.Callable;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 18/02/14
 */
/*
    Een SliderModule is een Module die een PixelSlider, en een JTextField combineert.
    De JTextField wordt geupdate naargelang de waarde in de PixelSlider.
    Een JLabel kan gebruikt worden om de SliderModule een titel te geven.
 */
public class SliderModule extends Module {
    private Callable onChangeFunctie;

    private int minWaarde = 0;
    private int maxWaarde = 0;
    private int waarde = 0;
    private String eenheid = "";

    private PixelPlainLabel titelLabel;
    private JTextField textField;
    private PixelSlider slider;

    public SliderModule(String titel, int minWaarde, int maxWaarde, int waarde, String eenheid, Callable onChangeFunctie){
        super();
        this.eenheid = eenheid;
        this.onChangeFunctie = onChangeFunctie;

        //components aanmaken
        titelLabel = new PixelPlainLabel(titel);
        textField = new JTextField();
        slider = new PixelSlider(0,0,0);

        bouwLayout();

        //waarden instellen
        this.setMaxWaarde(maxWaarde);
        this.setMinWaarde(minWaarde);
        this.setWaarde(waarde);

        //listener voor slider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setWaarde(slider.getValue());
            }
        });
    }

    public SliderModule(String titel, int minWaarde, int maxWaarde, int waarde, String eenheid){
        this(titel,minWaarde,maxWaarde, waarde, eenheid, new Callable() { //lege callable meegeven! voor als er geen functie nodig is
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }

    public SliderModule(String titel, int minWaarde, int maxWaarde, int waarde){
        this(titel,minWaarde,maxWaarde, waarde, "");
    }

    public SliderModule(String titel, int minWaarde, int maxWaarde){
        this(titel,minWaarde,maxWaarde, maxWaarde);
    }

    public SliderModule(String titel){
        this(titel,0,0);
    }

    public SliderModule(){
        this("");
    }

    public void setMaxWaarde(int maxWaarde){
        //controle
        if(maxWaarde < minWaarde) maxWaarde = minWaarde;
        if(waarde > maxWaarde) waarde = maxWaarde;

        this.maxWaarde = maxWaarde;

        //stel slider en textbox in
        slider.setMaximum(this.maxWaarde);
        setWaarde(waarde);
    }

    public void setMinWaarde(int minWaarde){
        //controle
        if(minWaarde > maxWaarde) maxWaarde = minWaarde;
        if(waarde < minWaarde) waarde = minWaarde;

        this.minWaarde = minWaarde;

        //stel slider en textbox in
        slider.setMinimum(this.minWaarde);
        setWaarde(waarde);
    }

    public void setWaarde(int waarde){
        //controle
        if(waarde < minWaarde) waarde = minWaarde;
        if(waarde > maxWaarde) waarde = maxWaarde;

        this.waarde = waarde;

        //stel label in
        slider.setValue(this.waarde);
        textField.setText(String.valueOf(this.waarde) + eenheid);

        try {
            if(onChangeFunctie != null) onChangeFunctie.call();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setTitel(String titel){
        titelLabel.setText(titel);
        bouwLayout();
    }

    public void setEenheid(String eenheid){
        this.eenheid = eenheid;
    }

    private void bouwLayout(){
        this.removeAll();

        //componenten instellen
        textField.setEditable(false);
        Dimension textFieldSize = new Dimension(40, 24);
        textField.setMinimumSize(textFieldSize);
        textField.setPreferredSize(textFieldSize);
        textField.setMaximumSize(textFieldSize);
        textField.setBackground(Constanten.BALK_STREEP_KLEUR);
        textField.setBorder(new EmptyBorder(0,2,0,0));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(Constanten.PIXEL_FONT_KLEIN.getFont());
        textField.setForeground(Constanten.BALK_TEKST_KLEUR);

        titelLabel.setFont(Constanten.PIXEL_FONT_GROOT.getFont());
        titelLabel.setForeground(Constanten.BALK_TEKST_KLEUR);
        titelLabel.setBorder(new EmptyBorder(0,0,0,0));

        slider.setMaximumSize( new Dimension(200, 24));

        //componenten toevoegen, layout maken
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
        top.add(titelLabel);
        top.add(new Box.Filler(new Dimension (10,1),new Dimension (10,1),new Dimension (200 - textField.getWidth() - titelLabel.getWidth(),1)));
        top.add(textField);
        top.setAlignmentY(TOP_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(4,15,10,15));
        this.add(top);
        this.add(Box.createRigidArea(new Dimension (1,5)));
        this.add(Box.createVerticalGlue());
        this.add(slider);
    }

    public int getWaarde(){
        return waarde;
    }

    public int getMaxWaarde(){
        return maxWaarde;
    }

    public int getMinWaarde(){
        return minWaarde;
    }
}
