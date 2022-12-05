package fr.mgrimaud.snake.view;

import fr.mgrimaud.snake.controller.ControllerSimpleGame;
import fr.mgrimaud.snake.model.game.Game;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Matéo Grimaud
 * @version 1.0.0
 */
public class ViewSimpleGame implements PropertyChangeListener {

    private Game game;
    private ControllerSimpleGame controller;
    private JLabel jLabel;

    /**
     * @param game
     */
    public ViewSimpleGame(Game game, ControllerSimpleGame controller){

        this.game = game;
        this.controller = controller;
        // Adding listener on game changes
        game.addPropertyChangeListener("turn",this);
        controller.addPropertyChangeListener("gameState", this);

        // Window Creation
        JFrame window = generateWindow();

        // Label creation
        jLabel = new JLabel("Turn " + game.getTurn(), JLabel.CENTER);
        jLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        window.add(jLabel);

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
        jFrame.setSize(new Dimension(700,700));
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
            case "turn" : jLabel.setText("Turn " + propertyChangeEvent.getNewValue()); break;
            case "gameState" : break;
            default: System.err.println("Received a non supported property change event, data might not have been actualised"); break;
        }
    }
}
