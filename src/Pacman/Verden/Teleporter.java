package Pacman.Verden;

import Pacman.Sprites;
import javafx.geometry.Point2D;

/**
 * Klassen for Teleporter
 * @author Sigve og Govert
 */
public class Teleporter {

    private Point2D vector;

    /**
     * Constructor for ny teleporter.
     * @param vector tar inn Point2D posisjon
     */
    public Teleporter(Point2D vector){
        this.vector = vector;
    }

    /**
     * Metode for å sjekke om en figur er på en teleporter i kartet.
     * @param vector Point2D Posisjonen til figur
     * @return Returnerer true hvis figuren er på toppen av en teleporter i kartet.
     */
    public boolean teleport(Point2D vector) {
        int intXIndex = (int)((vector.getX()+(Kart.celleBredde/2))/Kart.celleBredde);
        int intYIndex = (int)((vector.getY()+(Kart.celleHoyde/2))/Kart.celleHoyde);
        if (Kart.kartArray[intYIndex][intXIndex] == Sprites.Sprite.TELEPORTER) {
            return true;
        }
        else return false;
    }
}

