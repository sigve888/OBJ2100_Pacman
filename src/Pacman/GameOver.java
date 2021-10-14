package Pacman;

import Pacman.Verden.Kart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class GameOver {
    private static Pane pane;
    private static Label labelScore;
    private static Font font = new Font("Magneto Bold", 48.0);
    private static ImageView logoBilde = new ImageView();
    private static ImageView gameOverBilde = new ImageView();

    /**
     * Endrer panet blir vist til en "gameover" skjerm.
     * @param main Tar inn main klassen for å få tilgang til timer og stackpanen.
     */
    public static void gameOver(Main main) {
        main.timer.cancel();
        Main.stackPane.getChildren().add(gameOverScreen());

    }

    /**
     * Setter pane med score og bilder.
     * @return Returnerer et ferdiglaget pane til "gameover" skjermen.
     */
    protected static Pane gameOverScreen() {
        pane = new Pane();

        logoBilde.setImage(Sprites.getLogoImg());
        logoBilde.setX(Kart.vindubredde/10);
        logoBilde.setY(Kart.vinduhøyde/10);
        logoBilde.setFitWidth(Kart.vindubredde-(Kart.vindubredde/6));
        logoBilde.setFitHeight((Kart.vinduhøyde/4));

        gameOverBilde.setImage(Sprites.getGameOverImg());
        gameOverBilde.setX(Kart.vindubredde/10);
        gameOverBilde.setY((Kart.vinduhøyde/2)-(Kart.vinduhøyde/8));
        gameOverBilde.setFitWidth(Kart.vindubredde-(Kart.vindubredde/6));
        gameOverBilde.setFitHeight((Kart.vinduhøyde/4));

        labelScore = new Label("Score: " + Score.getPoints());
        labelScore.setFont(font);
        labelScore.setTranslateY(gameOverBilde.getY() + gameOverBilde.getFitHeight());
        labelScore.setTranslateX(gameOverBilde.getX() + (gameOverBilde.getFitWidth()/3));
        pane.setPrefSize(Kart.vindubredde,Kart.vinduhøyde);
        pane.setStyle("-fx-background-color: black;");
        labelScore.setStyle("-fx-text-fill: yellow;");
        pane.getChildren().addAll(labelScore, logoBilde, gameOverBilde);
        return pane;
    }
}

