import java.util.Scanner;

public class AsteriskSquare {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter square side size: ");
        int side = input.nextInt();
        
        squareOfAsterisks(side);
        
        input.close();
    }

    public static void squareOfAsterisks(int side) {
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                System.out.print("*");
            }
            System.out.println(); 
        }
    }
}