public class CommandLineSum {
    public static void main(String[] args) {
        double totalSum = 0.0;

        for (String arg : args) {
            try {
                totalSum += Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                System.out.printf("Warning: '%s' is not a valid number and was skipped.%n", arg);
            }
        }

        System.out.printf("The total sum of command-line arguments is: %.2f%n", totalSum);
    }
}