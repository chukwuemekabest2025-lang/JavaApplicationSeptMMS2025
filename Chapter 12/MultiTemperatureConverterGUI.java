import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class MultiTemperatureConverterGUI extends JFrame {

    private final JTextField inputField;
    private final JComboBox<String> fromCombo;
    private final JComboBox<String> toCombo;
    private final JLabel resultLabel;

    public MultiTemperatureConverterGUI() {
        super("Multi-Scale Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        String[] scales = {"Fahrenheit", "Celsius", "Kelvin"};

        // Row 1: Input Field
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Temperature:"));
        inputField = new JTextField(10);
        inputPanel.add(inputField);

        // Row 2: Select Scales
        JPanel scalePanel = new JPanel(new FlowLayout());
        fromCombo = new JComboBox<>(scales);
        toCombo = new JComboBox<>(scales);
        toCombo.setSelectedIndex(1); // Default to Celsius
        scalePanel.add(new JLabel("From:"));
        scalePanel.add(fromCombo);
        scalePanel.add(new JLabel("To:"));
        scalePanel.add(toCombo);

        // Row 3: Convert Button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton convertButton = new JButton("Convert");
        buttonPanel.add(convertButton);

        // Row 4: Result Display
        resultLabel = new JLabel("Converted Temperature: --", JLabel.CENTER);

        convertButton.addActionListener(e -> convertTemperature());

        add(inputPanel);
        add(scalePanel);
        add(buttonPanel);
        add(resultLabel);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void convertTemperature() {
        try {
            double temp = Double.parseDouble(inputField.getText());
            String from = (String) fromCombo.getSelectedItem();
            String to = (String) toCombo.getSelectedItem();

            // First convert input scale to Celsius
            double celsius = switch (from) {
                case "Fahrenheit" -> (5.0 / 9.0) * (temp - 32.0);
                case "Kelvin" -> temp - 273.15;
                default -> temp;
            };

            // Convert Celsius to target scale
            double result = switch (to) {
                case "Fahrenheit" -> (celsius * 9.0 / 5.0) + 32.0;
                case "Kelvin" -> celsius + 273.15;
                default -> celsius;
            };

            resultLabel.setText(String.format("Converted Temperature: %.2f °%s", result, to.charAt(0)));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultiTemperatureConverterGUI().setVisible(true));
    }
}