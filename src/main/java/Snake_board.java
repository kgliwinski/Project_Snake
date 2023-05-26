import java.util.ArrayList;
import java.util.Random;

/**
 * Class represents snake game board
 */
public class Snake_board implements GameBaseObject {
    /**
     * List of objects with specific type
     */
    private class ObjectList {
        public ArrayList<BoardElement> boardElements;
        public BoardElement.Type type;

        public ObjectList(BoardElement.Type type) {
            this.boardElements = new ArrayList<>();
            this.type = type;
        }
    }
    private final int board_height;
    private final int board_width;
    private final int grid;
    private ArrayList<ObjectList> objects;

    /**
     * Snake board constructor
     * @param board_height board height
     * @param board_width board width
     * @param grid board grid
     */
    public Snake_board(int board_height, int board_width, int grid) {
        this.board_height = board_height;
        this.board_width = board_width;
        this.grid = grid;
        restart();

    }

    /**
     * Check collision in x
     * @param boardElements board elements
     * @param x x position
     * @return ture if collision occurs, false - no collision
     */
    private boolean checkCollision_x(ArrayList<BoardElement> boardElements, int x) {
        for (BoardElement obj : boardElements) {
            if (obj.getTopLeft_x() == x) {
                return true;
            }
        }

        return false;
    }

    /**
     * Generate position in x axes
     * @return x top left position
     */
    public int generatePosition_x() {
        Random rand = new Random();
        ArrayList<BoardElement> boardElements = getAllObjects();

        int new_x = rand.nextInt(board_height / grid) * grid;
//        while (checkCollision_x(boardElements, new_x) == true) {
//            new_x += grid;
//
//            if (new_x >= board_width) {
//                new_x = 0;
//            }
//        }

        return  new_x;
    }

    /**
     * Check collision in y
     * @param boardElements board elements
     * @param y y position
     * @return ture if collision occurs, false - no collision
     */
    private boolean checkCollision_y(ArrayList<BoardElement> boardElements, int y) {
        for (BoardElement obj : boardElements) {
            if (obj.getTopLeft_y() == y) {
                return true;
            }
        }

        return false;
    }

    /**
     * Generate position in x axes
     * @return x top left position
     */
    public synchronized int generatePosition_y() {
        Random rand = new Random();
        ArrayList<BoardElement> boardElements = getAllObjects();

        int new_y = rand.nextInt(board_width/ grid) * grid;
//        while (checkCollision_y(boardElements, new_y) == true) {
//            new_y += grid;
//
//            if (new_y >= board_width) {
//                new_y = 0;
//            }
//        }

        return new_y;
    }

    /**
     * Generate snake starting top left x position
     * @return top left x position
     */
    public int generateSnakeStart_x() {
        return board_width/2;
    }

    /**
     * Generate snake starting top left y position
     * @return top left y position
     */
    public int generateSnakeStart_y() {
        return board_height/2;
    }

    public int generateAiSnakeStart_x(){
        return grid * 5;
    }

    public int generateAiSnakeStart_y(){
        return grid * 5;
    }

    /**
     * Add elements to board
     * @param boardElements elements to be added
     * @param type type of elements
     */
    public synchronized void addElements(ArrayList<BoardElement> boardElements, BoardElement.Type type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                obj.boardElements.addAll(boardElements);
            }
        }
    }

    /**
     * Add element to board
     * @param boardElement element to be added
     * @param type type of element
     */
    public synchronized void addElement(BoardElement boardElement, BoardElement.Type type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                obj.boardElements.add(boardElement);
            }
        }
    }

    /**
     * Get board elements
     * @param type element type
     * @return board elements with specific type
     */
    public synchronized ArrayList<BoardElement> getElements(BoardElement.Type type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                return obj.boardElements;
            }
        }

        throw new RuntimeException("Invalid type");
    }

    /**
     * Get all board objects
     * @return all board objects
     */
    public synchronized ArrayList<BoardElement> getAllObjects() {
        ArrayList<BoardElement> temp = new ArrayList<>();
        for (ObjectList obj : this.objects) {
            temp.addAll(obj.boardElements);
        }

        return temp;
    }

    /**
     * Get board grid
     * @return board grid
     */
    public int getGrid() {
        return grid;
    }

    /**
     * Get board width
     * @return board width
     */
    public int getWidth() {
        return board_width;
    }

    /**
     * Get board height
     * @return board height
     */
    public int getHeight() {
        return board_height;
    }

    /**
     * Restart board
     */
    @Override
    public void restart() {
        objects = new ArrayList<ObjectList>();
        for (BoardElement.Type type : BoardElement.Type.values()) {
            objects.add(new ObjectList(type));
        }
    }
}
