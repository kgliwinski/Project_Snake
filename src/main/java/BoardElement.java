/**
 * Class represents board element
 */
public class BoardElement {
    /**
     * Element type
     */
    public enum Type {
        USER_SNAKE,
        AI_SNAKE,
        FRUIT,
        FROG,
        OBSTACLE,
    }
    protected int top_left_x;
    protected int top_left_y;
    protected Type type;

    /**
     * Board Element constructor
     * @param top_left_x top left x position
     * @param top_left_y top left y position
     * @param type type of element
     */
    public BoardElement(int top_left_x, int top_left_y, Type type) {
        this.top_left_x = top_left_x;
        this.top_left_y = top_left_y;
        this.type = type;
    }

    /**
     * Get top left x position
     * @return top left x position
     */
    public int getTopLeft_x() {
        return this.top_left_x;
    }

    /**
     * Get top left y position
     * @return top left y position
     */
    public int getTopLeft_y() {
        return this.top_left_y;
    }

    /**
     * Get Element type
     * @return type of element
     */
    public Type getType() {
        return  type;
    }

    /**
     * Move Element, arguments are added to current position
     * @param x translation in x
     * @param y translation in y
     */
    public void move(int x, int y) {
        this.top_left_x += x;
        this.top_left_y += y;
    }

    /**
     * Set Element new position
     * @param x top left x position
     * @param y top left y position
     */
    public void setPosition(int x, int y) {
        this.top_left_x = x;
        this.top_left_y = y;
    }

    /**
     * Check if element intersect
     * @param obj1 Element 1
     * @param obj2 Element 2
     * @return ture if intersects, false if not
     */
    static public boolean intersect(BoardElement obj1, BoardElement obj2) {
        return obj1.getTopLeft_x() == obj2.getTopLeft_x() &&
                obj1.getTopLeft_y() == obj2.getTopLeft_y();
    }
}
