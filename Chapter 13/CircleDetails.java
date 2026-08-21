import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class CircleDetails extends JPanel {
    private final double radius;
    private final double x;
    private final double y;

    public CircleDetails(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double diameter = 2 * radius;
        double circumference = 2 * Math.PI * radius;
        double area = Math.PI * radius * radius;

        // Draw circle (x, y are treated as the top-left corner of the bounding box)
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
        g2d.draw(circle);

        // Display results on screen
        int textX = (int) x;
        int textY = (int) (y + diameter + 20);
        g2d.drawString(String.format("Diameter: %.2f", diameter), textX, textY);
        g2d.drawString(String.format("Circumference: %.2f", circumference), textX, textY + 15);
        g2d.drawString(String.format("Area: %.2f", area), textX, textY + 30);
    }

    public static void main(String[] args) {
        double r = Double.parseDouble(JOptionPane.showInputDialog("Enter Circle Radius:"));
        double x = Double.parseDouble(JOptionPane.showInputDialog("Enter X Coordinate:"));
        double y = Double.parseDouble(JOptionPane.showInputDialog("Enter Y Coordinate:"));

        JFrame frame = new JFrame("13.17 Circle Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CircleDetails(r, x, y));
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}