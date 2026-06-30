import java.util.Arrays;

public class KnightsTour {
    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    
    private static final int[][] baseAccessibility = {
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {2, 3, 4, 4, 4, 4, 3, 2}
    };

    public static void main(String[] args) {
        System.out.println("--- Running Heuristic Knight's Tour from Corner (0,0) ---");
        runSingleTour(0, 0);
    }

    public static void runSingleTour(int startRow, int startCol) {
        int[][] board = new int[8][8];
        int[][] access = new int[8][8];
        for (int i = 0; i < 8; i++) access[i] = Arrays.copyOf(baseAccessibility[i], 8);

        int currentRow = startRow;
        int currentColumn = startCol;
        int moveCounter = 1;
        board[currentRow][currentColumn] = moveCounter;

        while (moveCounter < 64) {
            int bestMoveIndex = -1;
            int minAccess = 9; 

            for (int i = 0; i < 8; i++) {
                int nextRow = currentRow + vertical[i];
                int nextCol = currentColumn + horizontal[i];

                if (isValidMove(nextRow, nextCol, board)) {
                    if (access[nextRow][nextCol] < minAccess) {
                        minAccess = access[nextRow][nextCol];
                        bestMoveIndex = i;
                    }
                }
            }

            if (bestMoveIndex == -1) {
                break;
            }

            for (int i = 0; i < 8; i++) {
                int targetRow = currentRow + vertical[i];
                int targetCol = currentColumn + horizontal[i];
                if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {
                    access[targetRow][targetCol]--;
                }
            }

            currentRow += vertical[bestMoveIndex];
            currentColumn += horizontal[bestMoveIndex];
            moveCounter++;
            board[currentRow][currentColumn] = moveCounter;
        }

        printBoard(board);
        System.out.println("Total moves made: " + moveCounter);
    }

    private static boolean isValidMove(int row, int col, int[][] board) {
        return (row >= 0 && row < 8 && col >= 0 && col < 8 && board[row][col] == 0);
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int val : row) {
                System.out.printf("%3d", val);
            }
            System.out.println();
        }
    }
}