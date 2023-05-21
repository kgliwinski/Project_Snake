import java.util.ArrayList;
import java.util.Random;

public class Snake_board {
    private int board_height;
    private int board_width;
    private int grid;
    private ArrayList<Object> objects;
    public Snake_board(int board_height, int board_width, int grid) {
        this.board_height = board_height;
        this.board_width = board_width;
        this.grid = grid;
        objects = new ArrayList<Object>();
    }

    public int generatePosition_x() {
        Random rand = new Random();
        int new_x = rand.nextInt(board_height / grid) * grid;
//        System.out.printf("Generated x %d\n", new_x);
        return  new_x;
    }

    public int generatePosition_y() {
        Random rand = new Random();
        int new_y = rand.nextInt(board_width/ grid) * grid;
//        System.out.printf("Generated y %d\n", new_y);
        return  new_y;
    }
    public void addObjects(ArrayList<Object> objects) {
        synchronized (this.objects) {
            this.objects.addAll(objects);
        }
    }

    public void addObject(Object objects) {
        synchronized (this.objects) {
            this.objects.add(objects);
        }
    }
    public int getGrid() {
        return grid;
    }

    public ArrayList<Object> getObjects() {
        return  this.objects;
    }

    public int getWidth() {
        return board_width;
    }

    public int getHeight() {
        return board_height;
    }
}
