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
    private volatile boolean end;

    private volatile int score;

    public GameThread(Snake_board board) {
        this.board = board;
        snake_usr = new UserSnake(board);
        snake_thread = new Thread(snake_usr);
        fruit = new FruitThread(board, 5000);
        fruit_thread = new Thread(fruit);
        fruit.addFruit();
        fruit.addFruit();
        end = false;
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

        checkEating();
    }

    public boolean checkEnd() {
        return snake_usr.checkCollision();
    }

    public void checkEating() {
        synchronized (board) {
            ArrayList<Object> snake = board.getObjects(Object.ObjectType.USER_SNAKE);
            ArrayList<Object> fruits = board.getObjects(Object.ObjectType.FRUIT);
            for (Object snake_part : snake) {
                for (int i = 0; i < fruits.size(); ++i) {
                    if (Object.intersect(snake_part, fruits.get(i))) {
                        fruit.changePosition(i);
                        snake_usr.grow();
                        score += 1;
                        return;
                    }
                }
            }
        }
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
