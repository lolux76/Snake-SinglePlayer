package fr.mgrimaud.snake.model.agent;

import fr.mgrimaud.snake.model.agent.strategy.RandomStrategy;
import fr.mgrimaud.snake.model.agent.strategy.Strategy;
import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.utils.AgentAction;
import fr.mgrimaud.snake.utils.Position;

import java.util.ArrayList;

public abstract class Agent extends Entity {


    private Strategy strategy;

    private AgentAction lastAction;

    private final ArrayList<Position> snakeBodies;

    private Position mapSize;

    public Agent(Position position, SnakeGame game){
        super(position, game);
        this.strategy = new RandomStrategy();
        this.snakeBodies = new ArrayList<Position>();
        this.lastAction = AgentAction.MOVE_LEFT;
        this.mapSize = new Position(game.getSizeX(), game.getSizeY());
    }

    public Agent(Position position, SnakeGame game, Strategy strategy){
        super(position, game);
        this.strategy = strategy;
        this.snakeBodies = new ArrayList<Position>();
        this.lastAction = AgentAction.MOVE_LEFT;
        this.mapSize = new Position(game.getSizeX(), game.getSizeY());
    }

    public void changeStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    private AgentAction executeStrategy(){
        return strategy.execute();
    }

    private void moveAgent(){
        AgentAction action = executeStrategy();
        if(isLegalMove(action)) {
            snakeBodies.add(new Position(this.position.getX(), this.position.getY()));
            switch (action) {
                case MOVE_UP:
                    if(this.position.getY() - 1 < 0){
                        this.position.setY(game.getSizeY());
                    }
                    else {
                        this.position.setY(this.position.getY() - 1);
                    }
                    lastAction = AgentAction.MOVE_UP;
                    break;
                case MOVE_DOWN:
                    if(this.position.getY() + 1 > game.getSizeY()){
                        this.position.setY(0);
                    }
                    else {
                        this.position.setY(this.position.getY() + 1);
                    }
                    lastAction = AgentAction.MOVE_DOWN;
                    break;
                case MOVE_RIGHT:
                    if(this.position.getX() + 1 > game.getSizeX()){
                        this.position.setX(0);
                    }
                    else {
                        this.position.setX(this.position.getX() + 1);
                    }
                    lastAction = AgentAction.MOVE_RIGHT;
                    break;
                case MOVE_LEFT:
                    if(this.position.getX() - 1 < 0){
                        this.position.setX(game.getSizeX());
                    }
                    else {
                        this.position.setX(this.position.getX() - 1);
                    }
                    lastAction = AgentAction.MOVE_LEFT;
                    break;
                default:
                    break;
            }
        }

        manageOnBorderMove();

    }

    private boolean isLegalMove(AgentAction action)
    {
        switch (lastAction){
            case MOVE_UP: return !(action == AgentAction.MOVE_DOWN);
            case MOVE_DOWN: return !(action == AgentAction.MOVE_UP);
            case MOVE_LEFT: return !(action == AgentAction.MOVE_RIGHT);
            case MOVE_RIGHT: return  !(action == AgentAction.MOVE_LEFT);
            default: return false;
        }
    }

    private void manageOnBorderMove(){
        if(this.position.getX() < 1){
            this.position.setX(mapSize.getX() - 1);
        } else if (this.position.getX() > mapSize.getX() - 1) {
            this.position.setX(0);
        }
        if(this.position.getY() < 1){
            this.position.setY(mapSize.getY() - 1);
        } else if (this.position.getY() > mapSize.getY() - 1) {
            this.position.setY(0);
        }
    }

    public void takeTurn(){
        this.moveAgent();
    }

    public Position getPosition(){
        return this.position;
    }

    public AgentAction getLastAction(){
        return this.lastAction;
    }

    public ArrayList<Position> getSnakeBodies(){
        return this.snakeBodies;
    }
}
