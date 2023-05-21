import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameThread extends JPanel implements Runnable, KeyListener {
//    private AISnakeThread ai_snake;
//    private  Thread ai_snake_thread;
//    private Thread user_snake_thread;
//    private UserSnake user_snake;
//    Snake snake;
//    Snake snake_usr;
    private Snake_board board;
    UserSnake snake_usr;
    Thread snake_thread;
    public GameThread() {
//        ai_snake = new AISnakeThread(200, 200, 25);
//        user_snake = new UserSnake(100, 25, 25);
//        ai_snake_thread = new Thread(ai_snake);
//        user_snake_thread = new Thread(user_snake);
//
//        snake = ai_snake.getSnake();
//        snake_usr = user_snake.getSnake();
        board = new Snake_board(700, 700, 25);
        snake_usr = new UserSnake(board);
        snake_thread = new Thread(snake_usr);

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void drawSnake(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        for (Object obj: this.board.getObjects()) {
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
                graphics.setPaint(Color.blue);
                graphics.drawRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 25, 25);
                graphics.fillRect(obj.getTopLeft_x(), obj.getTopLeft_y(), 25, 25);
            }
        }

//        for (Object rec : this.snake.getBody()) {
//            graphics.setPaint(new Color(207, 133, 133));
//            graphics.drawRect(rec.getTopLeft_x(), rec.getTopLeft_y(), 25, 25);
//            graphics.setPaint(new Color(207, 133, 133));
//            graphics.fillRect(rec.getTopLeft_x(), rec.getTopLeft_y(), 25, 25);
//        }
//
//        for (Object rec : this.snake_usr.getBody()) {
//            graphics.setPaint(new Color(133, 207, 133));
//            graphics.drawRect(rec.getTopLeft_x(), rec.getTopLeft_y(), 25, 25);
//            graphics.setPaint(new Color(133, 207, 133));
//            graphics.fillRect(rec.getTopLeft_x(), rec.getTopLeft_y(), 25, 25);
//        }
    }

    @Override
    public void run() {
        snake_thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        ai_snake_thread.start();
//        user_snake_thread.start();

        while (true) {
//            if (ai_snake.checkMove() == true) {
//                snake = ai_snake.getSnake();
//                System.out.println("AI snake");
//                repaint();
//            }
//            if (snake_usr.checkMove() == true) {
//            }
            synchronized (board) {
                board.notifyAll();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(50, 50, 50));
        drawSnake(g);
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
        System.out.println(c);

        if (c == 39 && !snake_usr.getDirection().equals(Snake.SnakeMovement.LEFT)) {
            System.out.println("right");
            this.snake_usr.setDirection(Snake.SnakeMovement.RIGHT);
        } else if (c == 37 && !snake_usr.getDirection().equals(Snake.SnakeMovement.RIGHT)) {
            System.out.println("left");
            this.snake_usr.setDirection(Snake.SnakeMovement.LEFT);
        } else if (c == 38 && !snake_usr.getDirection().equals(Snake.SnakeMovement.DOWN)) {
            System.out.println("up");
            this.snake_usr.setDirection(Snake.SnakeMovement.UP);
        } else if (c == 40 && !snake_usr.getDirection().equals(Snake.SnakeMovement.UP)) {
            System.out.println("down");
            this.snake_usr.setDirection(Snake.SnakeMovement.DOWN);
        }
    }
}
