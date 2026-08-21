import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class ScreenSaver2D extends JPanel implements ActionListener {
    private final Random random = new Random();
    private final Timer timer;

    public ScreenSaver2D() {
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        for (int i = 0; i < 30; i++) {
            Color c1 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Color c2 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            double x1 = random.nextInt(w);
            double y1 = random.nextInt(h);
            double x2 = random.nextInt(w);
            double y2 = random.nextInt(h);

            GradientPaint gradient = new GradientPaint((float) x1, (float) y1, c1, (float) x2, (float) y2, c2);
            g2d.setPaint(gradient);

            double shapeW = 30 + random.nextInt(120);
            double shapeH = 30 + random.nextInt(120);

            if (random.nextBoolean()) {
                g2d.fill(new Rectangle2D.Double(x1, y1, shapeW, shapeH));
            } else {
                g2d.fill(new Ellipse2D.Double(x1, y1, shapeW, shapeH));
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.22 Screen Saver 2D API");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ScreenSaver2D());
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}