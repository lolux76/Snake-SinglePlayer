package fr.mgrimaud.snake.gameState;

import fr.mgrimaud.snake.model.agent.Snake;

import java.util.ArrayList;

public abstract class GameState {

    boolean clickPlay;
    boolean clickPause;
    boolean clickRestart;
    boolean clickStep;

    public boolean isClickPlay() {
        return clickPlay;
    }

    public boolean isClickPause() {
        return clickPause;
    }

    public boolean isClickRestart() {
        return clickRestart;
    }

    public boolean isClickStep() {
        return clickStep;
    }

}
