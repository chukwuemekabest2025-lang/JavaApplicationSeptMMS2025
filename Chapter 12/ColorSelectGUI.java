import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class ColorSelectGUI extends JFrame {

    public ColorSelectGUI() {
        super("ColorSelect");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Top Panel: JComboBox (Color Dropdown)
        String[] colors = {"RED", "GREEN", "BLUE", "YELLOW", "BLACK"};
        JComboBox<String> colorComboBox = new JComboBox<>(colors);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(colorComboBox);

        // Center Panel: Checkboxes (Background, Foreground)
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JCheckBox backgroundCheck = new JCheckBox("Background");
        JCheckBox foregroundCheck = new JCheckBox("Foreground");
        checkPanel.add(backgroundCheck);
        checkPanel.add(foregroundCheck);

        // Bottom Panel: Buttons (Ok, Cancel)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Center container panel to stack checkPanel and buttonPanel vertically
        JPanel centerContainer = new JPanel(new BorderLayout(5, 5));
        centerContainer.add(checkPanel, BorderLayout.NORTH);
        centerContainer.add(buttonPanel, BorderLayout.CENTER);

        // Add to main frame layout
        add(topPanel, BorderLayout.NORTH);
        add(centerContainer, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ColorSelectGUI().setVisible(true);
        });
    }
}