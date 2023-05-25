import java.util.ArrayList;

public class SnakeGame {
    private Snake_board board;
    private UserSnake snake_usr;
    private Thread snake_thread;
    private FruitsThread fruit;
    private Thread fruit_thread;
    private volatile int score;

    /**
     * Snake game constructor
     * @param board game board
     */
    public SnakeGame(Snake_board board) {
        this.board = board;
        snake_usr = new UserSnake(board);
        snake_thread = new Thread(snake_usr);
        fruit = new FruitsThread(board, 3,5000);
        fruit_thread = new Thread(fruit);
        Obstacle.generateObstacle(board);
        Obstacle.generateObstacle(board);
        score = 0;
    }

    /**
     * Start game
     */
    public void start() {
        snake_thread.start();
        fruit_thread.start();
    }

    /**
     * Perform one move, includes 100 millis delay
     */
    public void move() {
        synchronized (board) {
            board.notifyAll();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        checkUserSnakeEating();
    }

    /**
     * Check end of the game
     * @return true - game ends
     */
    public boolean checkEnd() {
        if (checkCollisionWithObstacle() == true) {
            return  true;
        }
        return snake_usr.checkCollision();
    }

    /**
     * Check that user snake ate fruit
     */
    private synchronized void checkUserSnakeEating() {
        BoardElement snake_head = board.getElements(BoardElement.Type.USER_SNAKE).get(0);
        ArrayList<BoardElement> fruits = board.getElements(BoardElement.Type.FRUIT);
        for (int i = 0; i < fruits.size(); ++i) {
            if (BoardElement.intersect(snake_head, fruits.get(i))) {
                fruit.changePosition(i);
                snake_usr.grow();
                score += 1;
                return;
            }
        }
    }

    private synchronized boolean checkCollisionWithObstacle() {
        BoardElement snake_head = board.getElements(BoardElement.Type.USER_SNAKE).get(0);
        ArrayList<BoardElement> obstacle_boardElements = board.getElements(BoardElement.Type.OBSTACLE);
        for (BoardElement obj : obstacle_boardElements) {
            if (BoardElement.intersect(obj, snake_head)) {
                return true;
            }
        }
        return  false;
    }

    public Snake.SnakeMovement getSnakeDirection() {
        return snake_usr.getDirection();
    }
    public void setSnakeDirection(Snake.SnakeMovement direction) {
        snake_usr.setDirection(direction);
    }

    public int getScore() {
        return score;
    }

    public void restartGame() {
        board.restart();
        snake_usr.restart();
        fruit.restart();

        Obstacle.generateObstacle(board);
        Obstacle.generateObstacle(board);
        score = 0;
    }
}
