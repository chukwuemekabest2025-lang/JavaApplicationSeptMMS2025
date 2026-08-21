import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GridRectangle2D extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double width = getWidth();
        double height = getHeight();
        int rows = 10;
        int cols = 10;

        double cellWidth = width / cols;
        double cellHeight = height / rows;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = col * cellWidth;
                double y = row * cellHeight;
                Rectangle2D.Double cell = new Rectangle2D.Double(x, y, cellWidth, cellHeight);
                g2d.draw(cell);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.14 10x10 Grid (Rectangle2D.Double)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GridRectangle2D());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}