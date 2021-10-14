package Pacman.Figurer.Ghosts;

import Pacman.Figurer.Figurer;
import Pacman.Verden.Kart;
import Pacman.Main;
import Pacman.Movement.Movement;
import Pacman.Sprites;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * @Author Sigve og Govert
 */
public class Ghost extends Figurer {
    /**
     * Constructor til spøkelsene
     * @param ghostVector Tar inn start posisjon til ghost.
     */
    public Ghost(Point2D ghostVector) {
        super(ghostVector, new ImageView(), new Movement());
        this.figurBilde.setX(ghostVector.getX());
        this.figurBilde.setY(ghostVector.getY());
        this.figurBilde.setFitWidth(Kart.celleBredde);
        this.figurBilde.setFitHeight(Kart.celleHoyde);
    }

    /**
     *
     * @return returnerer bildet som tilhører spøkels.
     */
    public ImageView getFigurBilde() {
        return figurBilde;
    }

    /**
     * Denne funksjonen ser om spøkelsene er på en teleporter, og forteller dem at de skal flytte på seg.
     */
    public void update() {
        movement.move(this);
        sjekkTeleport();
    }

    /**
     * Endrer bildene til Spooket ghost image
     * @param main Tar inn main klassen slik at den kan endre på bildene til de forskjellge spøkelsene
     */
    public static void changeToSpooked(Main main) {
        main.ghost1.figurBilde.setImage(Sprites.getSpookedGhostImg());
        main.ghost2.figurBilde.setImage(Sprites.getSpookedGhostImg());
        main.ghost3.figurBilde.setImage(Sprites.getSpookedGhostImg());
        main.ghost4.figurBilde.setImage(Sprites.getSpookedGhostImg());
    }

    /**
     * Endrer bildene til normale ghost images
     * @param main Tar inn main klassen slik at den kan endre på bildene til de forskjellge spøkelsene
     */
    public static void changeToNormal(Main main) {
        main.ghost1.figurBilde.setImage(Sprites.getGhost1Img());
        main.ghost2.figurBilde.setImage(Sprites.getGhost2Img());
        main.ghost3.figurBilde.setImage(Sprites.getGhost3Img());
        main.ghost4.figurBilde.setImage(Sprites.getGhost4Img());
    }
}
