import java.util.ArrayList;

/**
 * Class represent fruit
 */
public class Fruits implements GameBaseObject{
    /**
     * Stores fruits elements
     */
    private ArrayList<BoardElement> boardElements;

    protected Snake_board board;
    private BoardElement.Type type;
    protected int number_of_fruits;

    /**
     * Fruits constructor
     * @param board snake game board
     * @param number_of_fruits number of fruits to create
     */
    public Fruits(Snake_board board, int number_of_fruits) {
        type = BoardElement.Type.FRUIT;
        this.board = board;
        this.number_of_fruits = number_of_fruits;
        restart();
    }


    /**
     * Generate new position for fruits with this index
     * @param index index of fruit in ArrayList
     */
    public void changePosition(int index) {
        synchronized (board) {
            BoardElement temp = boardElements.get(index);
            temp.setPosition(
                    board.generatePosition_x(),
                    board.generatePosition_y()
            );
        }
    }

    /**
     * Restart fruits
     */
    @Override
    public void restart() {
        boardElements = new ArrayList<>();
        for (int i = 0; i < number_of_fruits; ++ i) {
            boardElements.add(new BoardElement(
                    board.generatePosition_x(),
                    board.generatePosition_y(),
                    type
            ));
        }

        board.addElements(boardElements, type);
    }
}
