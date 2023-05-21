import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JPanel {
    private Snake snake;
    public SnakeGame() {
        setBackground(new Color(55, 55, 55));
        this.snake = new Snake(125, 100, 25);
    }
    public void drawSnake(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        for (Object rec : this.snake.getBody()) {
            graphics.setPaint(new Color(130, 207, 133));
            graphics.drawRect(rec.getTopLeft_x(), rec.getTopLeft_y(), 25, 25);
            graphics.setPaint(new Color(130, 207, 133));
            graphics.fillRect(rec.getTopLeft_x(), rec.getTopLeft_y(), 25, 25);
        }
    }

    public void snakeGrow() {
        snake.grow();
    }

    public Snake.SnakeMovement getSnakeDirection() {
        return snake.getDirection();
    }

    public void setSnakeDirection(Snake.SnakeMovement direction) {
        snake.setDirection(direction);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        snake.move();
        drawSnake(g);
    }
}


