import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {
    boolean right = false;
    boolean left = false;
    boolean jump = true;
    Player player = new Player();
    int cameraX = 500;

    Ground[] grounds = {
        new Ground(400, 525, 400, 15),
            new Ground(400, 400, 200, 15),
            new Ground(200, 450, 1600, 15)
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(player.x - cameraX, player.y, 25,25);
        g.setColor(new Color(100, 100, 100));

        for (Ground ground : grounds) {
            g.fillRect(ground.x - cameraX, ground.y, ground.width, ground.height);

        }
    }
    Timer timer = new Timer(16, e -> {
        player.addVelocity();
        player.updateY();
        if (right) {
            player.right();

        }
        if (left) {
            player.left();

        }
        cameraX = player.x - (getWidth() - 25) / 2;

        if (player.y > 800) {
            player.respawn();
        }
        Crash();
        repaint();
    });
    public GamePanel() {
        timer.start();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }
    public void Crash() {
        for (Ground ground : grounds) {
            if (player.y + 25 >= ground.y
                    && player.x + 25 > ground.x
                    && player.x < ground.x + ground.width
            && player.velocityY > 0
            && player.previousY + 25 <= ground.y) {

                player.velocityY = 0;
                player.y = ground.y - 25;
                jump = false;
            }
            if (player.y <= ground.y + ground.height
            && player.previousY > ground.y + ground.height
            && player.velocityY < 0
            && player.x + 25 > ground.x
            && player.x < ground.x + ground.width) {
                player.velocityY = 0;
                player.y = ground.y + ground.height;
            }
            if (player.y + 25 > ground.y
            && player.y < ground.y + ground.height
            && player.previousX + 25 <= ground.x
            && player.x + 25 >= ground.x) {
                player.x = ground.x - 25;
            }

            if (player.y + 25 > ground.y
                    && player.y < ground.y + ground.height
                    && player.previousX >= ground.x + ground.width
                    && player.x <= ground.x + ground.width) {
                player.x = ground.x + ground.width;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!jump) {
                jump = true;
                player.jump();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            player.respawn();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
    }
}

