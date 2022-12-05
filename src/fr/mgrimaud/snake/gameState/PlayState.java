package fr.mgrimaud.snake.gameState;

import fr.mgrimaud.snake.model.agent.Item;
import fr.mgrimaud.snake.model.agent.Snake;

import java.util.ArrayList;

public class PlayState extends GameState{

    public PlayState(){
        clickPlay = false;
        clickPause = true;
        clickStep = false;
        clickRestart = false;
    }
}
