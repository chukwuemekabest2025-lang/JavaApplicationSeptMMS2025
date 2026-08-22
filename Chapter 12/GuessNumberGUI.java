import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class GuessNumberGUI extends JFrame {

    private int randomNumber;
    private int previousDistance = -1;

    private final JLabel promptLabel;
    private final JLabel statusLabel;
    private final JTextField guessField;
    private final JButton playAgainButton;
    private final JPanel backgroundPanel;

    private static final SecureRandom random = new SecureRandom();

    public GuessNumberGUI() {
        super("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        promptLabel = new JLabel("I have a number between 1 and 1000. Can you guess my number?", JLabel.CENTER);
        statusLabel = new JLabel("Please enter your first guess.", JLabel.CENTER);
        guessField = new JTextField(10);
        playAgainButton = new JButton("Play Again");

        JPanel inputPanel = new JPanel();
        inputPanel.add(guessField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);

        backgroundPanel.add(promptLabel);
        backgroundPanel.add(statusLabel);
        backgroundPanel.add(inputPanel);
        backgroundPanel.add(buttonPanel);

        add(backgroundPanel);

        guessField.addActionListener(new GuessHandler());
        playAgainButton.addActionListener(e -> resetGame());

        resetGame();

        setSize(450, 220);
        setLocationRelativeTo(null);
    }

    private void resetGame() {
        randomNumber = 1 + random.nextInt(1000);
        previousDistance = -1;
        guessField.setEditable(true);
        guessField.setText("");
        statusLabel.setText("Please enter your first guess.");
        backgroundPanel.setBackground(Color.LIGHT_GRAY);
    }

    private class GuessHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                int currentDistance = Math.abs(randomNumber - guess);

                if (guess == randomNumber) {
                    statusLabel.setText("Correct!");
                    backgroundPanel.setBackground(Color.GREEN);
                    guessField.setEditable(false);
                } else {
                    if (guess < randomNumber) {
                        statusLabel.setText("Too Low");
                    } else {
                        statusLabel.setText("Too High");
                    }

                    // Temperature feedback relative to previous guess
                    if (previousDistance != -1) {
                        if (currentDistance < previousDistance) {
                            backgroundPanel.setBackground(Color.RED);   // Warmer
                        } else {
                            backgroundPanel.setBackground(Color.BLUE);  // Colder
                        }
                    }
                    previousDistance = currentDistance;
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("Enter a valid integer between 1 and 1000.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessNumberGUI().setVisible(true));
    }
}