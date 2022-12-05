package fr.mgrimaud.snake.model.agent;

import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.utils.AgentType;
import fr.mgrimaud.snake.utils.ColorSnake;
import fr.mgrimaud.snake.utils.ItemType;
import fr.mgrimaud.snake.utils.Position;

public class EntityFactory {
    public Snake createAgent(Position position, AgentType agentType, SnakeGame game, ColorSnake color)
    {
        switch (agentType){
            case SNAKE:
                return new Snake(position, game, color);
            default: return null;
        }
    }

    public Item createAgent(Position position, ItemType itemType, SnakeGame game) {
        switch (itemType){
            case APPLE: return new Apple(position, game);
            default: return null;
        }
    }
}
