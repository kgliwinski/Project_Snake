import javax.swing.*;
import java.awt.*;

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
    }
}
