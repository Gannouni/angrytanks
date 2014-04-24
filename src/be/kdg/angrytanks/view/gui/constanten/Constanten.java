package be.kdg.angrytanks.view.gui.constanten;

import java.awt.*;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 16/02/14
 */

/*
    De Constanten-klasse is een soort inventaris van alle variabelen en objecten die doorheen de hele GUI gebruikt kunnen worden
 */

public final class Constanten {

    //fonts
    public static final Lettertype PIXEL_FONT_GROOT = new Lettertype("resources/fonts/Pixelade_Regular.ttf", 20f);
    public static final Lettertype PIXEL_FONT_KLEIN = new Lettertype("resources/fonts/04b03_Regular.ttf", 16f);

    //kleuren
    public static final Color BALK_BG_KLEUR = new Color(33, 49, 56);
    public static final Color BALK_STREEP_KLEUR = new Color(62, 82, 86);
    public static final Color BALK_STREEP_LICHTER_KLEUR = new Color(102, 122, 126);
    public static final Color BALK_ACCENT_KLEUR = new Color(128, 159, 145);
    public static final Color BALK_TEKST_KLEUR = new Color(221, 234, 228);

    //groottes
    public static final int BALK_HOOGTE = 70;
    public static final int BASIS_DIKTE = 2;

    //spelPanel graphics
    public static final Afbeelding MARKERING_PATTERN = new Afbeelding("resources/markering_pattern.gif");
    public static final Afbeelding SLASHES_LINKS = new Afbeelding("resources/slashes_left.gif");
    public static final Afbeelding SLASHES_RECHTS = new Afbeelding("resources/slashes_right.gif");
    public static final Afbeelding WIND_LINKS = new Afbeelding("resources/windpijl_links.gif");
    public static final Afbeelding WIND_RECHTS = new Afbeelding("resources/windpijl_rechts.gif");
    public static final Afbeelding WIND_NEUTRAAL = new Afbeelding("resources/windpijl_neutraal.gif");
    public static final Afbeelding TANK_LINKS = new Afbeelding("resources/tank_links.gif");
    public static final Afbeelding TANK_RECHTS = new Afbeelding("resources/tank_rechts.gif");
    public static final Afbeelding KRACHT = new Afbeelding("resources/kracht.gif");
    public static final Afbeelding HOEK = new Afbeelding("resources/hoek.gif");
    public static final Afbeelding ICOON_BOM = new Afbeelding("resources/icoon_bom.gif");
    public static final Afbeelding ICOON_TARGET = new Afbeelding("resources/icoon_target.gif");
    public static final Afbeelding ICOON_SKULL = new Afbeelding("resources/icoon_skull.gif");
    public static final Afbeelding ICOON_SAD = new Afbeelding("resources/icoon_sad.gif");
    public static final Afbeelding ICOON_WOLK = new Afbeelding("resources/icoon_wolk.gif");

    //graphics startOptiesPanel
    public static final Afbeelding FANCYTANK_A = new Afbeelding("resources/fancytank_left_crop.png");
    public static final Afbeelding FANCYTANK_B = new Afbeelding("resources/fancytank_right_crop.png");
    public static final Afbeelding COMBOBOX_PIJL = new Afbeelding("resources/combobox_arrow.gif");

    //graphics HoofdMenuPanel
    public static final Afbeelding HOOFDMENU_BG_TANK = new Afbeelding("resources/hoofdmenu_tank.png");
    public static final Afbeelding HOOFDMENU_BG_LANDSCHAP = new Afbeelding("resources/hoofdmenu_landschap.png");
    public static final Afbeelding HOOFDMENU_BG_LOGO = new Afbeelding("resources/hoofdmenu_logo.png");




    //maten landschap
    public static final int BLOKGROOTTE = 32;
    public static final int EENHEIDGROOTTE = 2;

    //graphics landschap
    public static final Afbeelding LUCHT = new Afbeelding("resources/landschap/lucht.png");

    //baan
    public static final Afbeelding BAAN_POEF = new Afbeelding("resources/landschap/baan_poef.gif");
    public static final Afbeelding BAAN_KOGEL_KLEIN = new Afbeelding("resources/landschap/kogel_klein.gif");
    public static final Afbeelding BAAN_EXPLOSIE = new Afbeelding("resources/landschap/explosie.gif");

    //rots
    public static final Afbeelding ROTS_NO_EDGE = new Afbeelding("resources/landschap/rock.gif"); //noEdge
    public static final Afbeelding ROTS_T = new Afbeelding("resources/landschap/rock_t.gif"); //topEdge
    public static final Afbeelding ROTS_R = new Afbeelding("resources/landschap/rock_r.gif"); //rightEdge
    public static final Afbeelding ROTS_TR = new Afbeelding("resources/landschap/rock_tr.gif"); //topRightEdge
    public static final Afbeelding ROTS_B = new Afbeelding("resources/landschap/rock_b.gif"); //bottomEdge
    public static final Afbeelding ROTS_TB = new Afbeelding("resources/landschap/rock_tb.gif"); //topBottomEdge
    public static final Afbeelding ROTS_RB = new Afbeelding("resources/landschap/rock_rb.gif"); //rightBottomEdge
    public static final Afbeelding ROTS_TRB = new Afbeelding("resources/landschap/rock_trb.gif"); //topRightBottomEdge
    public static final Afbeelding ROTS_L = new Afbeelding("resources/landschap/rock_l.gif"); //leftEdge
    public static final Afbeelding ROTS_TL = new Afbeelding("resources/landschap/rock_tl.gif"); //topLeftEdge
    public static final Afbeelding ROTS_RL = new Afbeelding("resources/landschap/rock_rl.gif"); //rightLeftEdge
    public static final Afbeelding ROTS_TRL = new Afbeelding("resources/landschap/rock_trl.gif"); //topRightLeftEdge
    public static final Afbeelding ROTS_BL = new Afbeelding("resources/landschap/rock_bl.gif"); //bottomLeftEdge
    public static final Afbeelding ROTS_TBL = new Afbeelding("resources/landschap/rock_tbl.gif"); //topBottomLeftEdge
    public static final Afbeelding ROTS_RBL = new Afbeelding("resources/landschap/rock_rbl.gif"); //rightBottomLeftEdge
    public static final Afbeelding ROTS_TRBL = new Afbeelding("resources/landschap/rock_trbl.gif"); //topRightBottomLeftEdge

    //wolk
    public static final Afbeelding WOLK_NO_EDGE = new Afbeelding("resources/landschap/cloud.gif"); //noEdge
    public static final Afbeelding WOLK_T = new Afbeelding("resources/landschap/cloud_t.gif"); //topEdge
    public static final Afbeelding WOLK_R = new Afbeelding("resources/landschap/cloud_r.gif"); //rightEdge
    public static final Afbeelding WOLK_TR = new Afbeelding("resources/landschap/cloud_tr.gif"); //topRightEdge
    public static final Afbeelding WOLK_B = new Afbeelding("resources/landschap/cloud_b.gif"); //bottomEdge
    public static final Afbeelding WOLK_TB = new Afbeelding("resources/landschap/cloud_tb.gif"); //topBottomEdge
    public static final Afbeelding WOLK_RB = new Afbeelding("resources/landschap/cloud_rb.gif"); //rightBottomEdge
    public static final Afbeelding WOLK_TRB = new Afbeelding("resources/landschap/cloud_trb.gif"); //topRightBottomEdge
    public static final Afbeelding WOLK_L = new Afbeelding("resources/landschap/cloud_l.gif"); //leftEdge
    public static final Afbeelding WOLK_TL = new Afbeelding("resources/landschap/cloud_tl.gif"); //topLeftEdge
    public static final Afbeelding WOLK_RL = new Afbeelding("resources/landschap/cloud_rl.gif"); //rightLeftEdge
    public static final Afbeelding WOLK_TRL = new Afbeelding("resources/landschap/cloud_trl.gif"); //topRightLeftEdge
    public static final Afbeelding WOLK_BL = new Afbeelding("resources/landschap/cloud_bl.gif"); //bottomLeftEdge
    public static final Afbeelding WOLK_TBL = new Afbeelding("resources/landschap/cloud_tbl.gif"); //topBottomLeftEdge
    public static final Afbeelding WOLK_RBL = new Afbeelding("resources/landschap/cloud_rbl.gif"); //rightBottomLeftEdge
    public static final Afbeelding WOLK_TRBL = new Afbeelding("resources/landschap/cloud_trbl.gif"); //topRightBottomLeftEdge

    //tankA
    public static final Afbeelding TANK_A_00 = new Afbeelding("resources/landschap/tank_a_00.gif"); //0°
    public static final Afbeelding TANK_A_10 = new Afbeelding("resources/landschap/tank_a_10.gif"); //10°
    public static final Afbeelding TANK_A_20 = new Afbeelding("resources/landschap/tank_a_20.gif"); //20°
    public static final Afbeelding TANK_A_30 = new Afbeelding("resources/landschap/tank_a_30.gif"); //30°
    public static final Afbeelding TANK_A_40 = new Afbeelding("resources/landschap/tank_a_40.gif"); //40°
    public static final Afbeelding TANK_A_50 = new Afbeelding("resources/landschap/tank_a_50.gif"); //50°
    public static final Afbeelding TANK_A_60 = new Afbeelding("resources/landschap/tank_a_60.gif"); //60°
    public static final Afbeelding TANK_A_70 = new Afbeelding("resources/landschap/tank_a_70.gif"); //70°
    public static final Afbeelding TANK_A_80 = new Afbeelding("resources/landschap/tank_a_80.gif"); //80°
    public static final Afbeelding TANK_A_90 = new Afbeelding("resources/landschap/tank_a_90.gif"); //90°
    public static final Afbeelding TANK_A_SCHROOT = new Afbeelding("resources/landschap/tank_a_schroot.gif"); //schroot


    //tankB
    public static final Afbeelding TANK_B_00 = new Afbeelding("resources/landschap/tank_b_00.gif"); //0°
    public static final Afbeelding TANK_B_10 = new Afbeelding("resources/landschap/tank_b_10.gif"); //10°
    public static final Afbeelding TANK_B_20 = new Afbeelding("resources/landschap/tank_b_20.gif"); //20°
    public static final Afbeelding TANK_B_30 = new Afbeelding("resources/landschap/tank_b_30.gif"); //30°
    public static final Afbeelding TANK_B_40 = new Afbeelding("resources/landschap/tank_b_40.gif"); //40°
    public static final Afbeelding TANK_B_50 = new Afbeelding("resources/landschap/tank_b_50.gif"); //50°
    public static final Afbeelding TANK_B_60 = new Afbeelding("resources/landschap/tank_b_60.gif"); //60°
    public static final Afbeelding TANK_B_70 = new Afbeelding("resources/landschap/tank_b_70.gif"); //70°
    public static final Afbeelding TANK_B_80 = new Afbeelding("resources/landschap/tank_b_80.gif"); //80°
    public static final Afbeelding TANK_B_90 = new Afbeelding("resources/landschap/tank_b_90.gif"); //90°
    public static final Afbeelding TANK_B_SCHROOT = new Afbeelding("resources/landschap/tank_b_schroot.gif"); //schroot


    //geluiden
    public static final Geluid MENU_MUZIEK = new Geluid("resources/geluiden/menu_muziek.wav");
    public static final Geluid INGAME_MUZIEK = new Geluid("resources/geluiden/ingame_muziek.wav");
    public static final Geluid GELUID_VUUR = new Geluid("resources/geluiden/explosie_vuur.wav");
    public static final Geluid GELUID_LAND = new Geluid("resources/geluiden/explosie_land.wav");
    public static final Geluid GELUID_RAAK = new Geluid("resources/geluiden/explosie_raak.wav");
    public static final Geluid GELUID_FINALE = new Geluid("resources/geluiden/finale.wav");

    //splashscreen
    public static final Afbeelding SPLASH_AFBEELDING = new Afbeelding("resources/splashscreen.png");

    //helptekst
    public static final Tekst HELPTEKST = new Tekst("resources/helpText.txt");

}
