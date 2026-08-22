import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class AlignGUI extends JFrame {

    public AlignGUI() {
        super("Align");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Left Panel: Checkboxes stacked vertically
        JPanel checkPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JCheckBox snapToGrid = new JCheckBox("Snap to Grid");
        JCheckBox showGrid = new JCheckBox("Show Grid");
        checkPanel.add(snapToGrid);
        checkPanel.add(showGrid);

        // Center Panel: Labels and TextFields aligned using GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Row 0: X: [ 8 ]
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("X:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField xField = new JTextField("8", 3);
        inputPanel.add(xField, gbc);

        // Row 1: Y: [ 8 ]
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Y:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField yField = new JTextField("8", 3);
        inputPanel.add(yField, gbc);

        // Right Panel: Action Buttons stacked vertically
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        JButton helpButton = new JButton("Help");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(helpButton);

        // Add sub-panels to main frame layout
        add(checkPanel);
        add(inputPanel);
        add(buttonPanel);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AlignGUI().setVisible(true);
        });
    }
}