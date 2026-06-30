import java.util.Scanner;

public class TurtleGraphics {
    private static int direction = 0; 
    private static boolean penDown = false;
    private static int currentRow = 0;
    private static int currentCol = 0;
    private static int[][] floor = new int[20][20];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Turtle Graphics Simulation Initialized.");
        System.out.println("Commands: 1=Pen Up, 2=Pen Down, 3=Turn Right, 4=Turn Left");
        System.out.println("          5,10=Move Forward 10 fields, 6=Display, 9=End data");

        while (true) {
            System.out.print("Enter command: ");
            String commandLine = input.nextLine().trim();

            if (commandLine.equals("9")) {
                break;
            }

            if (commandLine.startsWith("5")) {
                String[] parts = commandLine.split(",");
                if (parts.length == 2) {
                    int steps = Integer.parseInt(parts[1].trim());
                    moveTurtle(steps);
                }
            } else {
                switch (commandLine) {
                    case "1":
                        penDown = false;
                        break;
                    case "2":
                        penDown = true;
                        floor[currentRow][currentCol] = 1; 
                        break;
                    case "3": 
                        direction = (direction + 1) % 4;
                        break;
                    case "4": 
                        direction = (direction + 3) % 4;
                        break;
                    case "6":
                        displayFloor();
                        break;
                    default:
                        System.out.println("Unknown command code.");
                }
            }
        }
        input.close();
    }

    private static void moveTurtle(int steps) {
        int rowIncrement = 0;
        int colIncrement = 0;

        switch (direction) {
            case 0: colIncrement = 1;  break; 
            case 1: rowIncrement = 1;  break; 
            case 2: colIncrement = -1; break; 
            case 3: rowIncrement = -1; break; 
        }

        for (int i = 0; i < steps; i++) {
            int nextRow = currentRow + rowIncrement;
            int nextCol = currentCol + colIncrement;

            if (nextRow >= 0 && nextRow < 20 && nextCol >= 0 && nextCol < 20) {
                currentRow = nextRow;
                currentCol = nextCol;
                if (penDown) {
                    floor[currentRow][currentCol] = 1;
                }
            } else {
                System.out.println("Turtle hit a wall! Movement halted prematurely.");
                break;
            }
        }
    }

    private static void displayFloor() {
        System.out.println("\n--- Current Sketchpad Output ---");
        for (int[] row : floor) {
            for (int item : row) {
                System.out.print(item == 1 ? "* " : "  ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------\n");
    }
}