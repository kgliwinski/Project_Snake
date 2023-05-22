import javax.naming.spi.ObjectFactoryBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameThread{
    private Snake_board board;
    private UserSnake snake_usr;
    private Thread snake_thread;
    private FruitThread fruit;
    private Thread fruit_thread;
    private volatile int score;

    public GameThread(Snake_board board) {
        this.board = board;
        snake_usr = new UserSnake(board);
        snake_thread = new Thread(snake_usr);
        fruit = new FruitThread(board, 5000);
        fruit_thread = new Thread(fruit);
        fruit.addFruit();
        fruit.addFruit();
        Obstacle.generateObstacle(board);
        Obstacle.generateObstacle(board);
        score = 0;
    }

    public void start() {
        snake_thread.start();
        fruit_thread.start();
    }

    public void move() {
        synchronized (board) {
            board.notifyAll();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        checkUserSnakeEating();
    }

    public boolean checkEnd() {
        if (checkCollisionWithObstacle() == true) {
            return  true;
        }
        return snake_usr.checkCollision();
    }

    private synchronized void checkUserSnakeEating() {
        Object snake_head = board.getObjects(Object.ObjectType.USER_SNAKE).get(0);
        ArrayList<Object> fruits = board.getObjects(Object.ObjectType.FRUIT);
        for (int i = 0; i < fruits.size(); ++i) {
            if (Object.intersect(snake_head, fruits.get(i))) {
                fruit.changePosition(i);
                snake_usr.grow();
                score += 1;
                return;
            }
        }
    }

    private synchronized boolean checkCollisionWithObstacle() {
        Object snake_head = board.getObjects(Object.ObjectType.USER_SNAKE).get(0);
        ArrayList<Object> obstacle_objects = board.getObjects(Object.ObjectType.OBSTACLE);
        for (Object obj : obstacle_objects) {
            if (Object.intersect(obj, snake_head)) {
                return true;
            }
        }
        return  false;
    }

    public Snake.SnakeMovement getSnakeDirection() {
        return snake_usr.getDirection();
    }
    public void setSnakeDirection(Snake.SnakeMovement direction) {
        snake_usr.setDirection(direction);
    }

    public int getScore() {
        return score;
    }
}
