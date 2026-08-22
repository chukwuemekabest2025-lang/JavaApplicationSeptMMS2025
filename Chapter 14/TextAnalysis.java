import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter line of text: ");
        String text = scanner.nextLine();

        // Part a: Letter counts
        System.out.println("\n--- Part (a): Letter Counts ---");
        int[] letterCounts = new int[26];
        for (char c : text.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') letterCounts[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (letterCounts[i] > 0) {
                System.out.printf("%c: %d  ", (char)('a' + i), letterCounts[i]);
            }
        }
        System.out.println();

        // Part b: Word length occurrences
        System.out.println("\n--- Part (b): Word Length Occurrences ---");
        String[] words = text.split("\\s+");
        int[] lengthCounts = new int[25]; // Max length 24
        for (String w : words) {
            String clean = w.replaceAll("[^a-zA-Z]", "");
            if (!clean.isEmpty()) {
                lengthCounts[clean.length()]++;
            }
        }
        System.out.printf("%-15s%-12s%n", "Word Length", "Occurrences");
        for (int i = 1; i < lengthCounts.length; i++) {
            if (lengthCounts[i] > 0) {
                System.out.printf("%-15d%-12d%n", i, lengthCounts[i]);
            }
        }

        // Part c: Word occurrence counts
        System.out.println("\n--- Part (c): Unique Word Occurrences ---");
        Map<String, Integer> wordFrequency = new LinkedHashMap<>();
        for (String w : words) {
            String clean = w.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!clean.isEmpty()) {
                wordFrequency.put(clean, wordFrequency.getOrDefault(clean, 0) + 1);
            }
        }
        System.out.printf("%-20s%-12s%n", "Word", "Count");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.printf("%-20s%-12d%n", entry.getKey(), entry.getValue());
        }

        scanner.close();
    }
}