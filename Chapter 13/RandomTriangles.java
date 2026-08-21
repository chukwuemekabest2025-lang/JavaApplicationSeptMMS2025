import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.Random;

public class RandomTriangles extends JPanel {
    private static final Random random = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < 15; i++) {
            Path2D.Double triangle = new Path2D.Double();
            
            double x1 = random.nextDouble() * width;
            double y1 = random.nextDouble() * height;
            double x2 = random.nextDouble() * width;
            double y2 = random.nextDouble() * height;
            double x3 = random.nextDouble() * width;
            double y3 = random.nextDouble() * height;

            triangle.moveTo(x1, y1);
            triangle.lineTo(x2, y2);
            triangle.lineTo(x3, y3);
            triangle.closePath();

            Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g2d.setColor(randomColor);
            g2d.fill(triangle);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.9 Random Triangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RandomTriangles());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}