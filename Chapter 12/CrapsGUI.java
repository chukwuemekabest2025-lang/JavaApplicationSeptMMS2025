import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.security.SecureRandom;

public class CrapsGUI extends JFrame {

    private enum Status { CONTINUE, WON, LOST }

    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;

    private static final SecureRandom randomNumbers = new SecureRandom();

    private int myPoint = 0;
    private Status gameStatus = Status.CONTINUE;
    private boolean isFirstRoll = true;

    private final JTextField die1Field;
    private final JTextField die2Field;
    private final JTextField sumField;
    private final JTextField pointField;
    private final JLabel statusLabel;
    private final JButton rollButton;

    public CrapsGUI() {
        super("Craps Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Form Fields Grid Layout
        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        gridPanel.add(new JLabel("Die 1:"));
        die1Field = createReadOnlyField();
        gridPanel.add(die1Field);

        gridPanel.add(new JLabel("Die 2:"));
        die2Field = createReadOnlyField();
        gridPanel.add(die2Field);

        gridPanel.add(new JLabel("Sum:"));
        sumField = createReadOnlyField();
        gridPanel.add(sumField);

        gridPanel.add(new JLabel("Point:"));
        pointField = createReadOnlyField();
        gridPanel.add(pointField);

        // Control Panel
        JPanel southPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        rollButton = new JButton("Roll Dice");
        statusLabel = new JLabel("Click Roll Dice to Start", JLabel.CENTER);

        southPanel.add(rollButton);
        southPanel.add(statusLabel);

        rollButton.addActionListener(e -> playRound());

        add(gridPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setSize(300, 230);
        setLocationRelativeTo(null);
    }

    private JTextField createReadOnlyField() {
        JTextField field = new JTextField();
        field.setEditable(false);
        return field;
    }

    private void playRound() {
        int d1 = 1 + randomNumbers.nextInt(6);
        int d2 = 1 + randomNumbers.nextInt(6);
        int sumOfDice = d1 + d2;

        die1Field.setText(String.valueOf(d1));
        die2Field.setText(String.valueOf(d2));
        sumField.setText(String.valueOf(sumOfDice));

        if (isFirstRoll) {
            switch (sumOfDice) {
                case SEVEN:
                case YO_LEVEN:
                    gameStatus = Status.WON;
                    pointField.setText("");
                    break;
                case SNAKE_EYES:
                case TREY:
                case BOX_CARS:
                    gameStatus = Status.LOST;
                    pointField.setText("");
                    break;
                default:
                    gameStatus = Status.CONTINUE;
                    myPoint = sumOfDice;
                    pointField.setText(String.valueOf(myPoint));
                    isFirstRoll = false;
                    break;
            }
        } else {
            if (sumOfDice == myPoint) {
                gameStatus = Status.WON;
            } else if (sumOfDice == SEVEN) {
                gameStatus = Status.LOST;
            }
        }

        updateGameStatus();
    }

    private void updateGameStatus() {
        if (gameStatus == Status.WON) {
            statusLabel.setText("You Win! Click Roll to play again.");
            isFirstRoll = true;
        } else if (gameStatus == Status.LOST) {
            statusLabel.setText("You Lose! Click Roll to play again.");
            isFirstRoll = true;
        } else {
            statusLabel.setText("Roll again!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrapsGUI().setVisible(true));
    }
}