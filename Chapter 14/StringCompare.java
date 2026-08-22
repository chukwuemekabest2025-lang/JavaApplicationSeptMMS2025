import java.util.Scanner;

public class StringCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String s2 = scanner.nextLine();

        int result = s1.compareTo(s2);

        if (result < 0) {
            System.out.println("\"" + s1 + "\" is less than \"" + s2 + "\"");
        } else if (result == 0) {
            System.out.println("\"" + s1 + "\" is equal to \"" + s2 + "\"");
        } else {
            System.out.println("\"" + s1 + "\" is greater than \"" + s2 + "\"");
        }
        scanner.close();
    }
}