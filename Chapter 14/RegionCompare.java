import java.util.Scanner;

public class RegionCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter start index for first string: ");
        int s1Start = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        System.out.print("Enter second string: ");
        String s2 = scanner.nextLine();
        System.out.print("Enter start index for second string: ");
        int s2Start = scanner.nextInt();

        System.out.print("Enter number of characters to compare: ");
        int length = scanner.nextInt();

        boolean match = s1.regionMatches(true, s1Start, s2, s2Start, length);

        if (match) {
            System.out.println("The specified regions are EQUAL (case-insensitive).");
        } else {
            System.out.println("The specified regions are NOT equal.");
        }
        scanner.close();
    }
}