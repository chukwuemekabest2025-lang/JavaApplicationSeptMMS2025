import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class ScreenSaverCustomLines extends JPanel implements ActionListener {
    private final Random random = new Random();
    private final Timer timer;
    private int lineCount = 100;

    public ScreenSaverCustomLines(JTextField inputField) {
        timer = new Timer(1000, this);
        timer.start();

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lineCount = Integer.parseInt(inputField.getText());
                    repaint();
                } catch (NumberFormatException ex) {
                    // Ignore invalid inputs
                }
            }
        });
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

        for (int i = 0; i < lineCount; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g.drawLine(random.nextInt(w), random.nextInt(h), random.nextInt(w), random.nextInt(h));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.20 Screen Saver (Dynamic Lines)");
        JTextField input = new JTextField("100", 10);
        
        ScreenSaverCustomLines panel = new ScreenSaverCustomLines(input);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(input, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}