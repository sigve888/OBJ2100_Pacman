package Pacman.Figurer;

import Pacman.Verden.Kart;
import Pacman.Movement.Movement;
import Pacman.Verden.Teleporter;
import Pacman.Updateable;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * @author Sigve og Govert
 */
public abstract class Figurer implements Updateable {

    public  Point2D vector;
    public ImageView figurBilde;
    public Movement movement;
    private Teleporter teleporter;

    /**
     *
     * @param vector Viser poisisjon i Point2D
     * @param figurBilde Har bildet til figuren lagret i et ImageView objekt.
     * @param movement Viser hvilken vei en figur går i enum.
     */
    public Figurer(Point2D vector, ImageView figurBilde, Movement movement) {
        this.vector = vector;
        this.figurBilde = figurBilde;
        this.movement = movement;
        this.teleporter = new Teleporter(vector);
    }

    /**
     * Sjekker om du er på en teleporter, så endrer den posisjonnen til andre siden av kartet hvis du er det.
     */
    protected void sjekkTeleport() {
        if (teleporter.teleport(vector)) {
            // if venstre teleporter
            if (vector.getX() < Kart.vindubredde/2){
                vector = new Point2D(Kart.vindubredde-(Kart.celleBredde * 1.5),vector.getY());
            } else {
                vector = new Point2D(0 + (Kart.celleBredde * 1.5), vector.getY());
            }
            //if høyre teleporter
            figurBilde.setX(vector.getX());
            figurBilde.setY(vector.getY());
        }
    }

    /**
     *
     * @return returnerer bildet som en figur tar i bruk.
     */
    public abstract ImageView getFigurBilde();

}


