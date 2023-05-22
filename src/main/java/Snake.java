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
    Snake_board board;

    public Snake(Snake_board board) {
        snake_type = Object.ObjectType.USER_SNAKE;
        direction = SnakeMovement.RIGHT;

        int top_left_x = board.generateSnakeStart_x();
        int top_left_y = board.generateSnakeStart_y();
        this.grid_size = board.getGrid();
        for (int i = 0; i < 4; ++i) {
            body.add(new Object(
                    top_left_x - grid_size * i,
                    top_left_y, snake_type)
            );
        }

        this.board = board;
        this.board.addObjects(body, snake_type);
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
        }

        // Move head
        switch (this.direction) {
            case UP:
                moveHead(0, -grid_size);
                break;
            case DOWN:
                moveHead(0, grid_size);
                break;
            case LEFT:
                moveHead(-grid_size, 0);
                break;
            case RIGHT:
                moveHead(grid_size, 0);
                break;
        }
    }

    private void moveHead(int x, int y) {
        if (this.body.get(0).getTopLeft_x() + x > board.getHeight() - grid_size) {
            x = -board.getHeight() + grid_size;
        } else if (this.body.get(0).getTopLeft_x() + x < 0) {
            x = board.getWidth() - grid_size;
        }

        if (this.body.get(0).getTopLeft_y() + y > board.getWidth() - grid_size) {
            y = -board.getHeight() + grid_size;
        } else if (this.body.get(0).getTopLeft_y() + y < 0) {
            y = board.getHeight() - grid_size;
        }
        this.body.get(0).move(x, y);
    }

    public void grow() {
        synchronized (board) {
            body.add(tail_previous_pos);
            board.addObject(body.get(body.size() - 1), snake_type);
        }
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

    public boolean checkCollision() {
        for (int i = 1; i < body.size(); ++i) {
            if (Object.intersect(body.get(0), body.get(i))) {
                return true;
            }
        }

        return  false;
    }

    public ArrayList<Object> getBody() {
        return this.body;
    }

}
