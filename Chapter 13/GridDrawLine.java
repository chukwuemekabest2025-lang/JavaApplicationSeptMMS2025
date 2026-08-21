import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GridDrawLine extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int rows = 8;
        int cols = 8;

        int rowHeight = height / rows;
        int colWidth = width / cols;

        // Draw horizontal lines
        for (int i = 0; i <= rows; i++) {
            int y = i * rowHeight;
            g.drawLine(0, y, width, y);
        }

        // Draw vertical lines
        for (int j = 0; j <= cols; j++) {
            int x = j * colWidth;
            g.drawLine(x, 0, x, height);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.11 8x8 Grid (drawLine)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GridDrawLine());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}