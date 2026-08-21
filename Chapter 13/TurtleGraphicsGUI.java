import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class TurtleGraphicsGUI extends JPanel {
    private int x = 250, y = 250;
    private int direction = 0; // 0: Right, 1: Down, 2: Left, 3: Up
    private boolean penDown = false;
    private final Path2D.Double path = new Path2D.Double();

    public TurtleGraphicsGUI() {
        path.moveTo(x, y);
    }

    public void turnRight() { direction = (direction + 1) % 4; }
    public void turnLeft() { direction = (direction + 3) % 4; }
    public void setPen(boolean down) { this.penDown = down; }

    public void move(int steps) {
        int pixels = steps * 10;
        int newX = x;
        int newY = y;

        switch (direction) {
            case 0 -> newX += pixels;
            case 1 -> newY += pixels;
            case 2 -> newX -= pixels;
            case 3 -> newY -= pixels;
        }

        if (penDown) {
            path.lineTo(newX, newY);
        } else {
            path.moveTo(newX, newY);
        }

        x = newX;
        y = newY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(path);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.23 Turtle Graphics");
        TurtleGraphicsGUI panel = new TurtleGraphicsGUI();

        JPanel controls = new JPanel();
        JButton penUpBtn = new JButton("Pen Up");
        JButton penDownBtn = new JButton("Pen Down");
        JButton turnRightBtn = new JButton("Turn Right");
        JButton turnLeftBtn = new JButton("Turn Left");
        JTextField moveField = new JTextField("5", 5);
        JButton moveBtn = new JButton("Move");

        penUpBtn.addActionListener(e -> panel.setPen(false));
        penDownBtn.addActionListener(e -> panel.setPen(true));
        turnRightBtn.addActionListener(e -> panel.turnRight());
        turnLeftBtn.addActionListener(e -> panel.turnLeft());
        moveBtn.addActionListener(e -> {
            try {
                int steps = Integer.parseInt(moveField.getText());
                panel.move(steps);
            } catch (NumberFormatException ignored) {}
        });

        controls.add(penUpBtn);
        controls.add(penDownBtn);
        controls.add(turnLeftBtn);
        controls.add(turnRightBtn);
        controls.add(new JLabel("Steps:"));
        controls.add(moveField);
        controls.add(moveBtn);

        frame.setLayout(new BorderLayout());
        frame.add(controls, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}