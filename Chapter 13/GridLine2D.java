import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class GridLine2D extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double width = getWidth();
        double height = getHeight();
        int rows = 8;
        int cols = 8;

        double rowHeight = height / rows;
        double colWidth = width / cols;

        // Horizontal lines
        for (int i = 0; i <= rows; i++) {
            double y = i * rowHeight;
            g2d.draw(new Line2D.Double(0, y, width, y));
        }

        // Vertical lines
        for (int j = 0; j <= cols; j++) {
            double x = j * colWidth;
            g2d.draw(new Line2D.Double(x, 0, x, height));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.12 8x8 Grid (Line2D.Double)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GridLine2D());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}