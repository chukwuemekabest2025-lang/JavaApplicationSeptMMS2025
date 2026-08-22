import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class DrawFrame extends JFrame {

    private final DrawPanel drawPanel;

    private static final String[] COLOR_NAMES = {
        "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green",
        "Light Gray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow"
    };

    private static final Color[] COLORS = {
        Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
        Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };

    private static final String[] SHAPE_NAMES = {"Line", "Rectangle", "Oval"};

    public DrawFrame() {
        super("Java Drawings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel statusLabel = new JLabel("(0,0)");
        drawPanel = new DrawPanel(statusLabel);

        // Control Panel components (NORTH)
        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton undoButton = new JButton("Undo");
        JButton clearButton = new JButton("Clear");
        JComboBox<String> colorChooser = new JComboBox<>(COLOR_NAMES);
        JComboBox<String> shapeChooser = new JComboBox<>(SHAPE_NAMES);
        JCheckBox filledCheckBox = new JCheckBox("Filled");

        controlPanel.add(undoButton);
        controlPanel.add(clearButton);
        controlPanel.add(colorChooser);
        controlPanel.add(shapeChooser);
        controlPanel.add(filledCheckBox);

        // Listeners for UI Controls
        undoButton.addActionListener(e -> drawPanel.clearLastShape());
        clearButton.addActionListener(e -> drawPanel.clearDrawing());
        colorChooser.addActionListener(e -> drawPanel.setCurrentColor(COLORS[colorChooser.getSelectedIndex()]));
        shapeChooser.addActionListener(e -> drawPanel.setShapeType(shapeChooser.getSelectedIndex()));
        filledCheckBox.addActionListener(e -> drawPanel.setFilledShape(filledCheckBox.isSelected()));

        // Frame Layout Configuration
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setSize(600, 450);
        setLocationRelativeTo(null);
    }
}

// Test Class to Run Application
class TestDraw {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawFrame().setVisible(true));
    }
}