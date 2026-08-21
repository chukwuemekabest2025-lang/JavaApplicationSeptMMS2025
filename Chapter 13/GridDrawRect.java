import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GridDrawRect extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int rows = 10;
        int cols = 10;

        int cellWidth = width / cols;
        int cellHeight = height / rows;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;
                g.drawRect(x, y, cellWidth, cellHeight);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.13 10x10 Grid (drawRect)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GridDrawRect());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}