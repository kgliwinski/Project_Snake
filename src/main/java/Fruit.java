import java.util.ArrayList;
import java.util.Random;
public class Fruit {
    private ArrayList<Object> objects;
    protected Snake_board board;
    private Object.ObjectType type;
    public Fruit(Snake_board board) {
        type = Object.ObjectType.FRUIT;
        objects = new ArrayList<Object>();
        objects.add(new Object(
                board.generatePosition_x(),
                board.generatePosition_y(),
                type
        ));

        board.addObjects(objects, type);
        this.board = board;
    }

    public void addFruit() {
        objects.add(new Object(
                board.generatePosition_x(),
                board.generatePosition_y(),
                type
        ));
        board.addObject(objects.get(objects.size() - 1), type);
    }

    public void changePosition(int index) {
        synchronized (board) {
            Object temp = objects.get(index);
            temp.setPosition(
                    board.generatePosition_x(),
                    board.generatePosition_y()
            );
        }
    }

    public ArrayList<Object> getFruits() {
        synchronized (board) {
            return objects;
        }
    }
}
