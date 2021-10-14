package Pacman.Figurer.Ghosts;

import Pacman.Sprites;
import javafx.geometry.Point2D;

/**
 * Klassen til det rosa spøkelset
 * @author Sigve og Govert
 */
public class pinkGhost extends Ghost{
    public pinkGhost(Point2D ghostVector) {
        super(ghostVector);
        this.figurBilde.setImage(Sprites.getGhost3Img());
    }
}
