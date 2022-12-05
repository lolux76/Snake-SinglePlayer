package fr.mgrimaud.snake.gameState;

import fr.mgrimaud.snake.model.agent.Snake;

import java.util.ArrayList;

public class PauseState extends GameState{

    public PauseState(){
        clickPlay = true;
        clickPause = false;
        clickStep = true;
        clickRestart = true;
    }
}
