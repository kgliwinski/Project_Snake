import java.util.ArrayList;

/**
 * This class extends Frogs class
 */
public class FrogsThread extends Frogs implements Runnable{
    private int change_position_time;
    private ArrayList<Long> timers_start_time;

    /**
     * Constructor of Fruits Thread
     * @param board
     * @param number_of_fruits
     * @param change_position_time
     */
    public FrogsThread(Snake_board board, int number_of_frogs, int change_position_time) {
        super(board, number_of_frogs);
        this.change_position_time = change_position_time;

        timers_start_time = new ArrayList<Long>();

        for (int i = 0; i < number_of_frogs; ++i) {
            timers_start_time.add(new Long(System.currentTimeMillis()));
        }
    }

    /**
     * Thread loop
     */
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

//    @Override
//    public void addFruit() {
//        super.addFruit();
//        Long time = System.currentTimeMillis();
//        timers_start_time.add(time);
//    }

    /**
     * Change fruit position and reset timer
     * @param index index of fruit in ArrayList
     */
    @Override
    public void changePosition(int index) {
        super.changePosition(index);
        timers_start_time.set(index, System.currentTimeMillis());
    }

    /**
     * Restart thread
     */
    @Override
    public void restart() {
        super.restart();
        timers_start_time = new ArrayList<Long>();

        for (int i = 0; i < number_of_frogs; ++i) {
            timers_start_time.add(new Long(System.currentTimeMillis()));
        }
    }
}
