import java.util.ArrayList;
import java.util.Random;

public class Obstacle {
//    public Obstacle(Snake_board board) {
//        board.addObjects(generateObstacle(board), Object.ObjectType.OBSTACLE);
//    }

    static public void generateObstacle(Snake_board board) {
        ArrayList<Object> obstacle = new ArrayList<>();
        Random rand = new Random();
        int type = rand.nextInt(2);
        switch (type) {
            case 0:
                generateLineShape(board, obstacle);
                break;
            case 1:
                generateLShape(board, obstacle);
                break;
        }
        board.addObjects(obstacle, Object.ObjectType.OBSTACLE);
    }

    static private void generateLineShape(Snake_board board, ArrayList<Object> obstacle) {
        Random rand = new Random();
        int grid = board.getGrid();
        int board_width = board.getWidth();
        int board_height = board.getHeight();

        int x = board.generatePosition_x();
        if (x == board.generateSnakeStart_x()) {
            x += grid * 2;
        }
        int y = board.generatePosition_y();
        obstacle.add(new Object(x, y, Object.ObjectType.OBSTACLE));

        int direction = rand.nextInt(2);

        for (int i = 0; i < board_width / grid / 4; ++i) {
            if (direction == 0) {
                x += grid;
            } else {
                y += grid;
            }

            if (x >= board_width) {
                x = 0;
            }

            if (y >= board_height) {
                y = 0;
            }

            obstacle.add(new Object(x, y, Object.ObjectType.OBSTACLE));
        }
    }

    static private void generateLShape(Snake_board board, ArrayList<Object> obstacle) {
        Random rand = new Random();
        int grid = board.getGrid();
        int board_width = board.getWidth();
        int board_height = board.getHeight();

        int x = board.generatePosition_x();
        if (x == board.generateSnakeStart_x()) {
            x += grid * 2;
        }
        int y = board.generatePosition_y();
        obstacle.add(new Object(x, y, Object.ObjectType.OBSTACLE));

        int direction = rand.nextInt(2);
        int size = board_width / grid / 4;
        for (int i = 0; i < size; ++i) {
            if (i < size/2) {
                if (direction == 0) {
                    x += grid;
                } else {
                    y += grid;

                }
            } else {
                if (direction == 0) {
                    y += grid;
                } else {
                    x += grid;
                }
            }

            if (x >= board_width) {
                x = 0;
            }

            if (y >= board_height) {
                y = 0;
            }

            obstacle.add(new Object(x, y, Object.ObjectType.OBSTACLE));
        }
    }
}
