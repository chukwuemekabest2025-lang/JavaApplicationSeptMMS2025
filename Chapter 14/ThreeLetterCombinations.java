import java.util.Scanner;

public class ThreeLetterCombinations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a 5-letter word: ");
        String word = scanner.nextLine();

        if (word.length() != 5) {
            System.out.println("Word must be exactly 5 letters!");
            scanner.close();
            return;
        }

        System.out.println("All 3-letter combinations:");
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == i) continue;
                for (int k = 0; k < 5; k++) {
                    if (k == i || k == j) continue;
                    String combo = "" + word.charAt(i) + word.charAt(j) + word.charAt(k);
                    System.out.print(combo + "  ");
                    count++;
                    if (count % 10 == 0) System.out.println();
                }
            }
        }
        System.out.println("\nTotal generated: " + count);
        scanner.close();
    }
}