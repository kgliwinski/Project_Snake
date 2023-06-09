import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SnakeBoard extends JFrame implements KeyListener {
    public static final String top_score_path = "topScore.txt";
    private JPanel mainPanel;
    private JButton startANewGameButton;
    private JLabel snakeInitialLabel;
    private JTextPane snakeBoardInitialScreenTextPane;

    private JLabel score;

    private JLabel topScore;

    private JLabel game_over;
    SnakeGame game;
    GameGUI gui;
    Snake_board game_board;

    private volatile boolean restart_clicked;

    /**
     * Snake board constructor
     */
    public SnakeBoard() {
        startANewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO When the board is created: start the game
                JOptionPane.showMessageDialog(startANewGameButton, snakeBoardInitialScreenTextPane.getText() + "Dupa");
            }
        });

        setTitle("Ssssssssssnake");
        setSize(700, 750);
        setResizable(false);
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game_board = new Snake_board(650, 650, 25);
        gui = new GameGUI(game_board, 15, 50);
        game = new SnakeGame(game_board);

        getContentPane().add(gui);
        gui.setLayout(null);
        score = new JLabel("Score");
        score.setHorizontalAlignment(SwingConstants.LEFT);
        score.setBounds(0, 0, 200, 50);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        topScore = new JLabel("Top score");
        topScore.setHorizontalAlignment(SwingConstants.RIGHT);
        topScore.setBounds(0, 0, 400, 50);
        topScore.setForeground(Color.WHITE);
        topScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        game_over = new JLabel();
        game_over.setHorizontalAlignment(SwingConstants.CENTER);
        game_over.setBounds(0, 100, 700, 50);
        game_over.setForeground(Color.WHITE);
        game_over.setFont(new Font("Comic Sans MS", Font.BOLD, 40));

        gui.add(score);
        gui.add(topScore);
    }

    /**
     * Creates a new file with (0) if no topScore file exists
     * @throws IOException
     */
    public void createTopScoreFile() throws IOException {
        FileWriter myWriter = new FileWriter(top_score_path);
        myWriter.write("0");
        myWriter.close();
    }

    /**
     * writes new top score to file
     * @param score - score to be written
     * @throws IOException
     */
    public void writeTopScore(int score) throws IOException {
        FileWriter myWriter = new FileWriter(top_score_path);
        myWriter.write(Integer.toString(score));
        myWriter.close();
    }

    /**
     * reads top score from file
     * @return top score integer
     * @throws IOException
     */
    public int readTopScore() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(top_score_path));
        int topScore = Integer.parseInt(reader.readLine());
        reader.close();
        return topScore;
    }

    /**
     * Start game
     */
    public void startGame() throws IOException {
        File f = new File(top_score_path);
        if (!f.exists() && !f.isDirectory()) {
            createTopScoreFile();
        }
        game.start();
        topScore.setText("Top Score: " + readTopScore());
        gui.redraw();
    }

    /**
     * Game loop
     */
    public void runGameLoop() throws IOException {
        while (true) {
            game.move();
            score.setText("Score: " + Integer.toString(game.getScore()));
            if (game.checkEnd() == true) {
                if (game.getScore() > readTopScore()) {
                    writeTopScore(game.getScore());
                }
                topScore.setText("Top Score: " + readTopScore());
                game_over.setText("GAME OVER");
                gui.add(game_over);
                gui.redraw();
                restart_clicked = false;
                while (restart_clicked == false) {
                }
                gui.remove(game_over);
                gui.redraw();
                game.restartGame();
            }
            gui.redraw();
        }
    }

    public static void main(String[] args) throws IOException {

        SnakeBoard board = new SnakeBoard();

        EventQueue.invokeLater(() -> board.setVisible(true));

        board.startGame();

        board.runGameLoop();

    }

    /**
     * Event to be processed upon typing a key
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Event to be processed upon releasing a key
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Generally, the WSAD steering of usr snake
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        if (c == 32) {
            restart_clicked = true;
        } else if (c == 39 && !game.getUsrSnakeDirection().equals(Snake.SnakeMovement.LEFT)) {
            this.game.setUsrSnakeDirection(Snake.SnakeMovement.RIGHT);
        } else if (c == 37 && !game.getUsrSnakeDirection().equals(Snake.SnakeMovement.RIGHT)) {
            this.game.setUsrSnakeDirection(Snake.SnakeMovement.LEFT);
        } else if (c == 38 && !game.getUsrSnakeDirection().equals(Snake.SnakeMovement.DOWN)) {
            this.game.setUsrSnakeDirection(Snake.SnakeMovement.UP);
        } else if (c == 40 && !game.getUsrSnakeDirection().equals(Snake.SnakeMovement.UP)) {
            this.game.setUsrSnakeDirection(Snake.SnakeMovement.DOWN);
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


