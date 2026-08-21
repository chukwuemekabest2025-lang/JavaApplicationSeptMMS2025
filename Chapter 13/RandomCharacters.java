import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class RandomCharacters extends JPanel {
    private static final Random random = new Random();
    private static final String[] FONT_NAMES = {"Serif", "SansSerif", "Monospaced", "Dialog"};
    private static final int[] FONT_STYLES = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < 30; i++) {
            // Pick a random uppercase letter
            char randomChar = (char) ('A' + random.nextInt(26));

            String fontName = FONT_NAMES[random.nextInt(FONT_NAMES.length)];
            int fontStyle = FONT_STYLES[random.nextInt(FONT_STYLES.length)];
            int fontSize = 12 + random.nextInt(48); // Size 12 to 60

            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

            int x = random.nextInt(Math.max(1, width - 40));
            int y = random.nextInt(Math.max(1, height - 40)) + 30;

            g.drawString(String.valueOf(randomChar), x, y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.10 Random Characters");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RandomCharacters());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}