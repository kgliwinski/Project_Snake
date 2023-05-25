import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
public class AISnake extends AISnakeThread{

    private ArrayList<BoardElement> body;

    /**
    * @brief Constructor of the AISnake class
     */
    public AISnake(Snake_board board){
        super(board);
    }

    /**
     *
     * @param direction - direction to be set
     */
    public void setAiSnakeDirection(Snake.SnakeMovement direction){
        synchronized (this){
            this.setDirection(direction);
        }
    }

    private int calculateElementDistance(BoardElement element, int x, int y){
        return (int) Math.sqrt(Math.pow(Math.abs(element.top_left_x - x),2) + Math.pow(Math.abs(element.top_left_y - y),2));
    }

    /**
     *
     * @param elements - Array of elements to be checked
     * @return A map containing x and y coordinates of the closest element from array
     */
    private Map<String, Integer> getClosestElementPosition(ArrayList<BoardElement> elements, int snake_x, int snake_y){
        int x = 4;
        int y = 4;
        int lowestDistance = 1000000;
        int currentDistance = 0;
        for(int i = 0; i < elements.size(); ++i){
            currentDistance = calculateElementDistance(elements.get(i), snake_x, snake_y);
            if (currentDistance < lowestDistance){
                lowestDistance = currentDistance;
                x = elements.get(i).top_left_x;
                y = elements.get(i).top_left_y;
            }
        }

        Map<String, Integer> ret = new HashMap<>();
        ret.put("x", x);
        ret.put("y", y);
        return ret;
    }

    /**
     * Generates a direction for the AI snake to turn to
     * @return generated direction
     */
    private Snake.SnakeMovement createAiSnakeDirection() {
        ArrayList<BoardElement> fruit = this.board.getElements(BoardElement.Type.FRUIT);
        ArrayList<BoardElement> snake = this.board.getElements(BoardElement.Type.AI_SNAKE);
        int snake_x = snake.get(0).getTopLeft_x();
        int snake_y = snake.get(0).getTopLeft_y();
        Map<String, Integer> closestFruit = getClosestElementPosition(fruit, snake_x, snake_y);
        Snake.SnakeMovement finalDirection = SnakeMovement.RIGHT;

        return finalDirection;
    }

    public void setDirection(){
        synchronized (this.direction) {
            this.direction = createAiSnakeDirection();
        }
    }

    public boolean checkCollision() {
        for (int i = 1; i < body.size(); ++i) {
            if (BoardElement.intersect(body.get(0), body.get(i))) {
                return true;
            }
        }

        return  false;
    }

    @Override
    public void move(){
        int p = 5;
        BoardElement tail = body.get(body.size() - 1);
        tail_previous_pos = new BoardElement(
                tail.getTopLeft_x(),
                tail.getTopLeft_y(), snake_type);

        // Move body
        for (int i = (body.size() - 1); i > 0 ; --i) {
            body.get(i).setPosition(
                    body.get(i-1).getTopLeft_x(),
                    body.get(i-1).getTopLeft_y());
        }

    }
    @Override
    public void restart() {
        body = new ArrayList<BoardElement>();

        int top_left_x = board.generateAiSnakeStart_x() + 3;
        int top_left_y = board.generateAiSnakeStart_y() + 3;
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
