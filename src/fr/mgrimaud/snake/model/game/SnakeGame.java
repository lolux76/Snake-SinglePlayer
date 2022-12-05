package fr.mgrimaud.snake.model.game;

import fr.mgrimaud.snake.model.agent.*;
import fr.mgrimaud.snake.model.input.InputMap;
import fr.mgrimaud.snake.utils.AgentType;
import fr.mgrimaud.snake.utils.FeaturesSnake;
import fr.mgrimaud.snake.utils.Position;
import fr.mgrimaud.snake.utils.TerminalColors;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class SnakeGame extends Game{

    private ArrayList<Snake> snakes;
    private ArrayList<Item> items;
    private  boolean[][] walls;
    private InputMap inputMap;
    private int sizeX;
    private int sizeY;
    private EntityFactory entityFactory;

    /**
     * @param maxTurn
     * @param inputMap
     */
    public SnakeGame(int maxTurn, long time, InputMap inputMap) {
        super(maxTurn, time);
        this.inputMap = inputMap;
        this.sizeX = inputMap.getSizeX();
        this.sizeY = inputMap.getSizeY();
        this.entityFactory = new EntityFactory();
        initializeGame();
    }

    @Override
    void initializeGame() {
        //Creating walls
        this.walls = inputMap.get_walls();
        this.snakes = new ArrayList<Snake>();
        this.items = new ArrayList<Item>();

        //Creating Snakes
        for (FeaturesSnake snakes : inputMap.getStart_snakes())
        {
            for(Position position : snakes.getPositions()){
                this.snakes.add(entityFactory.createAgent(position, AgentType.SNAKE, this, snakes.getColorSnake()));
            }
        }

        //Creating items
        /*for(FeaturesItem item: inputMap.getStart_items())
        {
            this.items.add( entityFactory.createAgent(new Position(item.getX(), item.getY()), item.getItemType(), this));
        }*/
    }

    @Override
    void takeTurn() {
        System.out.println("Tour " + getTurn() + " du jeu en cours");
        if(snakes.size() != 0) {
            try {
                for (Snake snake : snakes) {
                    snake.takeTurn();

                    System.out.println("Snake : " + snake.getColor().name() + " (" + snake.getPosition().getX() + ", " + snake.getPosition().getY() + ")");

                    //Dying on hitting a wall
                    if (walls[snake.getPosition().getX()][snake.getPosition().getY()]) {
                        support.firePropertyChange("snake", snakes, snakes.remove(snake));
                        continue;
                    }

                    //Manage snake encounter
                    manageEncounter(snake);
                }
            }
            catch (ConcurrentModificationException e) {
                }
        }
            gameOver();
    }

    private void manageEncounter(Snake snake){
        for (Snake otherSnake : snakes) {
            if (otherSnake != snake) {
                if (otherSnake.getPosition().getX() == snake.getPosition().getX() && otherSnake.getPosition().getY() == snake.getPosition().getY()) {
                    System.out.println("(" + otherSnake.getColor() + ", " + otherSnake.getSnakeBodies().size() + ") (" + snake.getColor() + ", " + snake.getSnakeBodies().size() + ")");
                    if (snake.getSnakeBodies().size() > otherSnake.getSnakeBodies().size()) {
                        support.firePropertyChange("snake", snakes, snakes.remove(otherSnake));
                    } else if (snake.getSnakeBodies().size() == otherSnake.getSnakeBodies().size()) {
                        support.firePropertyChange("snake", snakes, snakes.remove(snake));
                        support.firePropertyChange("snake", snakes, snakes.remove(otherSnake));
                    } else {
                        support.firePropertyChange("snake", snakes, snakes.remove(snake));
                    }
                } else {
                    for (Position position : otherSnake.getSnakeBodies()) {
                        if (position.getX() == snake.getPosition().getX() && position.getY() == snake.getPosition().getY()) {
                            System.out.println("(" + otherSnake.getColor() + ", " + otherSnake.getSnakeBodies().size() + ") (" + snake.getColor() + ", " + snake.getSnakeBodies().size() + ")");
                            if (snake.getSnakeBodies().size() > otherSnake.getSnakeBodies().size()) {
                                support.firePropertyChange("snake", snakes, snakes.remove(otherSnake));
                            } else if (snake.getSnakeBodies().size() == otherSnake.getSnakeBodies().size()) {
                                support.firePropertyChange("snake", snakes, snakes.remove(snake));
                                support.firePropertyChange("snake", snakes, snakes.remove(otherSnake));
                            } else {
                                support.firePropertyChange("snake", snakes, snakes.remove(snake));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    void gameOver() {
        if(snakes.size() == 1){
            System.out.println(TerminalColors.GREEN + "Victoire du joueur : " + TerminalColors.YELLOW + snakes.get(0).getColor().name() + TerminalColors.RESET);
            restart();
        }
        if(snakes.size() == 0){
            System.out.println(TerminalColors.BLUE + "Partie nulle : " + TerminalColors.RESET);
            restart();
        }
    }

    void restart(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        support.firePropertyChange("gameOver", false, true);
    }

    @Override
    boolean gameContinue() {
        return true;
    }

    public boolean[][] getWalls() {
        return walls;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

}
