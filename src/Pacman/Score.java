package Pacman;

/**
 * Klasse som tar vare på poengene spilleren tjener i spillet
 * @author Sigve og Govert
 */
public class Score {
    private static int points;

    /**
     * Constructor for poeng
     */
    public Score(){
        this.points = 0;
    }

    /**
     * Metode for å legge poeng til i den totale poengsummen
     * @param points Antall poeng du får.
     */
    public void scorePoints(int points) {
        this.points = this.points + points;
    }

    /**
     * Metode for å returnere den totale poengsummen
     * @return Returnerer total poengsum
     */
    protected static int getPoints() {
        return points;
    }
}
