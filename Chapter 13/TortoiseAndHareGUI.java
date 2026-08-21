import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TortoiseAndHareGUI extends JPanel {
    private int tPos = 0;
    private int hPos = 0;
    private final Random rand = new Random();
    private final Timer timer;

    public TortoiseAndHareGUI() {
        // Initialize the timer instance first
        timer = new Timer(200, null);
        
        // Add the action listener after initialization
        timer.addActionListener(e -> {
            moveTortoise();
            moveHare();
            repaint();

            if (tPos >= 300 || hPos >= 300) {
                timer.stop();
                String winner = (tPos >= 300 && hPos >= 300) ? "IT'S A TIE!" :
                                (tPos >= 300) ? "TORTOISE WINS!!!" : "HARE WINS!";
                JOptionPane.showMessageDialog(this, winner);
            }
        });
        
        timer.start();
    }

    private void moveTortoise() {
        int r = rand.nextInt(10) + 1;
        if (r <= 5) tPos += 3;       // Fast plod
        else if (r <= 7) tPos -= 6;  // Slip
        else tPos += 1;             // Slow plod
        if (tPos < 0) tPos = 0;
    }

    private void moveHare() {
        int r = rand.nextInt(10) + 1;
        if (r <= 2) {}               // Sleep
        else if (r <= 4) hPos += 9;  // Big hop
        else if (r <= 5) hPos -= 12; // Big slip
        else if (r <= 8) hPos += 1;  // Small hop
        else hPos -= 2;              // Small slip
        if (hPos < 0) hPos = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        // Mountain Arc
        g.drawArc(-w, 0, w * 2, h * 2, 0, 90);

        // Map linear race steps (0-300) onto the arc angles (90 to 180 degrees)
        double tAngle = Math.toRadians(90 + ((double) tPos / 300) * 90);
        double hAngle = Math.toRadians(90 + ((double) hPos / 300) * 90);

        int tx = (int) (w * Math.cos(tAngle)) + w;
        int ty = h - (int) (h * Math.sin(tAngle));

        int hx = (int) (w * Math.cos(hAngle)) + w;
        int hy = h - (int) (h * Math.sin(hAngle));

        g.setColor(Color.RED);
        g.drawString("T", tx, ty - 5);

        g.setColor(Color.BLUE);
        g.drawString("H", hx + 5, hy);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.25 Tortoise and Hare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TortoiseAndHareGUI());
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}