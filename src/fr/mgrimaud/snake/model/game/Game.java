package fr.mgrimaud.snake.model.game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Matéo Grimaud
 * @version 1.0.0
 *
 */
public abstract class Game implements Runnable {

    //GETTERS AND SETTERS
    public int getTurn() {
        return turn;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    //ATTRIBUTES
    private int turn;
    private final int maxTurn;
    private boolean isRunning;
    private Thread thread;
    private long time;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    //CONSTRUCTOR

    /**
     *
     * @param maxTurn
     */
    public Game(int maxTurn, long time){
        this.maxTurn = maxTurn;
        this.time = time;
    }


    /**
     * Initialise the game
     */
    //METHODS
    public void init(){
        this.turn = 0;
        this.isRunning = true;
        initializeGame();
    }

    /**
     * Increment turn counter,
     * if the game has ended, call gameOver() method
     *
     */
    public void step(){
        if(!gameContinue() || this.turn == this.maxTurn) // Game has ended
        {
            this.isRunning = false;
            gameOver();
        }
        else{ // Game continue, playing the turn
            takeTurn();
            support.firePropertyChange("turn", this.turn, this.turn + 1);
            this.turn++;
        }
    }

    /**
     * Pause the game
     */
    public void pause(){
        this.isRunning = false;
    }

    /**
     * Launch the game
     */
    public void run(){
        while(this.isRunning) {
            step();
            try {
                Thread.sleep(this.time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void launch(){
        this.isRunning = true;
        this.thread = new Thread(this);
        thread.start();
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

    public void changeTurn() {
        support.firePropertyChange("turn", this.turn, ++this.turn);
    }


    //ABSTRACT METHODS
    abstract void initializeGame();
    abstract void takeTurn();
    abstract void gameOver();
    abstract boolean gameContinue();

}
