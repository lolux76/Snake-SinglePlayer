package fr.mgrimaud.snake.view;

import fr.mgrimaud.snake.controller.AbstractController;
import fr.mgrimaud.snake.model.game.Game;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Matéo Grimaud
 * @version 1.0.0
 */
public class ViewCommand implements PropertyChangeListener {

    private final Game game;
    private final AbstractController controller;
    static final int minFPS = 0;
    static final int maxFPS = 10;;
    private final JLabel turnNumber;
    private final JButton restartButton;
    private final JButton playButton;
    private final JButton stepButton;
    private final JButton pauseButton;

    public ViewCommand(Game game, AbstractController controller){
        this.game = game;
        this.controller = controller;

        turnNumber = new JLabel("Turn :" + game.getTurn(), JLabel.CENTER);
        restartButton = new JButton();
        playButton = new JButton();
        stepButton = new JButton();
        pauseButton = new JButton();

        // Adding listeners on game changes
        game.addPropertyChangeListener("turn", this);
        game.addPropertyChangeListener("snake", this);
        controller.addPropertyChangeListener("gameState", this);

        JFrame window = generateWindow();
        JPanel topPanel = generateButtons();
        JPanel bottomPanel = generateBottom();

        JPanel panel = new JPanel();
        GridLayout verticalLayout = new GridLayout(2,1);
        panel.setLayout(verticalLayout);
        panel.add(topPanel);
        panel.add(bottomPanel);
        window.add(panel);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public JFrame generateWindow(){
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Commandes");
        jFrame.setSize(new Dimension(700,300));
        Dimension windowSize = jFrame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2 + 335;
        jFrame.setLocation(dx,dy);
        return jFrame;
    }

    public JPanel generateButtons(){
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(1,4);
        panel.setLayout(layout);

        Icon restartIcon = new ImageIcon("icons/icon_restart.png");
        restartButton.setIcon(restartIcon);

        Icon playIcon = new ImageIcon("icons/icon_play.png");
        playButton.setIcon(playIcon);

        Icon stepIcon = new ImageIcon("icons/icon_step.png");
        stepButton.setIcon(stepIcon);

        Icon pauseIcon = new ImageIcon("icons/icon_pause.png");
        pauseButton.setIcon(pauseIcon);

        //Buttons event listener
        restartButton.addActionListener(e->
        {
            controller.restart();
        });
        playButton.addActionListener(e->
        {
            controller.play();
        });
        stepButton.addActionListener(e->
        {
            controller.step();
        });
        pauseButton.addActionListener(e->
        {
            controller.pause();
        });

        restartButton.setEnabled(false);
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
        stepButton.setEnabled(true);

        //Adding buttons
        panel.add(restartButton);
        panel.add(playButton);
        panel.add(stepButton);
        panel.add(pauseButton);
        return panel;
    }

    public JPanel generateBottom(){
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(1,2);
        panel.setLayout(layout);

        JSlider turnSlider = new JSlider(JSlider.HORIZONTAL, minFPS, maxFPS, (int) game.getTime()/1000);
        turnSlider.setMinorTickSpacing(1);
        turnSlider.setMajorTickSpacing(10);
        turnSlider.setSnapToTicks(true);
        turnSlider.setPaintTicks(true);
        turnSlider.setPaintLabels(true);
        turnSlider.addChangeListener(e->
        {
            game.setTime(turnSlider.getValue() * 1000L);
        });
        panel.add(turnSlider);
        panel.add(turnNumber);
        return panel;
    }

    public void updateButtonsOnGameStateChange(){

        //Checking GameState to disable some buttons and enable others
        pauseButton.setEnabled(controller.getGameState().isClickPause());
        playButton.setEnabled(controller.getGameState().isClickPlay());
        stepButton.setEnabled(controller.getGameState().isClickStep());
        restartButton.setEnabled(controller.getGameState().isClickRestart());
    }

    /**
     * @param propertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        switch (propertyChangeEvent.getPropertyName()){
            case "turn" : turnNumber.setText("Turn :" + propertyChangeEvent.getNewValue());
                          break;

            case "gameState" : updateButtonsOnGameStateChange(); break;
            case "snake": break;

            default: System.err.println("Received a non supported property change event, data might not have been actualised");
                     System.err.println("Event : " + propertyChangeEvent.getPropertyName());
                     break;
        }
    }
}