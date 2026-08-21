import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class SpiralPolyline extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int points = 80;
        int[] xPoints = new int[points];
        int[] yPoints = new int[points];

        int x = centerX;
        int y = centerY;
        int radius = 10;

        for (int i = 0; i < points; i++) {
            xPoints[i] = x;
            yPoints[i] = y;

            switch (i % 4) {
                case 0 -> y += radius;
                case 1 -> x -= radius;
                case 2 -> y -= radius;
                case 3 -> x += radius;
            }
            radius += 10;
        }

        g.drawPolyline(xPoints, yPoints, points);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.26 Spiral (drawPolyline)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SpiralPolyline());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}