package Pacman.Movement;

import Pacman.Figurer.Figurer;
import Pacman.Figurer.Ghosts.Ghost;
import Pacman.Figurer.Player;
import Pacman.Sprites;
import Pacman.Verden.Kart;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;

import java.util.Random;

/**
 * @author Sigve og Govert
 * Denne klassen styrer alt som har med å flytte på figurene å gjøre.
 */
public class Movement {
    private MovementState currentState;
    private MovementState[] movementStates = MovementState.values();
    private Random random = new Random();
    boolean firstMove = false;

    public void setCurrentState(MovementState currentState) {
        this.currentState = currentState;
    }

    public Movement() {
        this.currentState = MovementState.STILL;
    }

    /**
     * Endrer movementstaten i forhold til hvilket KeyEvent den får inn.
     * @param key tar inn ett KeyEvent(Tastetrykk).
     */
    public void move(KeyEvent key) {
        switch (key.getCode()){
            case W:
                currentState = MovementState.UP;
                break;
            case A:
                currentState = MovementState.LEFT;
                break;
            case S:
                currentState = MovementState.DOWN;
                break;
            case D:
                currentState = MovementState.RIGHT;
                break;
        }
    }

    private boolean checkWall(Figurer figur) {
        int intXIndex = 0;
        int intYIndex = 0;
        boolean down = false;
        switch (currentState) {
            case UP:
                intXIndex = (int)((figur.vector.getX()+(Kart.celleBredde/2))/Kart.celleBredde);
                intYIndex = (int)((figur.vector.getY()+(Kart.celleHoyde/2)-Kart.celleHoyde/4)/Kart.celleHoyde);
                break;
            case DOWN:
                intXIndex = (int)((figur.vector.getX()+(Kart.celleBredde/2))/Kart.celleBredde);
                intYIndex = (int)((figur.vector.getY()+(Kart.celleHoyde/2)+Kart.celleHoyde/4)/Kart.celleHoyde);
                down = true;
                break;
            case LEFT:
                intXIndex = (int)((figur.vector.getX()+(Kart.celleBredde/2)-Kart.celleBredde/4)/Kart.celleBredde);
                intYIndex = (int)((figur.vector.getY()+(Kart.celleHoyde/2))/Kart.celleHoyde);
                break;
            case RIGHT:
                intXIndex = (int)((figur.vector.getX()+(Kart.celleBredde/2)+Kart.celleBredde/4)/Kart.celleBredde);
                intYIndex = (int)((figur.vector.getY()+(Kart.celleHoyde/2))/Kart.celleHoyde);
                break;
        }
        if (Kart.kartArray[intYIndex][intXIndex] == Sprites.Sprite.WALL) {
            return true;
        } else if (Kart.kartArray[intYIndex][intXIndex] == Sprites.Sprite.DOOR && down == true) {
            return true;
        } else {
            return false;
        }
    }

    private MovementState randomDirection(){
        MovementState lastState = currentState;
        int state;
        do {
            state = random.nextInt(movementStates.length);
        }while (movementStates[state] == lastState && movementStates[state] != MovementState.STILL);
        return movementStates[state];
    }

    /**
     * Denne klassen kan flytte på alle figurer, opp, ned, høyre og venstre.
     * Den styrer også hvilken vei spøkelsene skal gå.
     * @param figur For eksempel et spøkelse eller spiller.
     */
    public void move(Figurer figur) {
        Point2D potensiellMove;
        Point2D velocity;
        //Hvis det ikke er et spøkelse
        if (!Ghost.class.isInstance(figur)) {
            switch (currentState) {
                case UP:
                    if (figur.figurBilde.getImage() != Sprites.getPacmanUpImg()) {
                        figur.getFigurBilde().setImage(Sprites.getPacmanUpImg());
                    }
                    break;
                case DOWN:
                    if (figur.figurBilde.getImage() != Sprites.getPacmanDownImg()) {
                        figur.getFigurBilde().setImage(Sprites.getPacmanDownImg());
                    }
                    break;
                case LEFT:
                    if (figur.figurBilde.getImage() != Sprites.getPacmanLeftImg()) {
                        figur.figurBilde.setImage(Sprites.getPacmanLeftImg());
                    }
                    break;
                case RIGHT:
                    if (figur.figurBilde.getImage() != Sprites.getPacmanRightImg()) {
                        figur.figurBilde.setImage(Sprites.getPacmanRightImg());
                    }
                    break;
                case STILL:
                    break;
            }
        }
        // Hvis det ikke er en spiller
        if (!Player.class.isInstance(figur)) {
            //Hvis det er det første steget spøkelset tar skal det alltid være opp for å komme seg ut fra start sonen som har en enveis-dør.
            if (!firstMove) {
                currentState = MovementState.UP;
                firstMove = true;
            }
        }
        //Flytter figurer i forhold til hvilken movementstate (enum) de har
        switch (currentState){
            case UP:
                velocity = new Point2D(0, -1.5);
                potensiellMove = figur.vector.add(velocity);
                if (!checkWall(figur)){
                    figur.vector = potensiellMove;
                    figur.getFigurBilde().setY(figur.vector.getY());
                } else {
                    recalculateMove(figur);
                }
                break;
            case DOWN:
                velocity = new Point2D(0, 1.5);
                potensiellMove = figur.vector.add(velocity);
                if (!checkWall(figur)){
                    figur.vector = potensiellMove;
                    figur.figurBilde.setY(figur.vector.getY());
                } else {
                    recalculateMove(figur);
                }
                break;
            case LEFT:
                velocity = new Point2D(-1.5, 0);
                potensiellMove = figur.vector.add(velocity);
                if (!checkWall(figur)){
                    figur.vector = potensiellMove;
                    figur.figurBilde.setX(figur.vector.getX());
                } else {
                    recalculateMove(figur);
                }
                break;
            case RIGHT:
                velocity = new Point2D(1.5, 0);
                potensiellMove = figur.vector.add(velocity);
                if (!checkWall(figur)){
                    figur.vector = potensiellMove;
                    figur.figurBilde.setX(figur.vector.getX());
                } else {
                    recalculateMove(figur);
                }
                break;
            case STILL:
                recalculateMove(figur);
                break;
        }
    }
    private void recalculateMove(Figurer figur) {
        if (!Ghost.class.isInstance(figur)){
            setCurrentState(MovementState.STILL);
        }
        // Hvis et spøkelse står stille så skal den flytte på seg igjen.
        else{
            this.currentState = randomDirection();
        }
    }
}
