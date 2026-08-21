import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Arc2D;

public class PieChart extends JPanel {
    private final double[] values = new double[4];

    public PieChart(double v1, double v2, double v3, double v4) {
        values[0] = v1;
        values[1] = v2;
        values[2] = v3;
        values[3] = v4;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double total = values[0] + values[1] + values[2] + values[3];
        if (total == 0) return;

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};

        double currentAngle = 0;
        double x = 100, y = 50, size = 300;

        for (int i = 0; i < 4; i++) {
            double extent = (values[i] / total) * 360.0;
            g2d.setColor(colors[i]);
            g2d.fill(new Arc2D.Double(x, y, size, size, currentAngle, extent, Arc2D.PIE));
            currentAngle += extent;
        }
    }

    public static void main(String[] args) {
        double v1 = Double.parseDouble(JOptionPane.showInputDialog("Enter Number 1:"));
        double v2 = Double.parseDouble(JOptionPane.showInputDialog("Enter Number 2:"));
        double v3 = Double.parseDouble(JOptionPane.showInputDialog("Enter Number 3:"));
        double v4 = Double.parseDouble(JOptionPane.showInputDialog("Enter Number 4:"));

        JFrame frame = new JFrame("13.27 Pie Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PieChart(v1, v2, v3, v4));
        frame.setSize(500, 450);
        frame.setVisible(true);
    }
}