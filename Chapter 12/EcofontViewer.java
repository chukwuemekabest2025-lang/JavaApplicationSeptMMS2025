import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class EcofontViewer extends JFrame {

    private Font ecofont;
    private float fontSize = 9.0f; // Default starting font size

    private final JTextArea textArea;
    private final JLabel sizeLabel;

    public EcofontViewer() {
        super("Ecofont Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Load custom Ecofont from local file if available, otherwise fallback
        loadEcofont();

        // 1. Initialize sizeLabel FIRST to avoid NullPointerException
        sizeLabel = new JLabel("Font Size: 9 pt");

        // 2. Main Text Display Area
        textArea = new JTextArea("Type your text here to test Ecofont...\nThe quick brown fox jumps over the lazy dog.", 8, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        // 3. Now safe to call updateTextFont()
        updateTextFont();

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Control Buttons
        JButton increaseBtn = new JButton("Increase Font Size");
        JButton decreaseBtn = new JButton("Decrease Font Size");

        increaseBtn.addActionListener(e -> {
            fontSize += 1.0f;
            updateTextFont();
        });

        decreaseBtn.addActionListener(e -> {
            if (fontSize > 1.0f) {
                fontSize -= 1.0f;
                updateTextFont();
            }
        });

        // Top Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        controlPanel.add(increaseBtn);
        controlPanel.add(decreaseBtn);
        controlPanel.add(sizeLabel);

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(500, 350);
        setLocationRelativeTo(null);
    }

    private void loadEcofont() {
        File fontFile = new File("Spranq_eco_sans_regular.ttf");
        if (fontFile.exists()) {
            try {
                ecofont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(ecofont);
            } catch (FontFormatException | IOException e) {
                ecofont = new Font("Verdana", Font.PLAIN, 9);
            }
        } else {
            // Fallback font if TTF file is not present in folder
            ecofont = new Font("Verdana", Font.PLAIN, 9);
        }
    }

    private void updateTextFont() {
        ecofont = ecofont.deriveFont(fontSize);
        textArea.setFont(ecofont);
        sizeLabel.setText(String.format("Font Size: %.0f pt", fontSize));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EcofontViewer().setVisible(true));
    }
}