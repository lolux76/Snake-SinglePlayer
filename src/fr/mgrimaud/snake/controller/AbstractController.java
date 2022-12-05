package fr.mgrimaud.snake.controller;

import fr.mgrimaud.snake.model.game.Game;
import fr.mgrimaud.snake.gameState.GameState;
import fr.mgrimaud.snake.gameState.PauseState;
import fr.mgrimaud.snake.gameState.PlayState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractController {
    private Game game;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private GameState gameState;

    public AbstractController(Game game)
    {
        this.game = game;
        this.gameState = new PauseState();
    }

    public void restart()
    {
        System.out.println("init");
        game.init();
        changeState(new PauseState());
    }

    public void step()
    {
        game.step();
        changeState(new PauseState());
    }

    public void play()
    {
        game.launch();
        changeState(new PlayState());
    }

    public void pause()
    {
        game.pause();
        changeState(new PauseState());
    }

    public void changeState(GameState gameState){
        GameState oldState = this.gameState;
        this.gameState = gameState;
        support.firePropertyChange("gameState", oldState, this.gameState);
    }

    public GameState getGameState() {
        return gameState;
    }

    //Observers
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        support.addPropertyChangeListener(property, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
        support.removePropertyChangeListener(property, listener);
    }

    public void setSpeed(double speed){
        game.setTime((long) speed);
    }

    Game getGame(){
        return game;
    }

    void setGame(Game game){
        this.game = game;
    }

}
