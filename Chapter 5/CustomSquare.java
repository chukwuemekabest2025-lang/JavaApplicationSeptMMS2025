import java.util.Scanner;

public class CustomSquare {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter square side size: ");
        int side = input.nextInt();
        
        System.out.print("Enter character to fill with: ");
        char fill = input.next().charAt(0);
        
        squareOfCharacters(side, fill);
        
        input.close();
    }

    public static void squareOfCharacters(int side, char fillCharacter) {
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                System.out.print(fillCharacter);
            }
            System.out.println();
        }
    }
}