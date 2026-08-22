import java.util.Arrays;

public class SimpleCrosswordGenerator {
    private static final int SIZE = 10;
    private static final char[][] grid = new char[SIZE][SIZE];

    public static void main(String[] args) {
        for (char[] row : grid) Arrays.fill(row, '.');

        String[] words = {"JAVA", "ARRAY", "STRING", "CODE"};

        // Place horizontal word
        placeHorizontal(words[0], 2, 2);
        
        // Place vertical word intersecting at 'A'
        placeVertical(words[1], 1, 3);

        printGrid();
    }

    private static void placeHorizontal(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            grid[row][col + i] = word.charAt(i);
        }
    }

    private static void placeVertical(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            grid[row + i][col] = word.charAt(i);
        }
    }

    private static void printGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}