import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class ScreenSaverShapes extends JPanel implements ActionListener {
    private final Random random = new Random();
    private final Timer timer;

    public ScreenSaverShapes() {
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

        int w = getWidth();
        int h = getHeight();

        for (int i = 0; i < 50; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

            int shapeType = random.nextInt(4);
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int sw = 20 + random.nextInt(100);
            int sh = 20 + random.nextInt(100);

            switch (shapeType) {
                case 0 -> g.drawLine(x, y, random.nextInt(w), random.nextInt(h));
                case 1 -> g.drawRect(x, y, sw, sh);
                case 2 -> g.drawOval(x, y, sw, sh);
                case 3 -> g.fillRect(x, y, sw, sh);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.21 Screen Saver Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ScreenSaverShapes());
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}