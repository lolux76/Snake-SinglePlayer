package fr.mgrimaud.snake.model.agent;

import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.utils.Position;

public abstract class Item extends Entity{

    public Item(Position position, SnakeGame snake) {
        super(position, snake);
    }
}
