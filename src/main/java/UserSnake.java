/**
 * User snake class
 */
public class UserSnake extends SnakeThread{
    /**
     * User snake constructor
     * @param board
     */
    public UserSnake(Snake_board board) {

        super(board);
    }

    /**
     * Set user snake direction
     * @param direction direction
     */
    public void setSnakeDirection(Snake.SnakeMovement direction) {
        synchronized (this) {
            this.setDirection(direction);
        }
    }
}
