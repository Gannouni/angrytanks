package be.kdg.angrytanks.dom.io;

import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.dom.veld.Level;
import be.kdg.angrytanks.dom.veld.Onderdeel;
import be.kdg.angrytanks.dom.veld.Positie;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */

/**
    Een FileLevelLezer is een LevelLezer die Levels kan lezen uit de levelbestanden in een map.
 */
public class FileLevelLezer implements LevelLezer
{
    private final static String LEVELFOLDER = "Levels";
    private final static String EXTENSIE = ".angrytanks";
    private File levelFolder;


    public FileLevelLezer() throws AngryTanksException {
        //FileSystem fs = FileSystems.getDefault();
        //levelFolder = fs.getPath(this.levelFolderNaam).toFile();
        levelFolder = new File(LEVELFOLDER);
        if(!levelFolder.isDirectory()) throw new AngryTanksException("Levelfolder bestaat niet!");
    }

    public HashMap<String, Level> lees() throws AngryTanksException{
        HashMap<String, Level> levelMap = new HashMap<String, Level>();

        ArrayList<File> levelFiles = new ArrayList<File>();

        //bestanden vinden in folder
        File[] files = levelFolder.listFiles();
        if(files == null) files = new File[0];

        //bestanden selecteren
        for(File file : files){
            if(file.isFile()
               && file.getName().length() > EXTENSIE.length()
               && file.getName().substring(file.getName().length()-EXTENSIE.length()).equals(EXTENSIE)
               && !file.getName().substring(0,1).equals(".")){
                levelFiles.add(file);
            }
        }

        //bestanden uitlezen en toevoegen aan levelMap
        for(File file : levelFiles){
            try{
                String levelTitel = poetsNaam(file.getName());
                String levelText = new Scanner(file).useDelimiter("\\A").next();
                levelMap.put(levelTitel, decodeLevel(levelText));

            } catch(IOException e){
                throw new AngryTanksException("Bestand niet gevonden", e);
            }
        }
        return levelMap;
    }

    private Level decodeLevel(String levelText){ //maakt van een tekststring een Level-object
        Level level = new Level();
        Scanner textScanner;

        //lijnvolgorde omkeren (om te kunnen loopen van onder naar boven, y = 0 op basislijn van level)
        levelText = revertLines(levelText);

        //standaardposities voor tanks
        Positie tankApos = new Positie(2, 2);
        Positie tankBpos = new Positie(6, 2);

        //standaardmarges
        int topMarge = 0, rightMarge = 0, bottomMarge = 0, leftMarge = 0;

        //over tekst loopen voor marges
        textScanner = new Scanner(levelText);
        for(int line = 0; textScanner.hasNextLine(); line++){
            String textLine = textScanner.nextLine();
            if(textLine.matches("\\[[0-9]+,[0-9]+,[0-9]+,[0-9]+\\]")){
                String pattern = "\\[([0-9]+),([0-9]+),([0-9]+),([0-9]+)\\]";
                topMarge = Integer.parseInt(textLine.replaceAll(pattern, "$1"));
                rightMarge = Integer.parseInt(textLine.replaceAll(pattern, "$2"));
                bottomMarge = Integer.parseInt(textLine.replaceAll(pattern, "$3"));
                leftMarge = Integer.parseInt(textLine.replaceAll(pattern, "$4"));
            }
        }

        //over tekst loopen voor elementen
        textScanner = new Scanner(levelText);
        int breedte = 0;
        int hoogte = 0;
        int hoogteReserve = 0;
        boolean addToHoogte = false;
        for(int y = 0; textScanner.hasNextLine(); y++){
            String textLine = textScanner.nextLine();
            if(addToHoogte) hoogteReserve++;
            for(int x = 0; x < textLine.length(); x++){
                char teken = textLine.charAt(x);
                if(teken == 'o' || teken == 'a' || teken == 'b'){
                    addToHoogte = true;
                    hoogte += hoogteReserve;
                    hoogteReserve = 0;

                    if(breedte < x){
                        breedte = x;
                    }
                    Positie positie = new Positie(x + leftMarge, y + bottomMarge);
                    switch (Character.toLowerCase(teken)){
                        case 'o': level.addOnderdeel(positie, Onderdeel.OBSTRUCTIE);
                            break;
                        case 'a': tankApos = positie;
                            break;
                        case 'b': tankBpos = positie;
                            break;
                    }
                }

            }
        }


        //hoogte en breedte instellen
        level.setBreedte(breedte + leftMarge + rightMarge);
        level.setHoogte(hoogte + bottomMarge + topMarge);

        //checks om tanks goed te zetten
        if(tankApos.getX() < 1) tankApos = new Positie(2, tankApos.getY());
        if(tankApos.getY() < 2) tankApos = new Positie(tankApos.getX(), 2);
        if(tankBpos.getX() < 1) tankBpos = new Positie(2, tankBpos.getY());
        if(tankBpos.getY() < 2) tankBpos = new Positie(tankBpos.getX(), 2);
        if(tankBpos.getX() <= tankApos.getX() + 2) tankBpos = new Positie(tankApos.getX() + 3, tankBpos.getY());

        //toevoegen van schutter-zone
        for(int x = -1; x <= 1; x++){
                level.addOnderdeel(new Positie(tankApos.getX() + x, tankApos.getY()), Onderdeel.SCHUTTER_A_ZONE);
                level.addOnderdeel(new Positie(tankBpos.getX() + x, tankBpos.getY()), Onderdeel.SCHUTTER_B_ZONE);
        }

        //toevoegen van schutters
        level.addOnderdeel(new Positie(tankApos.getX(), tankApos.getY() + 1), Onderdeel.SCHUTTER_A);
        level.addOnderdeel(new Positie(tankBpos.getX(), tankBpos.getY() + 1), Onderdeel.SCHUTTER_B);

        //grond toevoegen onder schutters
        for(int x = -1; x <= 1; x++){
            level.addOnderdeel(new Positie(tankApos.getX() + x, tankApos.getY() - 1), Onderdeel.OBSTRUCTIE);
            level.addOnderdeel(new Positie(tankBpos.getX() + x, tankBpos.getY() - 1), Onderdeel.OBSTRUCTIE);
        }

        return level;
    }

    public String poetsNaam(String naam){ //haalt extensie weg uit bestandsnaam, en vervangt underscores door spaties
        return naam.replace('_', ' ').replaceAll(EXTENSIE + "$", "");
    }

    private String revertLines(String str){ //hulpfunctie om volgorde van lijnen om te keren
        ArrayList<String> arrL = new ArrayList<String>();
        Scanner textScanner = new Scanner(str);
        while(textScanner.hasNextLine()){
            arrL.add(textScanner.nextLine());
        }
        Collections.reverse(arrL);

        StringBuilder sb = new StringBuilder();
        for(String arrStr : arrL){
            sb.append(arrStr);
            sb.append("\n");
        }

        return sb.toString();
    }
}
