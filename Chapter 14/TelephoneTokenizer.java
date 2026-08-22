import java.util.Scanner;

public class TelephoneTokenizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number in format (555) 555-5555: ");
        String phone = scanner.nextLine();

        // Split by non-digit regex pattern
        String[] tokens = phone.split("[()\\s-]+");

        // First token will be empty if string starts with '('
        int startIdx = tokens[0].isEmpty() ? 1 : 0;

        String areaCode = tokens[startIdx];
        String sevenDigits = tokens[startIdx + 1] + tokens[startIdx + 2];

        System.out.println("Area code: " + areaCode);
        System.out.println("Phone number: " + sevenDigits);
        scanner.close();
    }
}