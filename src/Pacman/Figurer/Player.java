package Pacman.Figurer;

import Pacman.*;
import Pacman.Figurer.Ghosts.Ghost;
import Pacman.Movement.Movement;
import Pacman.Movement.MovementState;
import Pacman.Verden.Kart;
import Pacman.Verden.WorldBuilder;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * Klassen til Player (Spilleren)
 * Inneholder metoder slik som spising, og spøkelses-spising-modus.
 * @author Sigve og Govert
 */
public class Player extends Figurer {
    private Score score;
    private boolean ghostEatingMode = false;
    private long tid;

    public Player(Point2D playerVector) {
        super(playerVector, new ImageView(), new Movement());
        score = new Score();
        this.figurBilde.setX(playerVector.getX());
        this.figurBilde.setY(playerVector.getY());
        this.figurBilde.setImage(Sprites.getPacmanLeftImg());
        this.figurBilde.setFitWidth(Kart.celleBredde);
        this.figurBilde.setFitHeight(Kart.celleHoyde);
    }

    /**
     * Oppdaterer hvor spilleren vil gå, og sjekker om pacman kan spise eller teleportere seg.
     */
    @Override
    public void update() {
        movement.move(this);
        eat();
        sjekkTeleport();
    }

    private void eat() {
        int intXIndex = (int)((vector.getX()+(Kart.celleBredde/2))/Kart.celleBredde);
        int intYIndex = (int)((vector.getY()+(Kart.celleHoyde/2))/Kart.celleHoyde);
        if (Kart.kartArray[intYIndex][intXIndex] == Sprites.Sprite.FOOD) {
            WorldBuilder.kart.setEmpty(intXIndex,intYIndex);
            score.scorePoints(100);
        }
        if (Kart.kartArray[intYIndex][intXIndex] == Sprites.Sprite.BIGFOOD) {
            WorldBuilder.kart.setEmpty(intXIndex,intYIndex);
            score.scorePoints(400);
            setGhostEatingMode();
        }
    }

    private void setGhostEatingMode(){
        tid = 10000;
        ghostEatingMode = true;
    }

    public ImageView getFigurBilde() {
        return figurBilde;
    }

    /**
     * Metode for å sjekke avstand mellom spiller og spøkelse slik at det blir game over hvis de kommer for nærme hverandre, eller om pacman kan spise et spøkelse slik at det respawner der den først ble plassert hvis pacman har spist en stor mat.
     * @param main tar inn main klasse/objekt for å kunne regne ut avstanden mellom to forskjellige objekter.
     * @param tid Tar inn tid objekt i form av long som vil si frametimen, for å kunne regne ut hvor lenge ghosteating mode skal vare i millisekunder.
     */
    public void checkDistance(Main main, long tid){
        if (this.tid <= 0) {
            ghostEatingMode = false;
            Ghost.changeToNormal(main);
        } else {
            this.tid -= tid;
            Ghost.changeToSpooked(main);
        }

        if (vector.distance(main.getGhost1().vector) < ((Kart.celleBredde + Kart.celleHoyde)/2)/1.2){
            if(!ghostEatingMode){
                GameOver.gameOver(main);
            } else {
                main.ghost1.vector = Kart.getGhostSpawnVectors()[0];
                main.ghost1.figurBilde.setX(Kart.getGhostSpawnVectors()[0].getX());
                main.ghost1.figurBilde.setY(Kart.getGhostSpawnVectors()[0].getY());
                main.ghost1.movement.setCurrentState(MovementState.UP);
            }
        } else if (vector.distance(main.getGhost2().vector) < ((Kart.celleBredde + Kart.celleHoyde)/2)/1.2) {
            if(!ghostEatingMode){
                GameOver.gameOver(main);
            } else {
                main.ghost2.vector = Kart.getGhostSpawnVectors()[1];
                main.ghost2.figurBilde.setX(Kart.getGhostSpawnVectors()[1].getX());
                main.ghost2.figurBilde.setY(Kart.getGhostSpawnVectors()[1].getY());
                main.ghost2.movement.setCurrentState(MovementState.UP);
            }
        } else if (vector.distance(main.getGhost3().vector) < ((Kart.celleBredde + Kart.celleHoyde)/2)/1.2) {
            if(!ghostEatingMode){
                GameOver.gameOver(main);
            } else {
                main.ghost3.vector = Kart.getGhostSpawnVectors()[2];
                main.ghost3.figurBilde.setX(Kart.getGhostSpawnVectors()[2].getX());
                main.ghost3.figurBilde.setY(Kart.getGhostSpawnVectors()[2].getY());
                main.ghost3.movement.setCurrentState(MovementState.UP);
            }
        } else if (vector.distance(main.getGhost4().vector) < ((Kart.celleBredde + Kart.celleHoyde)/2)/1.2) {
            if(!ghostEatingMode){
                GameOver.gameOver(main);
            }else {
                main.ghost4.vector = Kart.getGhostSpawnVectors()[3];
                main.ghost4.figurBilde.setX(Kart.getGhostSpawnVectors()[3].getX());
                main.ghost4.figurBilde.setY(Kart.getGhostSpawnVectors()[3].getY());
                main.ghost4.movement.setCurrentState(MovementState.UP);
            }
        }
    }
}
