import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ColorChooserShapes extends JPanel {
    private final Random random = new Random();
    private String selectedShape = "Rectangle";
    private Color currentColor = Color.BLACK;

    public void setSelectedShape(String shape) {
        this.selectedShape = shape;
        repaint();
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        g.setColor(currentColor);

        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(Math.max(1, w - 100));
            int y = random.nextInt(Math.max(1, h - 100));
            int width = 20 + random.nextInt(100);
            int height = 20 + random.nextInt(100);

            if (selectedShape.equals("Rectangle")) {
                g.drawRect(x, y, width, height);
            } else if (selectedShape.equals("Oval")) {
                g.drawOval(x, y, width, height);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.30 Color Chooser Shapes");
        String[] shapes = {"Rectangle", "Oval"};
        JComboBox<String> shapeChooser = new JComboBox<>(shapes);
        JButton colorBtn = new JButton("Choose Color");

        ColorChooserShapes drawPanel = new ColorChooserShapes();

        shapeChooser.addActionListener(e -> drawPanel.setSelectedShape((String) shapeChooser.getSelectedItem()));

        colorBtn.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(frame, "Select Shape Color", Color.BLACK);
            if (chosen != null) {
                drawPanel.setCurrentColor(chosen);
            }
        });

        JPanel controls = new JPanel();
        controls.add(shapeChooser);
        controls.add(colorBtn);

        frame.setLayout(new BorderLayout());
        frame.add(controls, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}