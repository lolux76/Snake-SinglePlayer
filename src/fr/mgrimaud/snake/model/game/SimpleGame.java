package fr.mgrimaud.snake.model.game;

/**
 *
 * @author Matéo Grimaud
 * @version 1.0.0
 */
public class SimpleGame extends Game{

    /**
     * @param maxTurn
     */
    public SimpleGame(int maxTurn, long time) {
        super(maxTurn, time);
    }

    @Override
    void initializeGame() {

    }

    @Override
    void takeTurn() {
        System.out.println("Tour " + getTurn() + " du jeu en cours");
    }

    @Override
    void gameOver() {

    }

    @Override
    boolean gameContinue() {
        return true;
    }
}
