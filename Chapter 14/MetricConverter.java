import java.util.Scanner;

public class MetricConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter metric conversion query (e.g. 'How many inches are in 2 meters?'):");
        String query = scanner.nextLine().toLowerCase();

        String[] tokens = query.split("\\s+");

        try {
            String targetUnit = tokens[2]; // "inches"
            double value = Double.parseDouble(tokens[5]); // "2"
            String sourceUnit = tokens[6].replaceAll("[^a-z]", ""); // "meters"

            double result = convert(value, sourceUnit, targetUnit);
            if (result != -1) {
                System.out.printf("%.2f %s = %.2f %s%n", value, sourceUnit, result, targetUnit);
            } else {
                System.out.println("Invalid unit conversion! Category mismatch (e.g., length to mass).");
            }
        } catch (Exception e) {
            System.out.println("Could not parse query. Use format: 'How many [unit1] are in [value] [unit2]?'");
        }
        scanner.close();
    }

    private static double convert(double val, String from, String to) {
        // Length conversions
        if (from.equals("meters") && to.equals("inches")) return val * 39.3701;
        if (from.equals("inches") && to.equals("meters")) return val / 39.3701;

        // Volume conversions
        if (from.equals("quarts") && to.equals("liters")) return val * 0.946353;
        if (from.equals("liters") && to.equals("quarts")) return val / 0.946353;

        // Mass conversions
        if (from.equals("grams") && to.equals("pounds")) return val * 0.00220462;
        if (from.equals("pounds") && to.equals("grams")) return val / 0.00220462;

        return -1; // Mismatched unit types
    }
}