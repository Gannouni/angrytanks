package be.kdg.angrytanks.dom.io;

import be.kdg.angrytanks.dom.exceptions.AngryTanksException;
import be.kdg.angrytanks.dom.veld.Level;

import java.util.Map;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 04/02/14
 */
public interface LevelLezer {
    public Map<String, Level> lees() throws AngryTanksException;
}
