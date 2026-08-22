import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EventTrackerGUI extends JFrame implements ActionListener, ItemListener,
        ListSelectionListener, MouseListener, MouseMotionListener, KeyListener {

    private final JTextArea logArea;

    public EventTrackerGUI() {
        super("Event Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Interactive control panel
        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton button = new JButton("Click Me");
        JCheckBox checkBox = new JCheckBox("Check Me");
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        JList<String> list = new JList<>(new String[]{"Item A", "Item B"});
        JTextField textField = new JTextField("Type here", 10);

        controlPanel.add(button);
        controlPanel.add(checkBox);
        controlPanel.add(comboBox);
        controlPanel.add(new JScrollPane(list));
        controlPanel.add(textField);

        // Logging Display
        logArea = new JTextArea(15, 50);
        logArea.setEditable(false);

        // Event Registration
        button.addActionListener(this);
        checkBox.addItemListener(this);
        comboBox.addItemListener(this);
        list.addListSelectionListener(this);
        textField.addKeyListener(this);

        // Mouse listeners attached to whole frame surface
        addMouseListener(this);
        addMouseMotionListener(this);

        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);
        add(new JLabel("Move or click mouse in frame area to generate mouse events.", JLabel.CENTER), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void logEvent(String handlerName, Object event) {
        logArea.append("[" + handlerName + "]\n" + event.toString() + "\n\n");
    }

    // ActionListener
    @Override public void actionPerformed(ActionEvent e) { logEvent("ActionListener", e); }

    // ItemListener
    @Override public void itemStateChanged(ItemEvent e) { logEvent("ItemListener", e); }

    // ListSelectionListener
    @Override public void valueChanged(ListSelectionEvent e) { logEvent("ListSelectionListener", e); }

    // KeyListener
    @Override public void keyTyped(KeyEvent e) { logEvent("KeyListener (keyTyped)", e); }
    @Override public void keyPressed(KeyEvent e) { logEvent("KeyListener (keyPressed)", e); }
    @Override public void keyReleased(KeyEvent e) { logEvent("KeyListener (keyReleased)", e); }

    // MouseListener
    @Override public void mouseClicked(MouseEvent e) { logEvent("MouseListener (mouseClicked)", e); }
    @Override public void mousePressed(MouseEvent e) { logEvent("MouseListener (mousePressed)", e); }
    @Override public void mouseReleased(MouseEvent e) { logEvent("MouseListener (mouseReleased)", e); }
    @Override public void mouseEntered(MouseEvent e) { logEvent("MouseListener (mouseEntered)", e); }
    @Override public void mouseExited(MouseEvent e) { logEvent("MouseListener (mouseExited)", e); }

    // MouseMotionListener
    @Override public void mouseDragged(MouseEvent e) { logEvent("MouseMotionListener (mouseDragged)", e); }
    @Override public void mouseMoved(MouseEvent e) { logEvent("MouseMotionListener (mouseMoved)", e); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventTrackerGUI().setVisible(true));
    }
}