import java.util.Scanner;

public class ReverseSentence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String line = scanner.nextLine();

        String[] tokens = line.split(" ");

        System.out.print("Reversed words: ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            System.out.print(tokens[i] + " ");
        }
        System.out.println();
        scanner.close();
    }
}