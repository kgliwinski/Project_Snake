/**
 * Snake thread, extends snake class
 */
public class SnakeThread extends Snake implements Runnable{
    protected volatile boolean moved;

    /**
     * Snake thread constructor
     * @param board board
     */
    public SnakeThread(Snake_board board) {
        super(board);
    }

    /**
     * Thread loop
     */
    @Override
    public void run() {
        while (true) {
            synchronized (board) {
                try {
                    board.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                move();
            }
        }
    }

    /**
     * Snake move
     */
    @Override
    public void move() {
        synchronized (this) {
            super.move();
        }
    }
}
