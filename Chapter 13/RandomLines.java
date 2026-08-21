import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class RandomLines extends JPanel {
    private static final Random random = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < 20; i++) {
            double x1 = random.nextDouble() * width;
            double y1 = random.nextDouble() * height;
            double x2 = random.nextDouble() * width;
            double y2 = random.nextDouble() * height;

            Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            float randomThickness = 1.0f + random.nextFloat() * 9.0f; // 1.0 to 10.0

            g2d.setColor(randomColor);
            g2d.setStroke(new BasicStroke(randomThickness));
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.8 Random Lines (Line2D.Double)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RandomLines());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}