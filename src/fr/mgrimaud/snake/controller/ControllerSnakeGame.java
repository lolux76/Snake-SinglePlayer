package fr.mgrimaud.snake.controller;

import fr.mgrimaud.snake.model.agent.Snake;
import fr.mgrimaud.snake.model.game.SnakeGame;
import fr.mgrimaud.snake.model.input.InputMap;
import fr.mgrimaud.snake.view.PanelSnakeGame;
import fr.mgrimaud.snake.view.ViewCommand;
import fr.mgrimaud.snake.view.ViewSnakeGame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControllerSnakeGame extends AbstractController implements PropertyChangeListener {

    private int maxTurn;
    private long time;
    private String layoutPath;
    private InputMap inputMap;
    private PanelSnakeGame panelSnakeGame;
    private ViewSnakeGame viewSnakeGame;
    private final ViewCommand viewCommand;

    public ControllerSnakeGame(int maxTurn, long time, String layoutPath) throws Exception {
        super(new SnakeGame(maxTurn, time, new InputMap(layoutPath)));
        this.maxTurn = maxTurn;
        this.time = time;
        this.layoutPath = layoutPath;
        this.inputMap = new InputMap(layoutPath);
        this.panelSnakeGame = new PanelSnakeGame(inputMap.getSizeX(), inputMap.getSizeY(), inputMap.get_walls(), inputMap.getStart_snakes(), inputMap.getStart_items());
        this.viewSnakeGame = new ViewSnakeGame(getGame(), this, this.panelSnakeGame);
        this.viewCommand = new ViewCommand(getGame(), this);
        getGame().addPropertyChangeListener("gameOver", this);
    }

    public SnakeGame getGame(){
        return (SnakeGame) super.getGame();
    }

    /**
     * @param propertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        switch (propertyChangeEvent.getPropertyName()){
            case "turn" :
            case "gameState" :
            case "snake":
                break;

            case "gameOver":
                System.out.println("restartEvent");
                try {
                    super.setGame(new SnakeGame(maxTurn, time, new InputMap(layoutPath)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            default: System.err.println("Received a non supported property change event, data might not have been actualised");
                System.err.println("Event : " + propertyChangeEvent.getPropertyName());
                break;
        }
    }

}
