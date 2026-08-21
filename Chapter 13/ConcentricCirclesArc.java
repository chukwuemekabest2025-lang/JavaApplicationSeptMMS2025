import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class ConcentricCirclesArc extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int startingRadius = 20;
        int gap = 10;

        for (int i = 0; i < 8; i++) {
            int radius = startingRadius + (i * gap);
            int diameter = radius * 2;
            int x = centerX - radius;
            int y = centerY - radius;

            // An arc with startAngle=0 and arcAngle=360 forms a full circle
            g.drawArc(x, y, diameter, diameter, 0, 360);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.6 Concentric Circles (drawArc)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ConcentricCirclesArc());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}