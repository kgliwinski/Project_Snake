import java.util.ArrayList;

public class Fruits implements GameBaseObject{
    private ArrayList<BoardElement> boardElements;
    protected Snake_board board;
    private BoardElement.Type type;
    protected int number_of_fruits;
    public Fruits(Snake_board board, int number_of_fruits) {
        type = BoardElement.Type.FRUIT;
        this.board = board;
        this.number_of_fruits = number_of_fruits;
        restart();
    }

    public void addFruit() {
        boardElements.add(new BoardElement(
                board.generatePosition_x(),
                board.generatePosition_y(),
                type
        ));
        board.addObject(boardElements.get(boardElements.size() - 1), type);
    }

    public void changePosition(int index) {
        synchronized (board) {
            BoardElement temp = boardElements.get(index);
            temp.setPosition(
                    board.generatePosition_x(),
                    board.generatePosition_y()
            );
        }
    }

    public ArrayList<BoardElement> getFruits() {
        synchronized (board) {
            return boardElements;
        }
    }

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

        board.addObjects(boardElements, type);
    }
}
