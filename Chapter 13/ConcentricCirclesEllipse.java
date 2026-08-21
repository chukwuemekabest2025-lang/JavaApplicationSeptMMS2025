import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class ConcentricCirclesEllipse extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;
        double startingRadius = 20.0;
        double gap = 10.0;

        for (int i = 0; i < 8; i++) {
            double radius = startingRadius + (i * gap);
            double diameter = radius * 2.0;
            double x = centerX - radius;
            double y = centerY - radius;

            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
            g2d.draw(circle);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.7 Concentric Circles (Ellipse2D.Double)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ConcentricCirclesEllipse());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}