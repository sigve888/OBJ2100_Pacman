package Pacman;

import Pacman.Figurer.Player;
import javafx.scene.image.Image;

/**
 * @author Sigve og Govert
 * Denne klassen gjør bildene om til Image-variabler og har gettere for dem.
 */

public class Sprites {
    //Bilder som tilhører Pacman
    private static Image pacmanDownImg = new Image(Sprite.class.getResourceAsStream("/Sprites/pacmanDown.gif"));
    private static Image pacmanLeftImg = new Image(Sprite.class.getResourceAsStream("/Sprites/pacmanLeft.gif"));
    private static Image pacmanUpImg = new Image(Player.class.getResourceAsStream("/Sprites/pacmanUp.gif"));
    private static Image pacmanRightImg = new Image(Sprite.class.getResourceAsStream("/Sprites/pacmanRight.gif"));
    //Bilder som tilhører Spøkelser
    private static Image ghost1Img = new Image(Sprite.class.getResourceAsStream("/Sprites/ghost1.png"));
    private static Image ghost2Img = new Image(Sprite.class.getResourceAsStream("/Sprites/ghost2.png"));
    private static Image ghost3Img = new Image(Sprite.class.getResourceAsStream("/Sprites/ghost3.png"));
    private static Image ghost4Img = new Image(Sprite.class.getResourceAsStream("/Sprites/ghost4.png"));
    private static Image spookedGhostImg = new Image(Sprite.class.getResourceAsStream("/Sprites/spooked-ghost.png"));
    //Bilder som viser en tom rute
    private static Image emptyImg = new Image(Sprite.class.getResourceAsStream("/Sprites/empty.png"));
    //Bilder som tilhører Bilder som tilhører veggene.
    private static Image wallImg = new Image(Sprite.class.getResourceAsStream("/Sprites/wall.png"));
    private static Image wallMidImg = new Image(Sprite.class.getResourceAsStream("/Sprites/wallMid.png"));
    private static Image cULWallImg = new Image(Sprite.class.getResourceAsStream("/Sprites/cULWallImg.png"));
    private static Image cURWallImg = new Image(Sprite.class.getResourceAsStream("/Sprites/cURWallImg.png"));
    private static Image wallStraightVerImg = new Image(Sprite.class.getResourceAsStream("/Sprites/wallStraightVer.png"));
    private static Image wallStraightHorImg = new Image(Sprite.class.getResourceAsStream("/Sprites/wallStraightHor.png"));
    private static Image cDLWallImg = new Image(Sprite.class.getResourceAsStream("/Sprites/cDLWallImg.png"));
    private static Image cDRWallImg = new Image(Sprite.class.getResourceAsStream("/Sprites/cDRWallImg.png"));
    private static Image doorImg = new Image(Sprite.class.getResourceAsStream("/Sprites/doorImg.png"));
    //Bilder som tilhører mat
    private static Image smallFoodImg = new Image(Sprite.class.getResourceAsStream("/Sprites/smallfood.png"));
    private static Image bigFoodImg = new Image(Sprite.class.getResourceAsStream("/Sprites/bigfood.png"));
    //Bilder som tilhører Game over skjermen
    private static Image gameOverImg = new Image(Sprite.class.getResourceAsStream("/Sprites/game_over.png"));
    private static Image logoImg = new Image(Sprite.class.getResourceAsStream("/Sprites/PacmanLogo.png"));

    /**
     *
     * @return returnerer bilde som viser at pacman går ned.
     */
    public static Image getPacmanDownImg() {
        return pacmanDownImg;
    }

    /**
     *
     * @return returnerer doorImg-bildet som viser spillets dør
     */

    public static Image getDoorImg() {
        return doorImg;
    }

    /**
     *
     * @return returnerer pacmanLeftImg-bildet som vises når pacman gåt til venstre.
     */

    public static Image getPacmanLeftImg() {
        return pacmanLeftImg;
    }

    /**
     *
     * @return returnerer pacmanUpImg-bildet som vises når pacman går opp,
     */

    public static Image getPacmanUpImg() {
        return pacmanUpImg;
    }

    /**
     *
     * @return returnerer pacmanRightImg-bildet som vises når pacman går til venstre.
     */

    public static Image getPacmanRightImg() {
        return pacmanRightImg;
    }

    /**
     *
     * @return returnerer wallImg-bildet som viser veggen.
     */

    public static Image getWallImg() {
        return wallImg;
    }

    /**
     *
     * @return returnerer wallMidImg-bildet som viser en stjerne inni vegg-områdene
     */

    public static Image getWallMidImg() {
        return wallMidImg;
    }

    /**
     *
     * @return returnerer emtyImg-bildet som viser ingenting (sort)
     */

    public static Image getEmptyImg() {
        return emptyImg;
    }

    /**
     *
     * @return returnerer ghost1Img-bildet som viser et spøkelse
     */

    public static Image getGhost1Img() {
        return ghost1Img;
    }

    /**
     *
     * @return returnerer ghost2Img-bildet som viser et spøkelse
     */

    public static Image getGhost2Img() {
        return ghost2Img;
    }

    /**
     *
     * @return returnerer ghost3Img-bildet som viser et spøkelse
     */

    public static Image getGhost3Img() {
        return ghost3Img;
    }

    /**
     *
     * @return returnerer SpooketGhostImg-bildet som viser når spøkelsene er dødelige(blå)
     */

    public static Image getSpookedGhostImg() {
        return spookedGhostImg;
    }

    /**
     *
     * @return returnerer SmallFoodImg-bildet som viser vanlig mat
     */

    public static Image getSmallFoodImg() {
        return smallFoodImg;
    }

    /**
     *
     * @return returnerer BigFoodImg-bildet som viser stor mat
     */

    public static Image getBigFoodImg() {
        return bigFoodImg;
    }

    /**
     *
     * @return returnerer cULWallImg-bildet som viser veggen oppe i venstre hjørne
     */

    public static Image getcULWallImg() {
        return cULWallImg;
    }

    /**
     *
     * @return returnerer cURWallImg-bildet som viser veggen oppe i høyre hjørne
     */

    public static Image getcURWallImg() {
        return cURWallImg;
    }

    /**
     *
     * @return returnerer WallStraightVerImg-bildet som viser vegg som går vertikalt
     */

    public static Image getWallStraightVerImg() {
        return wallStraightVerImg;
    }

    /**
     *
     * @return returnerer WallStraightHorImg-bildet som viser vegg som går horisontalt
     */

    public static Image getWallStraightHorImg() {
        return wallStraightHorImg;
    }

    /**
     *
     * @return returnerer cDLWallImg-bildet som viser veggene nede i venstre hjørne
     */

    public static Image getcDLWallImg() {
        return cDLWallImg;
    }

    /**
     *
     * @return returnerer cDRWallImg-bildet som viser veggene nede i høyre hjørne
     */

    public static Image getcDRWallImg() {
        return cDRWallImg;
    }

    /**
     *
     * @return returnerer ghost4Img-bildet som viser et spøkelse
     */

    public static Image getGhost4Img() {
        return ghost4Img;
    }

    /**
     *
     * @return returnerer GameOverImg-bildet som vises når spillet er over
     */

    public static Image getGameOverImg() {
        return gameOverImg;
    }

    /**
     *
     * @return returnerer getLogoImg-bildet som viser Pac Man spillets logo
     */

    public static Image getLogoImg() {
        return logoImg;
    }

    /**
     * Enum som tar vare på alle typer av spriter.
     */
    public static enum Sprite {
        WALL, EMPTY, PLAYER, FOOD, BIGFOOD, GHOST, DOOR, TELEPORTER;
    }
}
