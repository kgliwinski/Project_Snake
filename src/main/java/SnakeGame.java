import java.util.ArrayList;

public class SnakeGame {
    private Snake_board board;
    private UserSnake snake_usr;
    private Thread snake_thread;
    private FruitsThread fruit;
    private Thread fruit_thread;
    private AISnake snake_ai;
    private Thread ai_snake_thread;
    private volatile int score;

    /**
     * @param board game board
     */
    public SnakeGame(Snake_board board) {
        this.board = board;
        snake_ai = new AISnake(board);
        ai_snake_thread = new Thread(snake_ai);
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
        ai_snake_thread.start();
        fruit_thread.start();
    }
    static int p = 0;
    /**
     * Perform one move, includes 100 millis delay
     */
    public void move() {
        synchronized (board) {
            if(p == 3){
                snake_ai.setDirection();
                p = 0;
            }
            p++;
            board.notifyAll();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        checkUserAndAISnakeEating();
    }

    /**
     * Check end of the game
     * @return true - game ends
     */
    public boolean checkEnd() {
        if (checkCollisionWithObstacle() == true) {
            return  true;
        }
        return snake_usr.checkCollision(); // || snake_ai.checkCollision();
    }

    /**
     * Check that user snake ate fruit
     */
    private synchronized void checkUserAndAISnakeEating() {
        BoardElement snake_head = board.getElements(BoardElement.Type.USER_SNAKE).get(0);
        BoardElement ai_snake_head = board.getElements(BoardElement.Type.AI_SNAKE).get(0);
        ArrayList<BoardElement> fruits = board.getElements(BoardElement.Type.FRUIT);
        for (int i = 0; i < fruits.size(); ++i) {
            if (BoardElement.intersect(snake_head, fruits.get(i))) {
                fruit.changePosition(i);
                snake_usr.grow();
                score += 1;
                return;
            }
            if (BoardElement.intersect(ai_snake_head, fruits.get(i))) {
                fruit.changePosition(i);
                snake_ai.grow();
                return;
            }
        }
    }

    /**
     * Checks if the user snake collided with obstacles or with the AI snake
     * @return True if the user snake collided
     */
    private synchronized boolean checkCollisionWithObstacle() {
        ArrayList<BoardElement> usr_snake = board.getElements(BoardElement.Type.USER_SNAKE);
        BoardElement snake_head = usr_snake.get(0);
        ArrayList<BoardElement> ai_snake = board.getElements(BoardElement.Type.AI_SNAKE);
        ArrayList<BoardElement> obstacle_boardElements = board.getElements(BoardElement.Type.OBSTACLE);
        for (BoardElement obj : obstacle_boardElements) {
            if (BoardElement.intersect(obj, snake_head)) {
                return true;
            }
        }
        for (BoardElement ai_snake_part : ai_snake){
            for (BoardElement snake_part : usr_snake){
                if (BoardElement.intersect(ai_snake_part, snake_part)){
                    return true;
                }
            }
        }
        return  false;
    }

    /**
     * Gets the direction of user snake
     * @return the direction uf user snake
     */
    public Snake.SnakeMovement getUsrSnakeDirection() {
        return snake_usr.getDirection();
    }

    /**
     * Sets the direction of user snake
     * @param direction - direction to be set
     */
    public void setUsrSnakeDirection(Snake.SnakeMovement direction) {
        snake_usr.setDirection(direction);
    }

    /**
     * Gets the direction of ai snake
     * @return the direction of ai snake
     */
    public Snake.SnakeMovement getAiSnakeDirection() {return snake_ai.getDirection();}

    /**
     * Sets the direction of ai snake - ai generated
     */
    public void setAiSnakeDirection() {
        snake_ai.setDirection();
    }

    /**
     * Get score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Restart the game
     */
    public void restartGame() {
        board.restart();
        snake_usr.restart();
        snake_ai.restart();
        fruit.restart();

        Obstacle.generateObstacle(board);
        Obstacle.generateObstacle(board);
        score = 0;
    }
}
