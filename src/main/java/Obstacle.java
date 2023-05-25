import java.util.ArrayList;
import java.util.Random;

/**
 * Obstacle class
 */
public class Obstacle {
    /**
     * Generate random obstacle
     * @param board game board
     */
    static public void generateObstacle(Snake_board board) {
        ArrayList<BoardElement> obstacle = new ArrayList<>();
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
        board.addElements(obstacle, BoardElement.Type.OBSTACLE);
    }

    /**
     * Generate line shape obstacle
     * @param board game board
     * @param obstacle array to store obstacle position
     */
    static private void generateLineShape(Snake_board board, ArrayList<BoardElement> obstacle) {
        Random rand = new Random();
        int grid = board.getGrid();
        int board_width = board.getWidth();
        int board_height = board.getHeight();

        int x = board.generatePosition_x();
        int y = board.generatePosition_y();

        if (x == board.generateSnakeStart_x()) {
            y += grid * 2;
        }
        obstacle.add(new BoardElement(x, y, BoardElement.Type.OBSTACLE));

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

            obstacle.add(new BoardElement(x, y, BoardElement.Type.OBSTACLE));
        }
    }

    /**
     * Generate L-shape obstacle
     * @param board game board
     * @param obstacle array to store obstacle position
     */
    static private void generateLShape(Snake_board board, ArrayList<BoardElement> obstacle) {
        Random rand = new Random();
        int grid = board.getGrid();
        int board_width = board.getWidth();
        int board_height = board.getHeight();

        int x = board.generatePosition_x();
        int y = board.generatePosition_y();

        if (x == board.generateSnakeStart_x()) {
            y += grid * 2;
        }
        obstacle.add(new BoardElement(x, y, BoardElement.Type.OBSTACLE));

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

            obstacle.add(new BoardElement(x, y, BoardElement.Type.OBSTACLE));
        }
    }
}
