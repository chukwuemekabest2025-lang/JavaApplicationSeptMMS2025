public class ClosedKnightsTour {
    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    
    public static void main(String[] args) {
        int startRow = 0, startCol = 0;
        int[][] board = new int[8][8];
        int currentRow = startRow, currentColumn = startCol;
        board[currentRow][currentColumn] = 1;

        for (int move = 2; move <= 64; move++) {
            int bestIdx = -1;
            int minAccess = 9;
            for (int i = 0; i < 8; i++) {
                int nextR = currentRow + vertical[i];
                int nextC = currentColumn + horizontal[i];
                if (nextR >= 0 && nextR < 8 && nextC >= 0 && nextC < 8 && board[nextR][nextC] == 0) {
                    bestIdx = i; 
                }
            }
            if (bestIdx == -1) break;
            currentRow += vertical[bestIdx];
            currentColumn += horizontal[bestIdx];
            board[currentRow][currentColumn] = move;
        }

        if (board[currentRow][currentColumn] == 64) {
            boolean isClosed = false;
            for (int i = 0; i < 8; i++) {
                if (currentRow + vertical[i] == startRow && currentColumn + horizontal[i] == startCol) {
                    isClosed = true;
                    break;
                }
            }
            System.out.println(isClosed ? "SUCCESS: This is a Closed Tour!" : "Full tour completed, but it is Open.");
        }
    }
}