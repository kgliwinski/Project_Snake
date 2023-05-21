public class Object {
    public enum ObjectType{
        USER_SNAKE,
        AI_SNAKE,
        FRUIT,
        FROG
    }
    protected int top_left_x;
    protected int top_left_y;
    protected ObjectType type;

    public Object(int top_left_x, int top_left_y, ObjectType type) {
        this.top_left_x = top_left_x;
        this.top_left_y = top_left_y;
        this.type = type;
    }

    public boolean intersects(Object object) {
        return  this.top_left_x == object.top_left_x && this.top_left_y == object.top_left_y;
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

    public ObjectType getType() {
        return  type;
    }

    static public boolean intersect(Object obj1, Object obj2) {
        return obj1.getTopLeft_x() == obj2.getTopLeft_x() &&
                obj1.getTopLeft_y() == obj2.getTopLeft_y();
    }
}
