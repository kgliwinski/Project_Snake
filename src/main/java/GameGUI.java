import javax.swing.*;
import java.awt.*;

/**
 * Game graphic user interface
 */
public class GameGUI extends JPanel {
    private Snake_board board;

    /**
     * Board grid
     */
    private int grid;

    /**
     * Board display X offset
     */
    private int position_x;

    /**
     * Board display Y offset
     */
    private int position_y;

    /**
     * Game GUI constructor
     * @param board game board
     * @param position_x board display x offset
     * @param position_y board display y offset
     */
    public GameGUI(Snake_board board, int position_x, int position_y) {
        this.board = board;
        this.grid = board.getGrid();
        this.position_x = position_x;
        this.position_y = position_y;
        setBackground(new Color(50, 50, 50));
    }

    /**
     * Redraw gui
     */
    public void redraw() {
        repaint();
    }

    /**
     * Draw GUI elements
     * @param g graphics
     */
    public void drawGame(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setPaint(Color.black);
        for (int i = 1; i < 5; ++i) {
            graphics.drawLine(
                    position_x,
                    board.getHeight() + position_y + i ,
                    board.getWidth() + position_x,
                    board.getHeight() + position_y + i);
            graphics.drawLine(
                    position_x,
                    position_y - i ,
                    board.getWidth() + position_x,
                    position_y - i);
            graphics.drawLine(
                    position_x - i,
                    position_y - 4,
                    position_x - i,
                    position_y + board.getHeight() + 4);
            graphics.drawLine(
                    position_x + board.getWidth() + i,
                    position_y - 4,
                    position_x + board.getWidth() + i,
                    position_y + board.getHeight() + 4);
        }
        graphics.setPaint(new Color(70, 70, 50));
        graphics.fillRect(position_x, position_y, board.getWidth(), board.getHeight());

        for (BoardElement obj: this.board.getAllObjects()) {
            if (obj.getType() == BoardElement.Type.FROG) {
                graphics.setPaint(Color.green);
            }

            if (obj.getType() == BoardElement.Type.AI_SNAKE) {
                graphics.setPaint(Color.blue);
            }

            if (obj.getType() == BoardElement.Type.USER_SNAKE) {
                graphics.setPaint(new Color(130, 200, 130));
            }

            if (obj.getType() == BoardElement.Type.FRUIT) {
                graphics.setPaint(Color.red);
            }

            if (obj.getType() == BoardElement.Type.OBSTACLE) {
                graphics.setPaint(Color.black);
            }

            graphics.drawRect(
                    obj.getTopLeft_x() + position_x,
                    obj.getTopLeft_y() + position_y,
                    grid, grid);
            graphics.fillRect(
                    obj.getTopLeft_x() + position_x,
                    obj.getTopLeft_y() + position_y,
                    grid, grid);
        }
    }

    /**
     * Paint components
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }
}
