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
        reset();

    }

    private boolean checkCollision_x(ArrayList<Object> objects, int x) {
        for (Object obj : objects ) {
            if (obj.getTopLeft_x() == x) {
                return true;
            }
        }

        return false;
    }

    public int generatePosition_x() {
        Random rand = new Random();
        ArrayList<Object> objects = getAllObjects();

        int new_x = rand.nextInt(board_height / grid) * grid;
        while (checkCollision_y(objects, new_x) == true) {
            new_x += grid;

            if (new_x >= board_width) {
                new_x = 0;
            }
        }

        return  new_x;
    }
    private boolean checkCollision_y(ArrayList<Object> objects, int y) {
        for (Object obj : objects ) {
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
        ArrayList<Object> objects = getAllObjects();

        int new_y = rand.nextInt(board_width/ grid) * grid;
        while (checkCollision_y(objects, new_y) == true) {
            new_y += grid;

            if (new_y >= board_width) {
                new_y = 0;
            }
        }

        return new_y;
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

    public void reset() {
        objects = new ArrayList<ObjectList>();
        for (Object.ObjectType type : Object.ObjectType.values()) {
            objects.add(new ObjectList(type));
        }
    }
}
