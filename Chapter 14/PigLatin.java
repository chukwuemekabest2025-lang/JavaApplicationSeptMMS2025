import java.util.Scanner;

public class PigLatin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an English phrase: ");
        String input = scanner.nextLine();

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(translateToPigLatin(word)).append(" ");
            }
        }

        System.out.println("Pig Latin: " + result.toString().trim());
        scanner.close();
    }

    private static String translateToPigLatin(String word) {
        if (word.length() < 2) return word;
        return word.substring(1) + word.charAt(0) + "ay";
    }
}