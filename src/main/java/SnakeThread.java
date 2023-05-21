public class SnakeThread extends Snake implements Runnable{
    protected volatile boolean moved;

    protected Snake_board board;

    public SnakeThread(Snake_board board) {

        super(
                board.generatePosition_x(),
                board.generatePosition_y(),
                board.getGrid());
        board.addObjects(getBody());
        this.board = board;
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

//    public Snake getSnake() {
//        Snake temp;
//        moved = false;
//        synchronized (snake) {
//            temp = snake;
//        }
//
//        return temp;
//    }
}
