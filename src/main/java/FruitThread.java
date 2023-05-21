import java.util.ArrayList;
import java.util.List;

public class FruitThread extends Fruit implements Runnable{
    private int change_position_time;
    private ArrayList<Long> timers_start_time;
    public FruitThread(Snake_board board, int change_position_time) {
        super(board);
        timers_start_time = new ArrayList<Long>();
        long time = System.currentTimeMillis();;
        timers_start_time.add(time);

        this.change_position_time = change_position_time;
    }

    @Override
    public void run() {
        for (Long tim: timers_start_time) {
            tim = System.currentTimeMillis();
        }

        while (true) {
            synchronized (board) {
                try {
                    board.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < timers_start_time.size(); ++i) {
                long now = System.currentTimeMillis();
                if (now - timers_start_time.get(i) > change_position_time) {
                    changePosition(i);
                }
            }

        }
    }

    @Override
    public void addFruit() {
        super.addFruit();
        Long time = System.currentTimeMillis();
        timers_start_time.add(time);
    }

    @Override
    public void changePosition(int index) {
        super.changePosition(index);
        timers_start_time.set(index, System.currentTimeMillis());
    }
}
