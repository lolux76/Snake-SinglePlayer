package fr.mgrimaud.snake.test;

import fr.mgrimaud.snake.controller.ControllerSnakeGame;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        int maxTurn = 50;
        long time = 1000;
        try {
            ControllerSnakeGame controller = new ControllerSnakeGame(maxTurn, time, "./layout/arena.lay");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}