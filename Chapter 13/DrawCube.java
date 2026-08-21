import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class DrawCube extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Front face coordinates
        double x = 100, y = 150, size = 150;
        // Offset for perspective (back face)
        double offset = 60;

        Path2D.Double cube = new Path2D.Double();

        // Front face square
        cube.moveTo(x, y);
        cube.lineTo(x + size, y);
        cube.lineTo(x + size, y + size);
        cube.lineTo(x, y + size);
        cube.closePath();

        // Back face square
        cube.moveTo(x + offset, y - offset);
        cube.lineTo(x + size + offset, y - offset);
        cube.lineTo(x + size + offset, y + size - offset);
        cube.lineTo(x + offset, y + size - offset);
        cube.closePath();

        // Connecting lines between front and back faces
        cube.moveTo(x, y);
        cube.lineTo(x + offset, y - offset);

        cube.moveTo(x + size, y);
        cube.lineTo(x + size + offset, y - offset);

        cube.moveTo(x + size, y + size);
        cube.lineTo(x + size + offset, y + size - offset);

        cube.moveTo(x, y + size);
        cube.lineTo(x + offset, y + size - offset);

        g2d.draw(cube);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.16 Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawCube());
        frame.setSize(450, 450);
        frame.setVisible(true);
    }
}