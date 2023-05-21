import java.awt.*;
import java.util.ArrayList;
public class Snake {
    public enum SnakeMovement{
        UP, DOWN, LEFT, RIGHT
    }
    int grid_size;
    SnakeMovement direction;
    private ArrayList<Object> body = new ArrayList<Object>();
    Object tail_previous_pos;
    Object.ObjectType snake_type;

    public Snake(int top_left_x, int top_left_y, int grid_size) {
        this.grid_size = grid_size;
        direction = SnakeMovement.RIGHT;
        snake_type = Object.ObjectType.USER_SNAKE;

        for (int i = 0; i < 4; ++i) {
            body.add(new Object(
                    top_left_x - grid_size * i,
                    top_left_y, snake_type)
            );
        }
    }

    public void move() {
        // Update previous tail position
        Object tail = body.get(body.size() - 1);
        tail_previous_pos = new Object(
                tail.getTopLeft_x(),
                tail.getTopLeft_y(), snake_type);

        // Move body
        for (int i = (body.size() - 1); i > 0 ; --i) {
            body.get(i).setPosition(
                    body.get(i-1).getTopLeft_x(),
                    body.get(i-1).getTopLeft_y());
//            System.out.printf("%d x: %d, y: %d\n", i, body.get(i).getTopLeft_x(), body.get(i).getTopLeft_y());
        }

        // Move head
        this.direction = direction;
        switch (this.direction) {
            case UP:
                this.body.get(0).move(0, -grid_size);
                break;
            case DOWN:
                this.body.get(0).move(0, grid_size);
                break;
            case LEFT:
                this.body.get(0).move(-grid_size, 0);
                break;
            case RIGHT:
                this.body.get(0).move(grid_size, 0);
                break;
        }
//        System.out.printf("H x: %d, y: %d\n", body.get(0).getTopLeft_x(), body.get(0).getTopLeft_y());

    }

    public void grow() {
        body.add(tail_previous_pos);
    }

    public void setDirection(SnakeMovement direction) {
        synchronized (this.direction) {
            this.direction = direction;
        }
    }

    public SnakeMovement getDirection() {
        synchronized (this.direction) {
            return this.direction;
        }
    }

    public ArrayList<Object> getBody() {
        return this.body;
    }
}
