import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class RandomColorsShapes extends JPanel {
    private final Random random = new Random();
    private String selectedShape = "Rectangle";

    private final Color[] colors = {
        Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
        Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
        Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };

    public void setSelectedShape(String shape) {
        this.selectedShape = shape;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(Math.max(1, w - 100));
            int y = random.nextInt(Math.max(1, h - 100));
            int width = 20 + random.nextInt(100);
            int height = 20 + random.nextInt(100);

            g.setColor(colors[random.nextInt(colors.length)]);

            if (selectedShape.equals("Rectangle")) {
                g.drawRect(x, y, width, height);
            } else if (selectedShape.equals("Oval")) {
                g.drawOval(x, y, width, height);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.29 Random Colors");
        String[] shapes = {"Rectangle", "Oval"};
        JComboBox<String> shapeChooser = new JComboBox<>(shapes);

        RandomColorsShapes drawPanel = new RandomColorsShapes();

        shapeChooser.addActionListener(e -> {
            String shape = (String) shapeChooser.getSelectedItem();
            drawPanel.setSelectedShape(shape);
        });

        frame.setLayout(new BorderLayout());
        frame.add(shapeChooser, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}