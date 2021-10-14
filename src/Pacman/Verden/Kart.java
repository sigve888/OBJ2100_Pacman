package Pacman.Verden;

import Pacman.Sprites;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @Author Sigve og Govert
 * Klasse som har i hovedoppgave å opprette og ha funksjoner til å endre på kartet.
 */
public class Kart extends Group {

    private int antRader;
    private int antKolonner;
    private String kartNavn;
    private int ghostCounter = 0;

    private Point2D pacmanSpawnVector;
    private static Point2D[] ghostSpawnVectors;

    public static double vinduhøyde = 806.0;
    public static double vindubredde = 812.0;
    public static double celleBredde;
    public static double celleHoyde;

    public static Sprites.Sprite[][] kartArray;
    private static ImageView[][] bildeArray;

    /**
     * Constructor til Kartet
     */
    public Kart() {
        this.kartNavn = velgKart();
        ghostSpawnVectors = new Point2D[4];
        this.kartArray = lesKart();
        this.bildeArray = tegnKart();
    }

    /**
     *
     * @return Returnerer høyde på vinduet.
     */
    public double getVinduhøyde() {
        return vinduhøyde;
    }

    /**
     * @return Returnerer bredde på vinduet.
     */
    public double getVindubredde() {
        return vindubredde;
    }

    /**
     * @return returnerer Point2D posisjon av hvor pacman skal starte.
     */
    public Point2D getPacmanSpawnVector() {
        return pacmanSpawnVector;
    }

    protected int getAntRader() {
        return antRader;
    }

    /**
     * @return Returnerer antall kolonner.
     */
    protected int getAntKolonner() {
        return antKolonner;
    }

    /**
     * @return returnerer et array med Point2D posisjon av hvor de forskjellige spøkelsene skal starte.
     */
    public static Point2D[] getGhostSpawnVectors() {
        return ghostSpawnVectors;

    }

    /**
     * Tar inn to parametre for å endre del av kartet til "empty".
     * @param y Y indexen i array.
     * @param x X indexen i array.
     */
    public void setEmpty(int y, int x) {
        this.kartArray[x][y] = Sprites.Sprite.EMPTY;
        this.bildeArray[x][y].setImage(Pacman.Sprites.getEmptyImg());
    }

    /**
     * @return Returnerer array med alle imageview til bildene inkl posisjon.
     */
    protected ImageView[][] getBildeArray() {
        return bildeArray;
    }

    private String velgKart (){
        String filNavn = JOptionPane.showInputDialog(null,"Hvilket kart vil du laste inn?", "kart");
        return filNavn + ".txt";
    }

    //Oppretter og returnerer et array i riktig størrelse klar til utfylling
    private Sprites.Sprite[][] opprettKartArray()   {
        int antRader = 0;
        int antKolonner = 0;

        File filnavn = new File(kartNavn);
        Scanner scanner = null;
        try {
            scanner = new Scanner(filnavn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()){
            antRader++;
            String linje = scanner.nextLine();
            Scanner linjeScanner = new Scanner(linje);
            while (linjeScanner.hasNext()){
                antKolonner++;
                linjeScanner.next();
            }
            linjeScanner.close();
        }
        scanner.close();
        antKolonner = antKolonner/antRader;
        Sprites.Sprite[][] kartArray = new Sprites.Sprite[antRader][antKolonner];
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        return kartArray;
    }

    //Fyller inn arrayet med kartdata fra tekstfil og returnerer det.
    private Sprites.Sprite[][] lesKart()   {
        Sprites.Sprite[][] array = opprettKartArray();
        File filnavn = new File(kartNavn);
        int rad = 0;
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(filnavn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner2.hasNextLine()){
            String linje = scanner2.nextLine();
            Scanner linjeScanner = new Scanner(linje);
            int kolonne = 0;
            while (linjeScanner.hasNext()){
                String spriteVerdi = linjeScanner.next();
                switch (spriteVerdi) {
                    case "W": //Wall
                        array[rad][kolonne++] = Sprites.Sprite.WALL;
                        break;
                    case "E": //Empty
                        array[rad][kolonne++] = Sprites.Sprite.EMPTY;
                        break;
                    case "P": //Player
                        array[rad][kolonne++] = Sprites.Sprite.PLAYER;
                        break;
                    case "F": //Food
                        array[rad][kolonne++] = Sprites.Sprite.FOOD;
                        break;
                    case "B": //Big food
                        array[rad][kolonne++] = Sprites.Sprite.BIGFOOD;
                        break;
                    case "G": //Ghost (Spøkelse)
                        array[rad][kolonne++] = Sprites.Sprite.GHOST;
                        break;
                    case "D": //Dør (Dør til spøkelse spawn)
                        array[rad][kolonne++] = Sprites.Sprite.DOOR;
                        break;
                    case "T": //Dør (Dør til spøkelse spawn)
                        array[rad][kolonne++] = Sprites.Sprite.TELEPORTER;
                        break;
                }
            }
            linjeScanner.close();
            rad++;
        }
        scanner2.close();
        return array;
    }


    private ImageView[][] tegnKart(){

        celleBredde = vindubredde /antKolonner;
        celleHoyde = vinduhøyde /antRader;

        //Siden bildene endrer seg i størrelse basert på hvor mange det er, så kan det forekomme floatpoint feil, denne metoden fikser dette.
        if (celleBredde%1 != 0) {
            do {
                vindubredde++;
                celleBredde = vindubredde / antKolonner;
            } while (celleBredde % 1 != 0);
        }

        this.bildeArray = new ImageView[this.antRader][this.antKolonner];
        for (int rad = 0; rad < this.antRader; rad++){
            for (int kolonne = 0; kolonne < this.antKolonner; kolonne++){
                ImageView imageView = new ImageView();
                imageView.setX(kolonne * celleBredde);
                imageView.setY(rad * celleHoyde);
                imageView.setFitWidth(celleBredde);
                imageView.setFitHeight(celleHoyde);
                this.bildeArray[rad][kolonne] = imageView;
                this.getChildren().add(imageView);
            }
        }
        for (int r = 0; r < this.antRader; r++){
            for (int k = 0; k <this.antKolonner; k++){
                Sprites.Sprite sprite = kartArray[r][k];
                switch (sprite){
                    case WALL:
                        // Sjekker hvilke type vegg som skal vises i forhold til andre vegger.
                        // Hvis det ikke er øverst eller til venstre i kartet:
                        if ( r != 0 && k != 0) {
                            // Sjekker om det er innafor kart størrelsen, for å unngå indexoutofbounds (Altså ikke helt nederst eller helt til høyre).
                            if (k >= 0 && k < antKolonner-1 && r >= 0 && r < antRader-1) {
                                // Sjekker om det ikke er en vegg i ruten over
                                if(kartArray[r-1][k] != Sprites.Sprite.WALL){
                                    // Sjekker om det er et hjørne øverst til venstre
                                    if (kartArray[r][k+1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r][k-1] != Sprites.Sprite.WALL ) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcULWallImg());
                                    // Sjekker om det er et hjørne øverst til høyre
                                    } else if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r][k+1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcURWallImg());
                                    //Sjekker om det kan være en rett horisontal vegg
                                    } else if(kartArray[r][k+1] == Sprites.Sprite.WALL && kartArray[r][k-1] == Sprites.Sprite.WALL){
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                    } else{
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallImg());
                                    }
                                // Sjekker om det ikke er en vegg i ruten under
                                } else if (kartArray[r+1][k] != Sprites.Sprite.WALL){
                                    // Sjekker om det er et hjørne nederst til venstre
                                    if (kartArray[r][k+1] == Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL && kartArray[r][k-1] != Sprites.Sprite.WALL ) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcDLWallImg());
                                        // Sjekker om det er et hjørne nederst til høyre
                                    } else if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL && kartArray[r][k+1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcDRWallImg());
                                        //Sjekker om det kan være en rett horisontal vegg
                                    } else if(kartArray[r][k+1] == Sprites.Sprite.WALL && kartArray[r][k-1] == Sprites.Sprite.WALL){
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                    } else{
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallImg());
                                    }
                                //Sjekker om det er vegger rundt hele ruten.
                                } else if(kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL && kartArray[r][k+1] == Sprites.Sprite.WALL && kartArray[r][k-1] == Sprites.Sprite.WALL){
                                    //Sjekker om det er hjørne nederst til venstre (JEG SLAPP HER SIST)
                                    if(kartArray[r-1][k-1] != Sprites.Sprite.WALL){
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcDRWallImg());
                                    //Sjekker om det er et hjørne nederst til høyre
                                    }else if(kartArray[r-1][k+1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcDLWallImg());
                                    //Sjekker om det er et hjørne øverst til venstre
                                    } else if(kartArray[r+1][k-1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcURWallImg());
                                    //Sjekker om det er et hjørne øverst til høyre
                                    } else if(kartArray[r+1][k+1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcULWallImg());
                                    // Det eneste denne veggen kan være er en vegg som tilhører i midten.
                                    } else {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallMidImg());
                                    }
                                } else{
                                    //Det eneste dette kan være er en vertikal vegg.
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightVerImg());
                                }
                            // Hvis det er helt til høyre eller helt nederst
                            } else {
                                //Sjekker om det ikke er den helt øverste raden den sjekker
                                if(r >= 0 && r < antRader-1){
                                    //Sjekker om det er et hjørne øverst til høyre
                                    if(kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r+1][k-1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcURWallImg());
                                    //Sjekker om det er hjørnet nederst til høyre
                                    }else if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL && kartArray[r-1][k-1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcDRWallImg());
                                    //Sjekker om det er en verikal vegg
                                    } else if (kartArray[r][k-1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightVerImg());
                                    } else {
                                        // Hvis ikke så må det være en horisontal vegg.
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                    }
                                //Nederste rad i kartet
                                } else {
                                    //Om det er et hjørne nederst til venstre
                                    if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL){
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcDRWallImg());
                                    //Sjekker om det er venstre hjørne
                                    } else if (kartArray[r][k-1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcULWallImg());
                                    //Sjekker om det er høyre hjørne som ikke er helt til høyre
                                    } else if (k >= 0 && k < antKolonner-1 && kartArray[r][k+1] != Sprites.Sprite.WALL) {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getcURWallImg());
                                    //Hvis ikke så er det en horisontal vegg
                                    } else {
                                        this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                    }
                                }
                            }
                        // Hvis det ikke er helt til venstre
                        }else if (k !=0) {
                            // Sjekker om det er innafor kart størrelsen, for å unngå indexoutofbounds. (Her blir det øvertst på kartet)
                            if (k >= 0 && k < antKolonner-1 && r >= 0 && r < antRader-1) {
                                //Sjekker om det er et hjørne øverst til venstre
                                if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r+1][k-1] != Sprites.Sprite.WALL){
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getcURWallImg());
                                    //Sjekker om det er et hjørne øverst til høyre
                                } else if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r+1][k+1] != Sprites.Sprite.WALL) {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getcULWallImg());
                                // Blir ikke de andre oppfylt så må det være en horisontal vegg
                                } else {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                }
                            // Hvis den kommer hit blir det øverst til høyre.
                            } else {
                                //Sjekker om det blir et hjørne
                                if (kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL) {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getcURWallImg());
                                //Sjekker om det blir en horisontal linje
                                } else if(kartArray[r][k-1] == Sprites.Sprite.WALL && kartArray[r+1][k] != Sprites.Sprite.WALL){
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                //Sjekker om det blir en vertikal linje
                                } else if(kartArray[r][k-1] != Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL) {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightVerImg());
                                } else {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallImg());
                                }
                            }
                        // Hvis det er til venstre, men ikke helt i toppen
                        } else if (k == 0 && r != 0) {
                            // Sjekker om det er innafor kart størrelsen, for å unngå indexoutofbounds.
                            if (k >= 0 && k < antKolonner-1 && r >= 0 && r < antRader-1){
                                // Sjekker om det er et hjørne opp til venstre
                                if(kartArray[r][k+1] == Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r+1][k+1] != Sprites.Sprite.WALL){
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getcULWallImg());
                                // Sjekker om det kan være en vertikal vegg
                                } else if (kartArray[r][k+1] != Sprites.Sprite.WALL && kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL) {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightVerImg());
                                // Sjekker om det kan være en vegg nederst til venstre
                                } else if (kartArray[r-1][k+1] != Sprites.Sprite.WALL && kartArray[r-1][k] == Sprites.Sprite.WALL && kartArray[r][k+1] == Sprites.Sprite.WALL) {
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getcDLWallImg());
                                } else{
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallStraightHorImg());
                                }
                            } else{
                                this.bildeArray[r][k].setImage(Pacman.Sprites.getcDLWallImg()); // Nederst til venstre
                            }
                        // Hvis det er i toppen og helt til venstre
                        } else{
                            // Sjekker om det er innafor kart størrelsen, for å unngå indexoutofbounds.
                            if (k >= 0 && k < antKolonner-1 && r >= 0 && r < antRader-1){
                                if(kartArray[r+1][k] == Sprites.Sprite.WALL && kartArray[r][k+1] == Sprites.Sprite.WALL){
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getcULWallImg());
                                } else{
                                    this.bildeArray[r][k].setImage(Pacman.Sprites.getWallImg());
                                }
                            } else{
                                this.bildeArray[r][k].setImage(Pacman.Sprites.getWallImg());
                            }
                        }
                        break;
                    case EMPTY: //Empty
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getEmptyImg());
                        break;
                    case PLAYER: //Player
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getEmptyImg());
                        this.pacmanSpawnVector = new Point2D(this.bildeArray[r][k].getX(), this.bildeArray[r][k].getY());
                        break;
                    case FOOD: //Food
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getSmallFoodImg());
                        break;
                    case BIGFOOD: //Big food
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getBigFoodImg());
                        break;
                    case GHOST: //Ghost (Spøkelse)
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getEmptyImg());
                        Point2D spawnLocation = new Point2D(this.bildeArray[r][k].getX(), this.bildeArray[r][k].getY());
                        this.ghostSpawnVectors[ghostCounter] = spawnLocation;
                        ghostCounter++;
                        break;
                    case DOOR: //Dør (Dør til spøkelse spawn)
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getDoorImg());
                        break;
                    case TELEPORTER: //Dør (Dør til spøkelse spawn)
                        this.bildeArray[r][k].setImage(Pacman.Sprites.getEmptyImg());
                        break;
                }
            }
        }
        return bildeArray;
    }
}
