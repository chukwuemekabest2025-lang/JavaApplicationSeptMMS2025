import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class TypingTutor extends JFrame implements KeyListener {

    private final JTextArea typingArea;
    private final JLabel targetLabel;
    private final JLabel statsLabel;

    private final Map<Integer, JButton> keyMap = new HashMap<>();
    private final Map<Integer, Color> defaultColors = new HashMap<>();

    private static final Color HIGHLIGHT_COLOR = new Color(255, 230, 150);

    // Target Pangram for Accuracy Monitoring
    private final String targetText = "The quick brown fox jumps over a lazy dog.";
    private int correctKeystrokes = 0;
    private int incorrectKeystrokes = 0;

    // Keyboard layout rows
    private static final String[][] KEY_ROWS = {
        {"~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "Backspace"},
        {"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\"},
        {"Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "Enter"},
        {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "?", "^"}
    };

    // Virtual KeyCodes corresponding to each string in the layout
    private static final int[][] KEY_CODES = {
        {
            KeyEvent.VK_BACK_QUOTE, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4,
            KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9,
            KeyEvent.VK_0, KeyEvent.VK_MINUS, KeyEvent.VK_EQUALS, KeyEvent.VK_BACK_SPACE
        },
        {
            KeyEvent.VK_TAB, KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_R,
            KeyEvent.VK_T, KeyEvent.VK_Y, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_O,
            KeyEvent.VK_P, KeyEvent.VK_OPEN_BRACKET, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_BACK_SLASH
        },
        {
            KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F,
            KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L,
            KeyEvent.VK_SEMICOLON, KeyEvent.VK_QUOTE, KeyEvent.VK_ENTER
        },
        {
            KeyEvent.VK_SHIFT, KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C, KeyEvent.VK_V,
            KeyEvent.VK_B, KeyEvent.VK_N, KeyEvent.VK_M, KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD,
            KeyEvent.VK_SLASH, KeyEvent.VK_UP
        }
    };

    public TypingTutor() {
        super("Typing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top Information Header
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JLabel instructLabel = new JLabel("<html>Type some text using your keyboard. The keys you press will be highlighted.<br>Note: Clicking the buttons with your mouse will not perform any action.</html>");
        instructLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        targetLabel = new JLabel("Target: " + targetText);
        targetLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        targetLabel.setForeground(new Color(0, 102, 204));

        topPanel.add(instructLabel, BorderLayout.NORTH);
        topPanel.add(targetLabel, BorderLayout.CENTER);

        // Center Text Area
        typingArea = new JTextArea(6, 45);
        typingArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.addKeyListener(this);

        JScrollPane scrollPane = new JScrollPane(typingArea);

        // Center Container
        JPanel centerContainer = new JPanel(new BorderLayout(5, 5));
        centerContainer.add(topPanel, BorderLayout.NORTH);
        centerContainer.add(scrollPane, BorderLayout.CENTER);

        // Keyboard Panel
        JPanel keyboardPanel = buildVirtualKeyboard();

        // Bottom Stats Bar
        statsLabel = new JLabel("Correct Keystrokes: 0 | Incorrect Keystrokes: 0 | Accuracy: 100%");
        statsLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.add(keyboardPanel, BorderLayout.CENTER);
        southPanel.add(statsLabel, BorderLayout.SOUTH);

        add(centerContainer, BorderLayout.NORTH);
        add(southPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel buildVirtualKeyboard() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        // Build standard 4 key rows
        for (int row = 0; row < KEY_ROWS.length; row++) {
            gbc.gridy = row;
            for (int col = 0; col < KEY_ROWS[row].length; col++) {
                gbc.gridx = col;
                JButton btn = new JButton(KEY_ROWS[row][col]);
                btn.setFocusable(false); // Ignore mouse focus

                int vkCode = KEY_CODES[row][col];
                keyMap.put(vkCode, btn);
                defaultColors.put(vkCode, btn.getBackground());

                panel.add(btn, gbc);
            }
        }

        // Row 4: Spacebar and Arrow Keys
        gbc.gridy = 4;

        // Spacebar
        JButton spaceBtn = new JButton("");
        spaceBtn.setFocusable(false);
        gbc.gridx = 3;
        gbc.gridwidth = 6;
        keyMap.put(KeyEvent.VK_SPACE, spaceBtn);
        defaultColors.put(KeyEvent.VK_SPACE, spaceBtn.getBackground());
        panel.add(spaceBtn, gbc);

        // Reset gridwidth for arrow keys
        gbc.gridwidth = 1;

        // Left Arrow (<)
        JButton leftBtn = new JButton("<");
        leftBtn.setFocusable(false);
        gbc.gridx = 10;
        keyMap.put(KeyEvent.VK_LEFT, leftBtn);
        defaultColors.put(KeyEvent.VK_LEFT, leftBtn.getBackground());
        panel.add(leftBtn, gbc);

        // Down Arrow (v)
        JButton downBtn = new JButton("v");
        downBtn.setFocusable(false);
        gbc.gridx = 11;
        keyMap.put(KeyEvent.VK_DOWN, downBtn);
        defaultColors.put(KeyEvent.VK_DOWN, downBtn.getBackground());
        panel.add(downBtn, gbc);

        // Right Arrow (>)
        JButton rightBtn = new JButton(">");
        rightBtn.setFocusable(false);
        gbc.gridx = 12;
        keyMap.put(KeyEvent.VK_RIGHT, rightBtn);
        defaultColors.put(KeyEvent.VK_RIGHT, rightBtn.getBackground());
        panel.add(rightBtn, gbc);

        return panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (keyMap.containsKey(code)) {
            keyMap.get(code).setBackground(HIGHLIGHT_COLOR);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (keyMap.containsKey(code)) {
            keyMap.get(code).setBackground(defaultColors.get(code));
        }

        // Accuracy Check against target text
        checkAccuracy(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handled via keyPressed / keyReleased
    }

    private void checkAccuracy(char typedChar) {
        String currentText = typingArea.getText();
        int index = currentText.length() - 1;

        if (index >= 0 && index < targetText.length()) {
            if (typedChar == targetText.charAt(index)) {
                correctKeystrokes++;
            } else {
                incorrectKeystrokes++;
            }

            int total = correctKeystrokes + incorrectKeystrokes;
            double accuracy = (total > 0) ? ((double) correctKeystrokes / total) * 100.0 : 100.0;

            statsLabel.setText(String.format(
                "Correct Keystrokes: %d | Incorrect Keystrokes: %d | Accuracy: %.1f%%",
                correctKeystrokes, incorrectKeystrokes, accuracy
            ));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TypingTutor().setVisible(true));
    }
}