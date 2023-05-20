public class Rectangle {
    private int top_left_x;
    private int top_left_y;
    private int width;
    private int height;

    public Rectangle(int top_left_x, int top_left_y, int width, int height) {
        this.top_left_x = top_left_x;
        this.top_left_y = top_left_y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rectangle rectangle) {
        return  this.top_left_x == rectangle.top_left_x && this.top_left_y == rectangle.top_left_y;
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
}
