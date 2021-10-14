package Pacman.Figurer.Ghosts;

import Pacman.Sprites;
import javafx.geometry.Point2D;

/**
 * Klasse for det røde spøkelset.
 */
public class redGhost extends Ghost {

    public redGhost(Point2D ghostVector) {
        super(ghostVector);
        this.figurBilde.setImage(Sprites.getGhost2Img());
    }
}
