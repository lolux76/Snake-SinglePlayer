package fr.mgrimaud.snake.model.agent;

import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.utils.Position;

public abstract class Entity {
    Position position;
    SnakeGame game;

    public Entity(Position position, SnakeGame game)
    {
        this.position = position;
        this.game = game;
    }
}
