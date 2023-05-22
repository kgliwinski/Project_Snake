import java.util.ArrayList;
import java.util.Random;

public class Snake_board implements GameBaseObject {
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

    public Snake_board(int board_height, int board_width, int grid) {
        this.board_height = board_height;
        this.board_width = board_width;
        this.grid = grid;
        restart();

    }

    private boolean checkCollision_x(ArrayList<BoardElement> boardElements, int x) {
        for (BoardElement obj : boardElements) {
            if (obj.getTopLeft_x() == x) {
                return true;
            }
        }

        return false;
    }

    public int generatePosition_x() {
        Random rand = new Random();
        ArrayList<BoardElement> boardElements = getAllObjects();

        int new_x = rand.nextInt(board_height / grid) * grid;
        while (checkCollision_y(boardElements, new_x) == true) {
            new_x += grid;

            if (new_x >= board_width) {
                new_x = 0;
            }
        }

        return  new_x;
    }
    private boolean checkCollision_y(ArrayList<BoardElement> boardElements, int y) {
        for (BoardElement obj : boardElements) {
            if (obj.getTopLeft_y() == y) {
                return true;
            }
        }

        return false;
    }

    public int generateSnakeStart_x() {
        return board_width/2;
    }

    public int generateSnakeStart_y() {
        return board_height/2;
    }
    public synchronized int generatePosition_y() {
        Random rand = new Random();
        ArrayList<BoardElement> boardElements = getAllObjects();

        int new_y = rand.nextInt(board_width/ grid) * grid;
        while (checkCollision_y(boardElements, new_y) == true) {
            new_y += grid;

            if (new_y >= board_width) {
                new_y = 0;
            }
        }

        return new_y;
    }
    public synchronized void addObjects(ArrayList<BoardElement> boardElements, BoardElement.Type type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                obj.boardElements.addAll(boardElements);
            }
        }
    }

    public synchronized void addObject(BoardElement boardElement, BoardElement.Type type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                obj.boardElements.add(boardElement);
            }
        }
    }

    public synchronized ArrayList<BoardElement> getObjects(BoardElement.Type type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                return obj.boardElements;
            }
        }

        throw new RuntimeException("Invalid type");
    }

    public synchronized ArrayList<BoardElement> getAllObjects() {
        ArrayList<BoardElement> temp = new ArrayList<>();
        for (ObjectList obj : this.objects) {
            temp.addAll(obj.boardElements);
        }

        return temp;
    }

    public int getGrid() {
        return grid;
    }

    public int getWidth() {
        return board_width;
    }

    public int getHeight() {
        return board_height;
    }

    @Override
    public void restart() {
        objects = new ArrayList<ObjectList>();
        for (BoardElement.Type type : BoardElement.Type.values()) {
            objects.add(new ObjectList(type));
        }
    }
}
