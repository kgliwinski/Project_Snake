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
        setBackground(new Color(50, 50, 50));
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }
}
