package fr.mgrimaud.snake.view;

import fr.mgrimaud.snake.controller.ControllerSnakeGame;
import fr.mgrimaud.snake.model.agent.Snake;
import fr.mgrimaud.snake.model.game.Game;
import fr.mgrimaud.snake.utils.FeaturesItem;
import fr.mgrimaud.snake.utils.FeaturesSnake;
import fr.mgrimaud.snake.utils.Position;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ViewSnakeGame implements PropertyChangeListener {

    private Game game;
    private ControllerSnakeGame controller;
    private PanelSnakeGame panelSnakeGame;

    /**
     * @param game
     */
    public ViewSnakeGame(Game game, ControllerSnakeGame controller, PanelSnakeGame panelSnakeGame){

        this.game = game;
        this.controller = controller;
        this.panelSnakeGame = panelSnakeGame;
        // Adding listener on game changes
        game.addPropertyChangeListener("turn",this);
        game.addPropertyChangeListener("snake", this);
        controller.addPropertyChangeListener("gameState", this);

        // Window Creation
        JFrame window = generateWindow();

        //add Game Panel to window
        window.add(panelSnakeGame);

        // Render window
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * @return Main Window
     */
    public JFrame generateWindow(){
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Game");
        jFrame.setSize(new Dimension(panelSnakeGame.getSizeX() * 50,panelSnakeGame.getSizeY() * 50));
        Dimension windowSize = jFrame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2 - 350;
        jFrame.setLocation(dx,dy);
        return jFrame;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        switch (propertyChangeEvent.getPropertyName()){
            case "turn" :  update(); break;
            case "gameState" : break;
            case "snake": reloadMap(); break;
            default: System.err.println("Received a non supported property change event, data might not have been actualised"); break;
        }
    }

    public void update(){
        ArrayList<FeaturesSnake> displaySnakes = new ArrayList<FeaturesSnake>();
        ArrayList<FeaturesItem> displayItems = new ArrayList<FeaturesItem>();

        for(Snake snake : controller.getGame().getSnakes()){
            ArrayList<Position> positions = new ArrayList<Position>();
            positions.add(snake.getPosition());
            positions.addAll(snake.getSnakeBodies());

            displaySnakes.add(new FeaturesSnake(positions, snake.getLastAction(), snake.getColor(), false, false));
        }

        panelSnakeGame.updateInfoGame(displaySnakes, displayItems);
        for(FeaturesSnake snake : displaySnakes) {
            panelSnakeGame.paint_Snake(panelSnakeGame.getGraphics(), snake);
        }
    }

    public void reloadMap(){
        ArrayList<FeaturesSnake> displaySnakes = new ArrayList<FeaturesSnake>();
        ArrayList<FeaturesItem> displayItems = new ArrayList<FeaturesItem>();

        for(Snake snake : controller.getGame().getSnakes()){
            ArrayList<Position> positions = new ArrayList<Position>();
            positions.add(snake.getPosition());
            positions.addAll(snake.getSnakeBodies());

            displaySnakes.add(new FeaturesSnake(positions, snake.getLastAction(), snake.getColor(), false, false));
            System.out.println(displaySnakes.size());
        }

        panelSnakeGame.updateInfoGame(displaySnakes, displayItems);
        panelSnakeGame.paint(panelSnakeGame.getGraphics());
    }
}