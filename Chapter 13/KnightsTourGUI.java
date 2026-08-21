import javax.swing.*;
import java.awt.*;

public class KnightsTourGUI extends JPanel {
    private final int[][] board = new int[8][8];
    private final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    private int moveCount = 1;
    private int currRow = 0, currCol = 0;
    private Timer timer;

    public KnightsTourGUI() {
        board[currRow][currCol] = moveCount;

        timer = new Timer(300, e -> {
            if (!makeMove()) {
                timer.stop();
                String msg = (moveCount == 64) ? "Full Tour Completed!" : "Tour Stopped at Move " + moveCount;
                JOptionPane.showMessageDialog(this, msg);
            }
            repaint();
        });
        timer.start();
    }

    private boolean makeMove() {
        for (int i = 0; i < 8; i++) {
            int nextR = currRow + vertical[i];
            int nextC = currCol + horizontal[i];

            if (nextR >= 0 && nextR < 8 && nextC >= 0 && nextC < 8 && board[nextR][nextC] == 0) {
                currRow = nextR;
                currCol = nextC;
                moveCount++;
                board[currRow][currCol] = moveCount;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = Math.min(getWidth(), getHeight()) / 8;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 0) g.setColor(Color.LIGHT_GRAY);
                else g.setColor(Color.WHITE);

                g.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(c * tileSize, r * tileSize, tileSize, tileSize);

                if (board[r][c] > 0) {
                    g.drawString(String.valueOf(board[r][c]), c * tileSize + tileSize / 3, r * tileSize + tileSize / 2);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.24 Knight's Tour");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new KnightsTourGUI());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}