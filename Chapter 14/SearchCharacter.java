import java.util.Scanner;

public class SearchCharacter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String text = scanner.nextLine();
        System.out.print("Enter character to count: ");
        char target = scanner.next().charAt(0);

        int count = 0;
        int index = text.indexOf(target);

        while (index != -1) {
            count++;
            index = text.indexOf(target, index + 1);
        }

        System.out.println("Occurrences of '" + target + "': " + count);
        scanner.close();
    }
}