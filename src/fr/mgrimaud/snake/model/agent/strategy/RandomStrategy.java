package fr.mgrimaud.snake.model.agent.strategy;

import fr.mgrimaud.snake.utils.AgentAction;

import java.util.Random;

public class RandomStrategy implements Strategy{
    /**
     *
     */
    @Override
    public AgentAction execute() {
        return AgentAction.values()[new Random().nextInt(AgentAction.values().length)];
    }
}
