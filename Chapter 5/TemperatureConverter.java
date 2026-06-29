import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("1. Convert Fahrenheit to Celsius");
        System.out.println("2. Convert Celsius to Fahrenheit");
        System.out.print("Choose option (1 or 2): ");
        int choice = input.nextInt();
        
        if (choice == 1) {
            System.out.print("Enter Fahrenheit temperature: ");
            int fahr = input.nextInt();
            System.out.printf("Celsius equivalent: %d°C%n", celsius(fahr));
        } else if (choice == 2) {
            System.out.print("Enter Celsius temperature: ");
            int cels = input.nextInt();
            System.out.printf("Fahrenheit equivalent: %d°F%n", fahrenheit(cels));
        } else {
            System.out.println("Invalid choice.");
        }
        
        input.close();
    }

    public static int celsius(int fahrenheit) {
        return (int) (5.0 / 9.0 * (fahrenheit - 32));
    }

    public static int fahrenheit(int celsius) {
        return (int) (9.0 / 5.0 * celsius + 32);
    }
}