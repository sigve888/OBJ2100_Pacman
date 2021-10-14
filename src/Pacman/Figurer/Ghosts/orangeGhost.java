package Pacman.Figurer.Ghosts;

import Pacman.Sprites;
import javafx.geometry.Point2D;

/**
 * @author Sigve og Govert
 * Klassen til det oransje spøkelse
 */
public class orangeGhost extends Ghost{
    public orangeGhost(Point2D ghostVector) {
        super(ghostVector);
        this.figurBilde.setImage(Sprites.getGhost1Img());
    }
}
