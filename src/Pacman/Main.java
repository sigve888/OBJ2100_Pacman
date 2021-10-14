package Pacman;
import Pacman.Figurer.Ghosts.*;
import Pacman.Figurer.Player;
import Pacman.Verden.WorldBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.TimerTask;

/**
 * Pacman main metode.
 * @author Sigve - 233511 og Govert - 233565
 */
public class Main extends Application {
    public static StackPane stackPane;
    WorldBuilder world;
    private Player player;
    public Ghost ghost1;
    public Ghost ghost2;
    public Ghost ghost3;
    public Ghost ghost4;
    private int ghostCounter = 0;
    protected java.util.Timer timer;
    private void startTimer() {
        this.timer = new java.util.Timer();
        long frameTimeInMilliseconds = (long)(1000 / 60f);
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        player.update();
                        ghost1.update();
                        ghost2.update();
                        ghost3.update();
                        ghost4.update();
                        player.checkDistance(getMain(), frameTimeInMilliseconds);
                    }
                });
            }
        };
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     *
     * @return returnerer main objektet/klassen
     */
    private Main getMain() {
        return this;
    }

    /**
     *
     * @return Returnerer ghost1 objektet.
     */
    public Ghost getGhost1() {
        return ghost1;
    }

    /**
     *
     * @return Returnerer ghost1 objektet.
     */
    public Ghost getGhost2() {
        return ghost2;
    }

    /**
     *
     * @return Returnerer ghost1 objektet.
     */
    public Ghost getGhost3() {
        return ghost3;
    }

    /**
     *
     * @return Returnerer ghost1 objektet.
     */
    public Ghost getGhost4() {
        return ghost4;
    }

    private void initializer() {
        world = new WorldBuilder();
        player = new Player(world.kart.getPacmanSpawnVector()); // Tar inn vector hvor pacman skal starte
        Point2D[] ghostSpawnVector = world.kart.getGhostSpawnVectors();
        ghost1 = new orangeGhost(ghostSpawnVector[0]);
        ghost2 = new tealGhost(ghostSpawnVector[1]);
        ghost3 = new pinkGhost(ghostSpawnVector[2]);
        ghost4 = new redGhost(ghostSpawnVector[3]);
        world.pane.getChildren().addAll(player.getFigurBilde(), ghost1.getFigurBilde(), ghost2.getFigurBilde(), ghost3.getFigurBilde(), ghost4.getFigurBilde());
        startTimer();
    }

    /**
     * start metode til javaFX
     */
    @Override
    public void start(Stage vindu)  {
        initializer();

        stackPane = new StackPane();
        stackPane.getChildren().add(world.pane);
        Scene scene = new Scene(stackPane, world.kart.getVindubredde(), world.kart.getVinduhøyde()); // opprett en scene med layout
        vindu.setScene(scene); // tilordner scenen til programvinduet
        vindu.setTitle("Pacman Obligatorisk oppgave");
        vindu.show(); // Viser vinduet

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent -> {
            player.movement.move(keyEvent);
        }));
    }

    /**
     * main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
