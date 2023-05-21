public class SnakeThread extends Snake implements Runnable{
    protected volatile boolean moved;

    public SnakeThread(Snake_board board) {
        super(board);
    }

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

    public boolean checkMove() {
        return moved;
    }

    @Override
    public void move() {
        moved = false;
        synchronized (this) {
            super.move();
            moved = true;
        }
    }
}
