import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {

    private final JTextField fahrenheitField;
    private final JLabel resultLabel;

    public TemperatureConverterGUI() {
        super("Temperature Converter");
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        // Prompt label and input text field
        JLabel inputLabel = new JLabel("Enter Fahrenheit temperature:");
        fahrenheitField = new JTextField(10);

        // Label to display converted result
        resultLabel = new JLabel("Temperature in Celsius: --", SwingConstants.CENTER);

        // Add action listener to input field (triggers when pressing Enter)
        fahrenheitField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                    // Formula: C = (5 / 9) * (F - 32)
                    double celsius = (5.0 / 9.0) * (fahrenheit - 32);
                    resultLabel.setText(String.format("Temperature in Celsius: %.2f °C", celsius));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        TemperatureConverterGUI.this,
                        "Please enter a valid numeric value.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        add(inputLabel);
        add(fahrenheitField);
        add(resultLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 120);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TemperatureConverterGUI().setVisible(true);
        });
    }
}