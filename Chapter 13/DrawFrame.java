import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {
    private final DrawPanel drawPanel = new DrawPanel();

    private Color color1 = Color.BLACK;
    private Color color2 = Color.WHITE;

    private final JCheckBox useGradient = new JCheckBox("Use Gradient");
    private final JButton color1Btn = new JButton("Color 1");
    private final JButton color2Btn = new JButton("Color 2");
    private final JTextField widthField = new JTextField("1", 3);
    private final JTextField dashField = new JTextField("10", 3);
    private final JCheckBox dashedCheck = new JCheckBox("Dashed");

    public DrawFrame() {
        super("13.31 Java 2D Drawing Application");

        // Top Control Row 1 (Standard controls)
        JPanel topPanel1 = new JPanel();
        JButton undoBtn = new JButton("Undo");
        JButton clearBtn = new JButton("Clear");
        JComboBox<String> shapeChooser = new JComboBox<>(new String[]{"Line", "Rectangle", "Oval"});
        JCheckBox filledCheck = new JCheckBox("Filled");

        undoBtn.addActionListener(e -> drawPanel.clearLastShape());
        clearBtn.addActionListener(e -> drawPanel.clearDrawing());
        shapeChooser.addActionListener(e -> drawPanel.setShapeType(shapeChooser.getSelectedIndex()));
        filledCheck.addActionListener(e -> drawPanel.setFilled(filledCheck.isSelected()));

        topPanel1.add(undoBtn);
        topPanel1.add(clearBtn);
        topPanel1.add(shapeChooser);
        topPanel1.add(filledCheck);

        // Top Control Row 2 (Java 2D controls)
        JPanel topPanel2 = new JPanel();
        color1Btn.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(this, "Select Color 1", color1);
            if (chosen != null) { color1 = chosen; updateJava2DProps(); }
        });

        color2Btn.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(this, "Select Color 2", color2);
            if (chosen != null) { color2 = chosen; updateJava2DProps(); }
        });

        useGradient.addActionListener(e -> updateJava2DProps());
        dashedCheck.addActionListener(e -> updateJava2DProps());

        topPanel2.add(useGradient);
        topPanel2.add(color1Btn);
        topPanel2.add(color2Btn);
        topPanel2.add(new JLabel("Width:"));
        topPanel2.add(widthField);
        topPanel2.add(new JLabel("Dash Length:"));
        topPanel2.add(dashField);
        topPanel2.add(dashedCheck);

        JPanel headerContainer = new JPanel(new GridLayout(2, 1));
        headerContainer.add(topPanel1);
        headerContainer.add(topPanel2);

        add(headerContainer, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        updateJava2DProps();
    }

    private void updateJava2DProps() {
        // Handle Paint
        if (useGradient.isSelected()) {
            drawPanel.setCurrentPaint(new GradientPaint(0, 0, color1, 50, 50, color2, true));
        } else {
            drawPanel.setCurrentPaint(color1);
        }

        // Handle Stroke
        try {
            float width = Float.parseFloat(widthField.getText());
            if (dashedCheck.isSelected()) {
                float dash = Float.parseFloat(dashField.getText());
                float[] dashes = {dash};
                drawPanel.setCurrentStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
            } else {
                drawPanel.setCurrentStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            }
        } catch (NumberFormatException ignored) {}
    }

    public static void main(String[] args) {
        DrawFrame frame = new DrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 600);
        frame.setVisible(true);
    }
}