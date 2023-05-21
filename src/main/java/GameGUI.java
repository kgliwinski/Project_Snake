import javax.swing.*;
import java.awt.*;

public class GameGUI extends JPanel {
    private Snake_board board;
    private int grid;
    private int position_x;
    private int position_y;
    public GameGUI(Snake_board board, int position_x, int position_y) {
        this.board = board;
        this.grid = board.getGrid();
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public void redraw() {
        repaint();
    }
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
        }

        for (Object obj: this.board.getAllObjects()) {
            if (obj.getType() == Object.ObjectType.FROG) {
                graphics.setPaint(Color.green);
            }

            if (obj.getType() == Object.ObjectType.AI_SNAKE) {
                graphics.setPaint(Color.blue);
            }

            if (obj.getType() == Object.ObjectType.USER_SNAKE) {
                graphics.setPaint(new Color(130, 200, 130));
            }

            if (obj.getType() == Object.ObjectType.FRUIT) {
                graphics.setPaint(Color.red);
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(50, 50, 50));
        drawGame(g);
    }
}
