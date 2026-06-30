import java.util.Scanner;

public class DuplicateElimination {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] uniqueValues = new int[5];
        int count = 0; 

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter an integer between 10 and 100: ");
            int number = input.nextInt();

            while (number < 10 || number > 100) {
                System.out.print("Invalid! Enter a number between 10 and 100: ");
                number = input.nextInt();
            }

            boolean isDuplicate = false;
            for (int j = 0; j < count; j++) {
                if (uniqueValues[j] == number) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                uniqueValues[count] = number;
                count++;
                System.out.printf("Unique number registered: %d%n", number);
            } else {
                System.out.printf("%d has already been entered.%n", number);
            }

            System.out.print("Unique values so far: ");
            for (int k = 0; k < count; k++) {
                System.out.printf("%d ", uniqueValues[k]);
            }
            System.out.println("\n");
        }
        input.close();
    }
}