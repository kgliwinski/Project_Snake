import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserSnake extends SnakeThread{
    public UserSnake(Snake_board board) {

        super(board);
    }

    public void setSnakeDirection(Snake.SnakeMovement direction) {
        synchronized (this) {
            this.setDirection(direction);
        }
    }
}
