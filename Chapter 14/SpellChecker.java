import java.util.*;

public class SpellChecker {
    private static final List<String> wordList = Arrays.asList(
        "default", "computer", "java", "program", "string", "birthday", "handy"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine().toLowerCase();

        if (wordList.contains(input)) {
            System.out.println("Word is spelled correctly.");
        } else {
            System.out.println("Word is not spelled correctly.");
            checkSuggestions(input);
        }
        scanner.close();
    }

    private static void checkSuggestions(String input) {
        // Check single transpositions of adjacent letters
        for (int i = 0; i < input.length() - 1; i++) {
            char[] chars = input.toCharArray();
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String transposed = new String(chars);

            if (wordList.contains(transposed)) {
                System.out.println("Did you mean \"" + transposed + "\"?");
            }
        }
    }
}