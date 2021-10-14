package Pacman.Verden;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Sigve og Govert
 * Klasse til worldbuilder, som er ansvarlig for å lage kart, og sette den sammen inn i et pane, for å kunne vises fram i vinduet.
 */
public class WorldBuilder {

   public static Kart kart;
   public Pane pane;
   public ImageView[][] bilder;

    /**
     * Constructor for world builder som lager nytt kart og pane, samt bilder, slik at det blir en ferdig bygget pane med kartet.
     */
   public WorldBuilder() {
       kart = new Kart();
       pane = new Pane();
       bilder = kart.getBildeArray();

       for (int rad = 0; rad < kart.getAntRader(); rad++){
           for (int kolonne = 0; kolonne < kart.getAntKolonner(); kolonne++){
               pane.getChildren().add(bilder[rad][kolonne]);
           }
       }
   }

}
