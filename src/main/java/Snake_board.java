import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Snake_board {
    private class ObjectList {
        public ArrayList<Object> objects;
        public Object.ObjectType type;

        public ObjectList(Object.ObjectType type) {
            this.objects = new ArrayList<>();
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
        objects = new ArrayList<ObjectList>();
        for (Object.ObjectType type : Object.ObjectType.values()) {
            objects.add(new ObjectList(type));
        }

    }

    public int generatePosition_x() {
        Random rand = new Random();
        int new_x = rand.nextInt(board_height / grid) * grid;
        return  new_x;
    }

    public int generatePosition_y() {
        Random rand = new Random();
        int new_y = rand.nextInt(board_width/ grid) * grid;
        return  new_y;
    }
    public synchronized void addObjects(ArrayList<Object> objects, Object.ObjectType type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                obj.objects.addAll(objects);
            }
        }
    }

    public synchronized void addObject(Object object, Object.ObjectType type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                obj.objects.add(object);
            }
        }
    }

    public synchronized ArrayList<Object> getObjects(Object.ObjectType type) {
        for (ObjectList obj : this.objects) {
            if (obj.type == type) {
                return obj.objects;
            }
        }

        throw new RuntimeException("Invalid type");
    }

    public synchronized ArrayList<Object> getAllObjects() {
        ArrayList<Object> temp = new ArrayList<>();
        for (ObjectList obj : this.objects) {
            temp.addAll(obj.objects);
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
}
