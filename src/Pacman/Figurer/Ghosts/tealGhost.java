package Pacman.Figurer.Ghosts;

import Pacman.Sprites;
import javafx.geometry.Point2D;

/**
 * @author Sigve og Govert
 * Klasse for tealGhost.
 */
public class tealGhost extends Ghost{
    public tealGhost(Point2D ghostVector) {
        super(ghostVector);
        this.figurBilde.setImage(Sprites.getGhost4Img());
    }
}
