public class BoardElement {
    public enum Type{
        USER_SNAKE,
        AI_SNAKE,
        FRUIT,
        FROG,
        OBSTACLE,
    }
    protected int top_left_x;
    protected int top_left_y;
    protected Type type;

    public BoardElement(int top_left_x, int top_left_y, Type type) {
        this.top_left_x = top_left_x;
        this.top_left_y = top_left_y;
        this.type = type;
    }

    public boolean intersects(BoardElement boardElement) {
        return  this.top_left_x == boardElement.top_left_x && this.top_left_y == boardElement.top_left_y;
    }

    public int getTopLeft_x() {
        return this.top_left_x;
    }

    public int getTopLeft_y() {
        return this.top_left_y;
    }

    public void move(int x, int y) {
        this.top_left_x += x;
        this.top_left_y += y;
    }
    public void setPosition(int x, int y) {
        this.top_left_x = x;
        this.top_left_y = y;
    }

    public Type getType() {
        return  type;
    }

    static public boolean intersect(BoardElement obj1, BoardElement obj2) {
        return obj1.getTopLeft_x() == obj2.getTopLeft_x() &&
                obj1.getTopLeft_y() == obj2.getTopLeft_y();
    }
}
