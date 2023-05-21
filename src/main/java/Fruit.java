import java.util.ArrayList;
import java.util.Random;
public class Fruit extends Object {
    private int window_height;
    private int window_width;
    private int grid;
    public Fruit(int grid, int window_height, int window_width) {
        super(0, 0, ObjectType.FRUIT);
        Random rand = new Random();
        top_left_x = rand.nextInt(window_height / grid) * grid;
        top_left_y = rand.nextInt(window_width / grid) * grid;

        this.window_height = window_height;
        this.window_width = window_width;
        this.grid = grid;
    }

    private boolean checkTopLeft_x(int x, ArrayList<Object> existing_rect) {
        for (Object rec : existing_rect) {
            if (rec.top_left_x ==  x) {
                return  false;
            }
        }
        return true;
    }
    public void generateNewPosition(ArrayList<Object> existing_rect) {
        Random rand = new Random();
        int new_x = rand.nextInt(window_height / grid) * grid;
        while (checkTopLeft_x(new_x, existing_rect) == false) {
            new_x = rand.nextInt(window_height / grid) * grid;
        }

        int new_y = rand.nextInt(window_height / grid) * grid;
        while (checkTopLeft_x(new_y, existing_rect) == false) {
            new_y = rand.nextInt(window_height / grid) * grid;
        }

        this.top_left_x = new_x;
        this.top_left_y = new_y;
    }

    public Object getRectangle() {
        return this;
    }
}
