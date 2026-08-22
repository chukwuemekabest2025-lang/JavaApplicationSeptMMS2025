import java.util.Scanner;

public class AlphabetCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String text = scanner.nextLine().toLowerCase();

        int[] counts = new int[26];

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                counts[ch - 'a']++;
            }
        }

        System.out.printf("%-10s%-10s%n", "Letter", "Count");
        for (int i = 0; i < 26; i++) {
            System.out.printf("%-10c%-10d%n", (char)('a' + i), counts[i]);
        }
        scanner.close();
    }
}