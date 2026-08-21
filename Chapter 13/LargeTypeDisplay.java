import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;

public class LargeTypeDisplay extends JFrame {
    private final JTextArea textArea = new JTextArea("Type your text here...", 10, 30);
    private final JComboBox<String> fontChooser;
    private final JCheckBox boldCheck = new JCheckBox("Bold");
    private final JButton increaseBtn = new JButton("Increase Font Size");
    private final JButton decreaseBtn = new JButton("Decrease Font Size");

    private String currentFontFamily = "Serif";
    private int currentFontSize = 18;

    public LargeTypeDisplay() {
        super("13.32 Large-Type Display");

        Font uiFont = new Font("SansSerif", Font.PLAIN, 20);

        String[] fonts = {"Serif", "SansSerif", "Monospaced"};
        fontChooser = new JComboBox<>(fonts);
        fontChooser.setFont(uiFont);
        boldCheck.setFont(uiFont);
        increaseBtn.setFont(uiFont);
        decreaseBtn.setFont(uiFont);

        updateTextFont();

        fontChooser.addActionListener(e -> {
            currentFontFamily = (String) fontChooser.getSelectedItem();
            updateTextFont();
        });

        boldCheck.addActionListener(e -> updateTextFont());

        increaseBtn.addActionListener(e -> {
            currentFontSize++;
            updateTextFont();
        });

        decreaseBtn.addActionListener(e -> {
            if (currentFontSize > 6) {
                currentFontSize--;
                updateTextFont();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(fontChooser);
        controlPanel.add(boldCheck);
        controlPanel.add(increaseBtn);
        controlPanel.add(decreaseBtn);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void updateTextFont() {
        int style = boldCheck.isSelected() ? Font.BOLD : Font.PLAIN;
        textArea.setFont(new Font(currentFontFamily, style, currentFontSize));
    }

    public static void main(String[] args) {
        LargeTypeDisplay frame = new LargeTypeDisplay();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 500);
        frame.setVisible(true);
    }
}