import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;

public class ExerciseMenu extends JFrame {

    public ExerciseMenu() {
        super("GUI Exercises Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btn12_13 = new JButton("12.13 Temperature Converter");
        JButton btn12_14 = new JButton("12.14 Guess the Number");
        JButton btn12_15 = new JButton("12.15 Event Tracker");
        JButton btn12_16 = new JButton("12.16 Craps Game");
        JButton btn12_17 = new JButton("12.17 Drawing Application");

        btn12_13.addActionListener(e -> new MultiTemperatureConverterGUI().setVisible(true));
        btn12_14.addActionListener(e -> new GuessNumberGUI().setVisible(true));
        btn12_15.addActionListener(e -> new EventTrackerGUI().setVisible(true));
        btn12_16.addActionListener(e -> new CrapsGUI().setVisible(true));
        btn12_17.addActionListener(e -> new DrawFrame().setVisible(true));

        add(btn12_13);
        add(btn12_14);
        add(btn12_15);
        add(btn12_16);
        add(btn12_17);

        setSize(300, 250);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExerciseMenu().setVisible(true));
    }
}