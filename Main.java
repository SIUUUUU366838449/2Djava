import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("second game!!!!!!!!");
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel();
        jFrame.add(gamePanel);



        jFrame.setVisible(true);
    }
}