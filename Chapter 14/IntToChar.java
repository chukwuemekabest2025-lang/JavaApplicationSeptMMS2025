import java.util.Scanner;

public class IntToChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer character code (0-255): ");
        int code = scanner.nextInt();
        System.out.println("Character for " + code + " is: " + (char) code);

        System.out.println("\n--- All codes 000 to 255 ---");
        for (int i = 0; i <= 255; i++) {
            System.out.printf("Code %03d: %c%n", i, (char) i);
        }
        scanner.close();
    }
}