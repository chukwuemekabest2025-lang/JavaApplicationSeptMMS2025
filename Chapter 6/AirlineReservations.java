import java.util.Scanner;

public class AirlineReservations {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean[] seats = new boolean[10]; 

        while (true) {
            System.out.println("\nPlease type 1 for First Class");
            System.out.println("Please type 2 for Economy");
            System.out.print("Choice: ");
            int choice = input.nextInt();

            if (choice == 1) {
                assignSeat(seats, 0, 5, "First Class", "Economy", input);
            } else if (choice == 2) {
                assignSeat(seats, 5, 10, "Economy", "First Class", input);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void assignSeat(boolean[] seats, int start, int end, String currentSection, String alternateSection, Scanner input) {
        int assignedSeat = -1;

        for (int i = start; i < end; i++) {
            if (!seats[i]) {
                assignedSeat = i;
                break;
            }
        }

        if (assignedSeat != -1) {
            seats[assignedSeat] = true;
            printBoardingPass(assignedSeat + 1, currentSection);
        } else {
            System.out.printf("The %s section is full. Would you like to be placed in the %s section? (1 = Yes, 2 = No): ", currentSection, alternateSection);
            int changeSection = input.nextInt();

            if (changeSection == 1) {
                int altStart = (start == 0) ? 5 : 0;
                int altEnd = (start == 0) ? 10 : 5;
                
                int altAssignedSeat = -1;
                for (int i = altStart; i < altEnd; i++) {
                    if (!seats[i]) {
                        altAssignedSeat = i;
                        break;
                    }
                }

                if (altAssignedSeat != -1) {
                    seats[altAssignedSeat] = true;
                    printBoardingPass(altAssignedSeat + 1, alternateSection);
                } else {
                    System.out.println("Sorry, the entire flight is full. Next flight leaves in 3 hours.");
                }
            } else {
                System.out.println("Next flight leaves in 3 hours.");
            }
        }
    }

    private static void printBoardingPass(int seatNumber, String section) {
        System.out.println("\n=============================");
        System.out.println("        BOARDING PASS        ");
        System.out.printf("  Seat Number: %d%n", seatNumber);
        System.out.printf("  Section:     %s%n", section);
        System.out.println("=============================");
    }
}