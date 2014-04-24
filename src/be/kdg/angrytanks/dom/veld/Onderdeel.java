package be.kdg.angrytanks.dom.veld;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 08/02/14
 */
/* Onderdeel is een enum met verschillende soorten onderdelen van een Level.
   De Level-klasse maakt hiervan gebruik om een level te beschrijven.

   * OBSTRUCTIE is bedoeld voor alle posities waarop zich een obstructie bevindt,

   * SCHUTTER_A en SCHUTTER_B zijn bedoeld voor de exacte posities van beide schutters (van waaruit geschoten wordt),

   * SCHUTTER_A_ZONE en SCHUTTER_B_ZONE zijn bedoeld voor de omkaderende posities die ook tellen bij de schutter, en tevens geraakt kunnen worden.
         bvb:   A
               ZZZ
               (waar A = SCHUTTER_A en Z = SCHUTTER_A_ZONE)
*/
public enum Onderdeel{
    OBSTRUCTIE, SCHUTTER_A, SCHUTTER_A_ZONE, SCHUTTER_B, SCHUTTER_B_ZONE
}
