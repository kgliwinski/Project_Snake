import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * AI Snake Thread, extends Snake Thread class
 */
public class AISnakeThread extends SnakeThread {
    /**
     * Create AI Snake thread
     * @param board snake game board
     */
    public AISnakeThread(Snake_board board) {
        super(board);
        this.snake_type = BoardElement.Type.AI_SNAKE;
    }


}
