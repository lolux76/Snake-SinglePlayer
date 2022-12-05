package fr.mgrimaud.snake.model.agent;

import fr.mgrimaud.snake.model.agent.strategy.Strategy;
import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.utils.ColorSnake;
import fr.mgrimaud.snake.utils.Position;

import java.util.ArrayList;

public class Snake extends Agent{

    private final ColorSnake color;

    public Snake(Position position, SnakeGame game, ColorSnake color) {
        super(position, game);
        this.color = color;
    }

    public Snake(Position position, SnakeGame game, Strategy stragegy, ColorSnake color){
        super(position, game, stragegy);
        this.color = color;
    }

    public ColorSnake getColor() {
        return this.color;
    }
}
