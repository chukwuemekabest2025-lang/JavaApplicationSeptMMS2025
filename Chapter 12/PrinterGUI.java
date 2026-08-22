import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class PrinterGUI extends JFrame {

    public PrinterGUI() {
        super("Printer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Center Main Container
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Header Label: Printer: MyPrinter
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(new JLabel("Printer: MyPrinter"), gbc);

        // Reset gridwidth
        gbc.gridwidth = 1;

        // Blank Panel 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(createWhitePanel(), gbc);

        // Checkbox Panel (Image, Text, Code)
        JPanel checkPanel = new JPanel(new GridLayout(3, 1));
        checkPanel.add(new JCheckBox("Image"));
        checkPanel.add(new JCheckBox("Text"));
        checkPanel.add(new JCheckBox("Code"));
        gbc.gridx = 1;
        centerPanel.add(checkPanel, gbc);

        // Blank Panel 2
        gbc.gridx = 2;
        centerPanel.add(createWhitePanel(), gbc);

        // Radio Button Panel (Selection, All, Applet)
        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        JRadioButton selectionRadio = new JRadioButton("Selection");
        JRadioButton allRadio = new JRadioButton("All", true); // Default selected
        JRadioButton appletRadio = new JRadioButton("Applet");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(selectionRadio);
        radioGroup.add(allRadio);
        radioGroup.add(appletRadio);

        radioPanel.add(selectionRadio);
        radioPanel.add(allRadio);
        radioPanel.add(appletRadio);

        gbc.gridx = 3;
        centerPanel.add(radioPanel, gbc);

        // Blank Panel 3
        gbc.gridx = 4;
        centerPanel.add(createWhitePanel(), gbc);

        // Bottom Controls Row: Print Quality, ComboBox, Print to File
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        bottomPanel.add(new JLabel("Print Quality:"));
        bottomPanel.add(new JComboBox<>(new String[]{"High", "Medium", "Low"}));
        bottomPanel.add(new JCheckBox("Print to File"));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(bottomPanel, gbc);

        // Right Side Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancel"));
        buttonPanel.add(new JButton("Setup..."));
        buttonPanel.add(new JButton("Help"));

        // Wrap buttonPanel in a FlowLayout to align nicely at the top right
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
        rightWrapper.add(buttonPanel);

        // Add main sections to frame layout
        add(centerPanel, BorderLayout.CENTER);
        add(rightWrapper, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // Helper method to generate the white placeholder box components
    private JPanel createWhitePanel() {
        JPanel p = new JPanel();
        p.setBackground(java.awt.Color.WHITE);
        p.setPreferredSize(new Dimension(50, 70));
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrinterGUI().setVisible(true);
        });
    }
}