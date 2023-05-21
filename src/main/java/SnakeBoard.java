import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

public class SnakeBoard extends JFrame implements KeyListener {
    private JPanel mainPanel;
    private JButton startANewGameButton;
    private JLabel snakeInitialLabel;
    private JTextPane snakeBoardInitialScreenTextPane;

    private JLabel score;

    private JLabel game_over;
    GameThread game;
    GameGUI gui;
    Snake_board game_board;

    public SnakeBoard() {
        startANewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO When the board is created: start the game
                JOptionPane.showMessageDialog(startANewGameButton, snakeBoardInitialScreenTextPane.getText() + "Dupa");
            }
        });

        setTitle("Ssssssssssnake");
        setSize(700, 760);
        setResizable(false);
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game_board = new Snake_board(700, 700, 25);
        gui = new GameGUI(game_board, 0, 50);
        game = new GameThread(game_board);

        getContentPane().add(gui);
        gui.setLayout(null);
        score = new JLabel("Score");
        score.setHorizontalAlignment(SwingConstants.LEFT);
        score.setBounds(0, 0, 200, 50);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        game_over = new JLabel();
        game_over.setHorizontalAlignment(SwingConstants.CENTER);
        game_over.setBounds(0, 350, 700, 50);
        game_over.setForeground(Color.WHITE);
        game_over.setFont(new Font("Comic Sans MS", Font.BOLD, 40));


        gui.add(score);
    }

    public void startGame() {
        game.start();
        gui.redraw();
    }

    public void runGameLoop() {
        while (game.checkEnd() == false) {
            game.move();
            score.setText("Score: " + Integer.toString(game.getScore()));
            gui.redraw();
        }

        game_over.setText("GAME OVER");
        gui.add(game_over);
    }

    public static void main(String[] args) {

        SnakeBoard board = new SnakeBoard();

        EventQueue.invokeLater(() -> board.setVisible(true));

        board.startGame();

        board.runGameLoop();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        if (c == 39 && !game.getSnakeDirection().equals(Snake.SnakeMovement.LEFT)) {
            this.game.setSnakeDirection(Snake.SnakeMovement.RIGHT);
        } else if (c == 37 && !game.getSnakeDirection().equals(Snake.SnakeMovement.RIGHT)) {
            this.game.setSnakeDirection(Snake.SnakeMovement.LEFT);
        } else if (c == 38 && !game.getSnakeDirection().equals(Snake.SnakeMovement.DOWN)) {
            this.game.setSnakeDirection(Snake.SnakeMovement.UP);
        } else if (c == 40 && !game.getSnakeDirection().equals(Snake.SnakeMovement.UP)) {
            this.game.setSnakeDirection(Snake.SnakeMovement.DOWN);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        startANewGameButton = new JButton();
        startANewGameButton.setText("Start a new game for two players");
        mainPanel.add(startANewGameButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        snakeInitialLabel = new JLabel();
        snakeInitialLabel.setText("Hello in snake! :D");
        mainPanel.add(snakeInitialLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        snakeBoardInitialScreenTextPane = new JTextPane();
        snakeBoardInitialScreenTextPane.setText("SnakeBoard: Initial screen");
        mainPanel.add(snakeBoardInitialScreenTextPane, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}


