import java.util.Scanner;
import java.security.SecureRandom;

public class CAISystem {
    private static final SecureRandom random = new SecureRandom();
    private static int correctAnswer = 0;
    private static String currentQuestion = "";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.println("=== COMPUTER-ASSISTED INSTRUCTION SYSTEM ===");
            System.out.print("Enter difficulty level (1 = single digits, 2 = two digits, etc.): ");
            int difficulty = input.nextInt();
            
            System.out.println("\nChoose Operation Type:");
            System.out.println("1. Addition\n2. Subtraction\n3. Multiplication\n4. Division\n5. Random Mixture");
            System.out.print("Choice: ");
            int opType = input.nextInt();

            int correctCount = 0;
            int totalQuestions = 10;

            System.out.println("\nBeginning your session of 10 questions...");
            
            for (int i = 1; i <= totalQuestions; i++) {
                generateQuestion(difficulty, opType);
                System.out.printf("Question %d: %s ", i, currentQuestion);
                int studentAnswer = input.nextInt();

                if (studentAnswer == correctAnswer) {
                    printChatter(true);
                    correctCount++;
                } else {
                    printChatter(false);
                    // Give them chances to get this specific question right before moving on
                    while (studentAnswer != correctAnswer) {
                        System.out.print("Try again: ");
                        studentAnswer = input.nextInt();
                        if (studentAnswer != correctAnswer) {
                            printChatter(false);
                        }
                    }
                    printChatter(true);
                }
            }

            double percentage = ((double) correctCount / totalQuestions) * 100;
            System.out.printf("%nYour score: %.1f%%%n", percentage);
            
            if (percentage < 75.0) {
                System.out.println("Please ask your teacher for extra help.\n");
            } else {
                System.out.println("Congratulations, you are ready to go to the next level!\n");
            }
            
            System.out.print("Next student ready? (true/false): ");
            if (!input.nextBoolean()) break;
        }
        input.close();
    }

    public static void generateQuestion(int level, int opType) {
        int max = (int) Math.pow(10, level);
        int min = (level == 1) ? 1 : (int) Math.pow(10, level - 1);
        
        int num1 = min + random.nextInt(max - min);
        int num2 = min + random.nextInt(max - min);

        int choice = (opType == 5) ? 1 + random.nextInt(4) : opType;

        switch (choice) {
            case 1:
                correctAnswer = num1 + num2;
                currentQuestion = String.format("How much is %d plus %d?", num1, num2);
                break;
            case 2:
                correctAnswer = num1 - num2;
                currentQuestion = String.format("How much is %d minus %d?", num1, num2);
                break;
            case 3:
                correctAnswer = num1 * num2;
                currentQuestion = String.format("How much is %d times %d?", num1, num2);
                break;
            case 4:
                while (num2 == 0) num2 = min + random.nextInt(max - min); // Prevent division by zero
                correctAnswer = num1 / num2;
                currentQuestion = String.format("How much is %d divided by %d (integer division)?", num1, num2);
                break;
        }
    }

    public static void printChatter(boolean isCorrect) {
        int index = 1 + random.nextInt(4);
        if (isCorrect) {
            switch (index) {
                case 1: System.out.println("Very good!"); break;
                case 2: System.out.println("Excellent!"); break;
                case 3: System.out.println("Nice work!"); break;
                case 4: System.out.println("Keep up the good work!"); break;
            }
        } else {
            switch (index) {
                case 1: System.out.println("No. Please try again."); break;
                case 2: System.out.println("Wrong. Try once more."); break;
                case 3: System.out.println("Don't give up! No."); break;
                case 4: System.out.println("Keep trying."); break;
            }
        }
    }
}