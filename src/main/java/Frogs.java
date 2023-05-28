import java.util.ArrayList;
import java.util.Map;
public class Frogs implements GameBaseObject{

    private ArrayList<BoardElement> boardElements;

    protected Snake_board board;

    private BoardElement.Type type;

    protected int number_of_frogs;

    /**
     * Frogs constructor
     * @param board snake game board
     * @param number_of_frogs number of frogs to create
     */
    public Frogs(Snake_board board, int number_of_frogs){
        type = BoardElement.Type.FROG;
        this.board = board;
        this.number_of_frogs = number_of_frogs;
        restart();
    }

    public void changePosition(int index){
        synchronized (board) {
            BoardElement temp = boardElements.get(index);
            Map<String, Integer> frogMap = board.generateFrogPosition(temp.getTopLeft_x(), temp.getTopLeft_y());
            System.out.println(frogMap);
            int newPos_x = frogMap.get("x"), newPos_y = frogMap.get("y");
            int grid_size = board.getGrid();
            if(newPos_x > board.getHeight() - grid_size){
                newPos_x = -board.getWidth() + grid_size;
            } else if (newPos_x < 0) {
                newPos_x = board.getWidth() - grid_size;
            }
            if(newPos_y > board.getHeight() - grid_size){
                newPos_y = -board.getHeight() + grid_size;
            } else if (newPos_y < 0) {
                newPos_y = board.getHeight() - grid_size;
            }
            temp.setPosition(newPos_x, newPos_y);
        }
    }

    @Override
    public void restart(){
        boardElements = new ArrayList<>();
        for (int i = 0; i < number_of_frogs; ++ i) {
            boardElements.add(new BoardElement(
                    board.generatePosition_x(),
                    board.generatePosition_y(),
                    type
            ));
        }

        board.addElements(boardElements, type);
    }
}
