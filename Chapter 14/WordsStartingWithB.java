import java.util.Scanner;

public class WordsStartingWithB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String text = scanner.nextLine();

        String[] words = text.split("\\s+");

        System.out.println("Words starting with 'b':");
        for (String word : words) {
            if (word.toLowerCase().startsWith("b")) {
                System.out.println(word);
            }
        }
        scanner.close();
    }
}