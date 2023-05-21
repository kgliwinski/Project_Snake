import javax.naming.spi.ObjectFactoryBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameThread extends JPanel implements Runnable, KeyListener {
    private Snake_board board;
    private UserSnake snake_usr;
    private Thread snake_thread;
    private FruitThread fruit;
    private Thread fruit_thread;
    private boolean end = false;

    public GameThread() {
        board = new Snake_board(700, 700, 25);
        snake_usr = new UserSnake(board);
        snake_thread = new Thread(snake_usr);
        fruit = new FruitThread(board, 5000);
        fruit_thread = new Thread(fruit);
        fruit.addFruit();
        fruit.addFruit();

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }



    @Override
    public void run() {
        snake_thread.start();
        fruit_thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            synchronized (board) {
                board.notifyAll();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            checkEating();

            if (snake_usr.checkCollision()) {
                System.out.println("END");
                end = true;
            }

            repaint();
        }
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
                        return;
                    }
                }
            }
        }
    }

    public void drawBoard(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        for (Object obj: this.board.getAllObjects()) {
            if (obj.getType() == Object.ObjectType.FROG) {
                graphics.setPaint(Color.green);
                graphics.drawRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 24, 24);
                graphics.fillRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 24, 24);
            }

            if (obj.getType() == Object.ObjectType.AI_SNAKE) {
                graphics.setPaint(Color.blue);
                graphics.drawRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 24, 24);
                graphics.fillRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 24, 24);
            }

            if (obj.getType() == Object.ObjectType.USER_SNAKE) {
                graphics.setPaint(new Color(130, 200, 130));
                graphics.drawRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 25, 25);
                graphics.fillRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 25, 25);
            }

            if (obj.getType() == Object.ObjectType.FRUIT) {
                graphics.setPaint(Color.red);
                graphics.drawRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 25, 25);
                graphics.fillRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 25, 25);
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(50, 50, 50));
        drawBoard(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        if (c == 39 && !snake_usr.getDirection().equals(Snake.SnakeMovement.LEFT)) {
            this.snake_usr.setDirection(Snake.SnakeMovement.RIGHT);
        } else if (c == 37 && !snake_usr.getDirection().equals(Snake.SnakeMovement.RIGHT)) {
            this.snake_usr.setDirection(Snake.SnakeMovement.LEFT);
        } else if (c == 38 && !snake_usr.getDirection().equals(Snake.SnakeMovement.DOWN)) {
            this.snake_usr.setDirection(Snake.SnakeMovement.UP);
        } else if (c == 40 && !snake_usr.getDirection().equals(Snake.SnakeMovement.UP)) {
            this.snake_usr.setDirection(Snake.SnakeMovement.DOWN);
        }
    }
}
