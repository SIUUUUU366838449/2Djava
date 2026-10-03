public class Player {
    int x = 500;
    int y = 300;
    int previousY = y;
    int previousX = x;

    double velocityY = 0;
    double gravity = 1.0;
    public void addVelocity() {
        velocityY += gravity;
    }
    public void updateY() {
        previousY = y;
        y += velocityY;
    }
    public void right() {
        previousX = x;
        x += 5;
    }
    public void left() {
        previousX = x;
        x -= 5;
    }
    public void jump() {
        if (velocityY == 0) {
            velocityY = -10;
        }
    }
    public void respawn() {
        velocityY = 0;
        x = 500;
        y = 300;
    }
}
