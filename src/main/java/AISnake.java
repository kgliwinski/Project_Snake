import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class AISnake extends AISnakeThread {

//    private ArrayList<BoardElement> body;

    /**
     * @brief Constructor of the AISnake class
     */
    public AISnake(Snake_board board) {
        super(board);
        snake_type = BoardElement.Type.AI_SNAKE;
    }

    /**
     * @param direction - direction to be set
     */
    public void setAiSnakeDirection(Snake.SnakeMovement direction) {
        synchronized (this) {
            this.setDirection(direction);
        }
    }

    private int calculateElementDistance(BoardElement element, int x, int y) {
        return (int) Math.sqrt(Math.pow(element.getTopLeft_x() - x, 2) + Math.pow(element.getTopLeft_y() - y, 2));
    }

    /**
     * @param elements - Array of elements to be checked
     * @return A map containing x and y coordinates of the closest element from array
     */
    private Map<String, Integer> getClosestElementPosition(ArrayList<BoardElement> elements, int snake_x, int snake_y) {
        int x = 0, y = 0;
        int lowestDistance = 1000000000;
        int currentDistance = 0;
        for (int i = 0; i < elements.size(); ++i) {
            currentDistance = calculateElementDistance(elements.get(i), snake_x, snake_y);
            if (currentDistance < lowestDistance) {
                lowestDistance = currentDistance;
                x = elements.get(i).getTopLeft_x();
                y = elements.get(i).getTopLeft_y();
            }
        }

        Map<String, Integer> ret = new HashMap<>();
        ret.put("x", x);
        ret.put("y", y);
        return ret;
    }

    /**
     * Generates a direction for the AI snake to turn to
     *
     * @return generated direction
     */
    private Snake.SnakeMovement createAiSnakeDirection() {
        ArrayList<BoardElement> fruit = this.board.getElements(BoardElement.Type.FRUIT);
        ArrayList<BoardElement> snake = this.board.getElements(BoardElement.Type.AI_SNAKE);
        int snake_x = snake.get(0).getTopLeft_x();
        int snake_y = snake.get(0).getTopLeft_y();
        Snake.SnakeMovement cur_direction = this.getDirection();
        Map<String, Integer> closestFruit = getClosestElementPosition(fruit, snake_x, snake_y);
        if (Math.abs(snake_x - closestFruit.get("x")) > Math.abs(snake_y - closestFruit.get("y"))) {
            if (snake_x < closestFruit.get("x") && cur_direction != SnakeMovement.LEFT) {
                // check if closest apple is to the right
                // if it is, and the current direction is not left, return right
                System.out.println("Going right");
                return SnakeMovement.RIGHT;
            } else if (snake_x >= closestFruit.get("x") && cur_direction != SnakeMovement.RIGHT) {
                System.out.println("Going left");
                return SnakeMovement.LEFT;
            }
        }

        if (snake_y < closestFruit.get("y") && cur_direction != SnakeMovement.DOWN) {
            System.out.println("Going up");
            return SnakeMovement.UP;
        } else if (snake_y >= closestFruit.get("y") && cur_direction != SnakeMovement.UP) {
            System.out.println("Going down");
            return SnakeMovement.DOWN;
        }


        // kind of default
        System.out.println("Going default right");
        return SnakeMovement.RIGHT;
    }

    //    @Override
    public void setDirection() {
        System.out.println("Set AI Snake direction");
        synchronized (this.direction) {
            this.direction = createAiSnakeDirection();
        }
    }

    @Override
    public void restart() {
        body = new ArrayList<BoardElement>();

        int top_left_x = board.generateAiSnakeStart_x();
        int top_left_y = board.generateAiSnakeStart_y();
        this.grid_size = board.getGrid();
        for (int i = 0; i < 4; ++i) {
            body.add(new BoardElement(
                    top_left_x - grid_size * i,
                    top_left_y, snake_type)
            );
        }

        direction = SnakeMovement.RIGHT;
        this.snake_type = BoardElement.Type.AI_SNAKE;
        board.addElements(body, snake_type);
    }


}
