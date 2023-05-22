import java.util.ArrayList;

public class FruitsThread extends Fruits implements Runnable{
    private int change_position_time;
    private ArrayList<Long> timers_start_time;
    public FruitsThread(Snake_board board, int number_of_fruits, int change_position_time) {
        super(board, number_of_fruits);
        this.change_position_time = change_position_time;

        timers_start_time = new ArrayList<Long>();

        for (int i = 0; i < number_of_fruits; ++i) {
            timers_start_time.add(new Long(System.currentTimeMillis()));
        }
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

    @Override
    public void restart() {
        super.restart();
        timers_start_time = new ArrayList<Long>();

        for (int i = 0; i < number_of_fruits; ++i) {
            timers_start_time.add(new Long(System.currentTimeMillis()));
        }
    }
}
