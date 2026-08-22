import java.util.Scanner;

public class WordsEndingWithED {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String text = scanner.nextLine();

        String[] words = text.split("\\s+");

        System.out.println("Words ending with 'ED':");
        for (String word : words) {
            // Remove punctuation if present
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            if (cleanWord.toUpperCase().endsWith("ED")) {
                System.out.println(cleanWord);
            }
        }
        scanner.close();
    }
}