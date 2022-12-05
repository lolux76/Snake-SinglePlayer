package fr.mgrimaud.snake.controller;

import fr.mgrimaud.snake.model.game.SimpleGame;
import fr.mgrimaud.snake.view.ViewCommand;
import fr.mgrimaud.snake.view.ViewSimpleGame;

public class ControllerSimpleGame extends AbstractController{

    private final ViewSimpleGame viewSimpleGame;
    private final ViewCommand viewCommand;

    public ControllerSimpleGame(int maxTurn, long time) {
        super(new SimpleGame(maxTurn, time));
        this.viewSimpleGame = new ViewSimpleGame(getGame(), this);
        this.viewCommand = new ViewCommand(getGame(), this);
    }

}