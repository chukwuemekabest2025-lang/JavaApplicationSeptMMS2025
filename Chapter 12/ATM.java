import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class ATM extends JFrame {

    // Bank Database Models
    private int currentAccountNumber = -1;
    private boolean authenticated = false;

    // Simulated Account Database [AccountNumber, PIN, AvailableBalance, TotalBalance]
    private final double[][] accounts = {
        {12345, 54321, 1000.0, 1200.0},
        {98765, 56789, 2000.0, 2000.0}
    };

    // GUI Components
    private final JTextArea screenArea;
    private final JTextField keyPadDisplay;
    private final JButton removeCashButton;
    private final JButton insertEnvelopeButton;

    // State control
    private int currentState = STATE_ACCOUNT_NUM;
    private static final int STATE_ACCOUNT_NUM = 1;
    private static final int STATE_PIN = 2;
    private static final int STATE_MAIN_MENU = 3;
    private static final int STATE_WITHDRAWAL = 4;
    private static final int STATE_DEPOSIT = 5;

    private int tempAccNum = -1;
    private double pendingDepositAmount = 0;

    public ATM() {
        super("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Screen Area (Center Top)
        screenArea = new JTextArea(8, 30);
        screenArea.setEditable(false);
        screenArea.setFont(new Font("Monospaced", Font.BOLD, 13));
        screenArea.setBackground(new Color(230, 240, 250));
        JScrollPane scrollScreen = new JScrollPane(screenArea);

        // Keypad Container
        JPanel keyPadPanel = new JPanel(new BorderLayout(5, 5));
        keyPadDisplay = new JTextField(15);
        keyPadDisplay.setEditable(false);
        keyPadDisplay.setHorizontalAlignment(JTextField.RIGHT);
        keyPadDisplay.setFont(new Font("SansSerif", Font.BOLD, 16));

        JPanel buttonGrid = new JPanel(new GridLayout(4, 3, 5, 5));
        String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Clear", "Enter"};

        for (String key : keys) {
            JButton btn = new JButton(key);
            btn.addActionListener(this::handleKeyPadInput);
            buttonGrid.add(btn);
        }

        keyPadPanel.add(keyPadDisplay, BorderLayout.NORTH);
        keyPadPanel.add(buttonGrid, BorderLayout.CENTER);

        // Hardware Controls Panel (Cash Dispenser & Deposit Slot)
        JPanel hardwarePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        removeCashButton = new JButton("Remove Cash");
        removeCashButton.setEnabled(false);
        removeCashButton.addActionListener(e -> {
            removeCashButton.setEnabled(false);
            displayMessage("\nCash removed from dispenser.\nTaking you to main menu...");
            showMainMenu();
        });

        insertEnvelopeButton = new JButton("Insert Envelope");
        insertEnvelopeButton.setEnabled(false);
        insertEnvelopeButton.addActionListener(e -> {
            insertEnvelopeButton.setEnabled(false);
            processDepositEnvelope();
        });

        hardwarePanel.add(new JLabel("Dispenser / Slot:"));
        hardwarePanel.add(removeCashButton);
        hardwarePanel.add(insertEnvelopeButton);

        // Main Layout Assembly
        JPanel centerContainer = new JPanel(new BorderLayout(10, 10));
        centerContainer.add(scrollScreen, BorderLayout.NORTH);
        centerContainer.add(keyPadPanel, BorderLayout.CENTER);

        add(centerContainer, BorderLayout.CENTER);
        add(hardwarePanel, BorderLayout.SOUTH);

        setSize(420, 520);
        setLocationRelativeTo(null);

        promptAccountNumber();
    }

    private void handleKeyPadInput(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Clear")) {
            keyPadDisplay.setText("");
        } else if (command.equals("Enter")) {
            processEnterKey();
        } else {
            keyPadDisplay.setText(keyPadDisplay.getText() + command);
        }
    }

    private void processEnterKey() {
        String input = keyPadDisplay.getText().trim();
        keyPadDisplay.setText("");

        if (input.isEmpty()) return;

        try {
            switch (currentState) {
                case STATE_ACCOUNT_NUM -> {
                    tempAccNum = Integer.parseInt(input);
                    currentState = STATE_PIN;
                    screenArea.setText("Enter your PIN:");
                }
                case STATE_PIN -> {
                    int pin = Integer.parseInt(input);
                    if (authenticateUser(tempAccNum, pin)) {
                        authenticated = true;
                        currentAccountNumber = tempAccNum;
                        showMainMenu();
                    } else {
                        screenArea.setText("Invalid account number or PIN.\nPlease try again.\n\nEnter Account Number:");
                        currentState = STATE_ACCOUNT_NUM;
                    }
                }
                case STATE_MAIN_MENU -> processMainMenuChoice(Integer.parseInt(input));
                case STATE_WITHDRAWAL -> processWithdrawalChoice(Integer.parseInt(input));
                case STATE_DEPOSIT -> processDepositInput(Double.parseDouble(input));
            }
        } catch (NumberFormatException ex) {
            displayMessage("\nInvalid input format. Try again.");
        }
    }

    private boolean authenticateUser(int accNum, int pin) {
        for (double[] acc : accounts) {
            if ((int) acc[0] == accNum && (int) acc[1] == pin) {
                return true;
            }
        }
        return false;
    }

    private int getAccountIndex() {
        for (int i = 0; i < accounts.length; i++) {
            if ((int) accounts[i][0] == currentAccountNumber) return i;
        }
        return -1;
    }

    private void promptAccountNumber() {
        currentState = STATE_ACCOUNT_NUM;
        screenArea.setText("Welcome to the Bank ATM!\n\nPlease enter your Account Number:");
    }

    private void showMainMenu() {
        currentState = STATE_MAIN_MENU;
        screenArea.setText("Main menu:\n" +
                "1 - View my balance\n" +
                "2 - Withdraw cash\n" +
                "3 - Deposit funds\n" +
                "4 - Exit\n\n" +
                "Enter a choice:");
    }

    private void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> {
                int idx = getAccountIndex();
                screenArea.setText(String.format("Balance Information:\n - Available balance: $%.2f\n - Total balance:     $%.2f\n",
                        accounts[idx][2], accounts[idx][3]));
                showMainMenu();
            }
            case 2 -> {
                currentState = STATE_WITHDRAWAL;
                screenArea.setText("Withdrawal options:\n" +
                        "1 - $20     4 - $100\n" +
                        "2 - $40     5 - $200\n" +
                        "3 - $60     6 - Cancel\n\n" +
                        "Choose a withdrawal amount:");
            }
            case 3 -> {
                currentState = STATE_DEPOSIT;
                screenArea.setText("Please enter a deposit amount in CENTS\n(or 0 to cancel):");
            }
            case 4 -> {
                authenticated = false;
                currentAccountNumber = -1;
                displayMessage("Thank you! Goodbye.");
                promptAccountNumber();
            }
            default -> displayMessage("\nInvalid option selected.");
        }
    }

    private void processWithdrawalChoice(int choice) {
        int amount = switch (choice) {
            case 1 -> 20;
            case 2 -> 40;
            case 3 -> 60;
            case 4 -> 100;
            case 5 -> 200;
            default -> 0;
        };

        if (choice == 6) {
            showMainMenu();
            return;
        }

        if (amount == 0) {
            displayMessage("\nInvalid withdrawal selection.");
            return;
        }

        int idx = getAccountIndex();
        if (accounts[idx][2] >= amount) {
            accounts[idx][2] -= amount;
            accounts[idx][3] -= amount;

            screenArea.setText("Please collect your cash from dispenser below.");
            removeCashButton.setEnabled(true);
        } else {
            screenArea.setText("Insufficient funds in account.\nChoose a smaller amount.");
        }
    }

    private void processDepositInput(double cents) {
        if (cents == 0) {
            showMainMenu();
            return;
        }

        pendingDepositAmount = cents / 100.0;
        screenArea.setText(String.format("Please insert deposit envelope containing $%.2f\ninto the slot below.", pendingDepositAmount));
        insertEnvelopeButton.setEnabled(true);
    }

    private void processDepositEnvelope() {
        int idx = getAccountIndex();
        accounts[idx][3] += pendingDepositAmount;

        screenArea.setText(String.format("Your envelope of $%.2f has been received.\nNote: Funds deposited subject to verification.", pendingDepositAmount));
        showMainMenu();
    }

    private void displayMessage(String msg) {
        screenArea.append("\n" + msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM().setVisible(true));
    }
}