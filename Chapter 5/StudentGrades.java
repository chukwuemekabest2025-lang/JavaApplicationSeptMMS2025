import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the student's average score (0-100): ");
        int average = input.nextInt();
        
        int points = qualityPoints(average);
        System.out.printf("Quality Points equivalent: %d%n", points);
        
        input.close();
    }

    public static int qualityPoints(int average) {
        if (average >= 90 && average <= 100) return 4;
        else if (average >= 80 && average <= 89) return 3;
        else if (average >= 70 && average <= 79) return 2;
        else if (average >= 60 && average <= 69) return 1;
        else return 0;
    }
}