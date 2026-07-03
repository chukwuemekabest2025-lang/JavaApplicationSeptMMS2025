import java.util.Scanner;

public class HeartRateApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String first = input.nextLine();

        System.out.print("Enter last name: ");
        String last = input.nextLine();

        System.out.print("Enter birth month (1-12): ");
        int month = input.nextInt();

        System.out.print("Enter birth day (1-31): ");
        int day = input.nextInt();

        System.out.print("Enter birth year (e.g., 1995): ");
        int year = input.nextInt();

        HeartRates person = new HeartRates(first, last, month, day, year);

        System.out.println("\n--- Health Profile Report ---");
        System.out.printf("Name: %s %s%n", person.getFirstName(), person.getLastName());
        System.out.printf("DOB: %02d/%02d/%d%n", person.getBirthMonth(), person.getBirthDay(), person.getBirthYear());
        System.out.printf("Age: %d years old%n", person.getAge());
        System.out.printf("Maximum Heart Rate: %d bpm%n", person.getMaxHeartRate());
        System.out.printf("Target Heart Rate Range (50%%-85%%): %s%n", person.getTargetHeartRateRange());
    }
}