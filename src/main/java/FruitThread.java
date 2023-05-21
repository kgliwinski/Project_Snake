import java.util.ArrayList;

public class FruitThread implements Runnable{
    private Fruit fruit;
    private volatile boolean position_timeout;

    private int change_position_time;
    private long timer_start;


    public FruitThread(int grid, int board_width, int board_height, int change_position_time) {
        fruit = new Fruit(grid, board_width, board_height);
        this.change_position_time = change_position_time;

        position_timeout = false;
    }

    @Override
    public void run() {
        timer_start = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - timer_start > change_position_time) {
                position_timeout = true;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkPositionTimeout() {
        return  position_timeout;
    }

    public void generateFruitPosition(ArrayList<Object> exisiting_rect) {
        synchronized (fruit) {
            fruit.generateNewPosition(exisiting_rect);
            position_timeout = false;
            timer_start = System.currentTimeMillis();
        }
    }
    public Fruit getFruit() {
        Fruit temp;
        synchronized (fruit) {
            temp = new Fruit(fruit.top_left_x, fruit.top_left_y, 25);
        }

        return temp;
    }
}
