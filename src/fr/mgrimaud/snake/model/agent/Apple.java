package fr.mgrimaud.snake.model.agent;

import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.utils.Position;

public class Apple extends Item{

    public Apple(Position position, SnakeGame game) {
        super(position, game);
    }
}
